package com.bancobase.demo.pagos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.demo.pagos.dto.PagoRequestDTO;
import com.bancobase.demo.pagos.model.EmisorPago;
import com.bancobase.demo.pagos.model.EstatusPago;
import com.bancobase.demo.pagos.model.Pago;
import com.bancobase.demo.pagos.model.ReceptorPago;
import com.bancobase.demo.pagos.repository.EmisorPagoRepository;
import com.bancobase.demo.pagos.repository.EstatusPagoRepository;
import com.bancobase.demo.pagos.repository.PagosRepository;
import com.bancobase.demo.pagos.repository.ReceptorPagoRepository;
import com.bancobase.demo.pagos.service.KafkaProducerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pagos", description = "Operaciones sobre las operaciones de pago")
public class PagosController {
	
    @Autowired
    private PagosRepository pagosRepository;
    
    @Autowired
    private EstatusPagoRepository estatusRepository;
    
    @Autowired
    private EmisorPagoRepository emisorPagoRepository;
    
    @Autowired
    private ReceptorPagoRepository receptorPagoRepository;
     
    @Autowired
    private KafkaProducerService kafkaProducerService;
    

    @Operation(summary = "Crear un nuevo pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado con éxito"),
        @ApiResponse(responseCode = "400", description = "Error en los datos de entrada")
    })
    @PostMapping("/pagos")
	public ResponseEntity<Pago> crearPago(@RequestBody PagoRequestDTO pagoRequest) {
		EstatusPago estatus = estatusRepository.findById(pagoRequest.getIdEstatus())
				.orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

		EmisorPago emisor = emisorPagoRepository.findById(pagoRequest.getIdEmisorPago())
				.orElseThrow(() -> new RuntimeException("Emisor no encontrado"));

		ReceptorPago receptor = receptorPagoRepository.findById(pagoRequest.getIdReceptorPago())
				.orElseThrow(() -> new RuntimeException("Receptor no encontrado"));

		Pago pago = new Pago();
		pago.setCantidadProductos(pagoRequest.getCantidadProductos());
		pago.setMonto(pagoRequest.getMonto());
		pago.setConcepto(pagoRequest.getConcepto());
		pago.setFechaCreacion(pagoRequest.getFechaCreacion());
		pago.setEstatus(estatus);
		pago.setEmisor(emisor);
		pago.setReceptor(receptor);

		Pago pagoGuardado = pagosRepository.save(pago);
		return ResponseEntity.status(201).body(pagoGuardado);
	}
    
    
    @Operation(summary = "Consultar pago por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/pagos/{id}")
    public ResponseEntity<Pago> consultarPago(@PathVariable Integer id) {
        Optional<Pago> pago = pagosRepository.findById(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }    
    
    
    @Operation(summary = "Consultar el estatus de un pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatus del pago"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrada")
    })
    @GetMapping("/pagos/{id}/estatus")
    public ResponseEntity<EstatusPago> consultarEstatusPago(@PathVariable Integer id) {
        Optional<Pago> pago = pagosRepository.findById(id);
        if (pago.isPresent()) {
            return ResponseEntity.ok(pago.get().getEstatus());
        }
        return ResponseEntity.notFound().build();
    }    
    
    @Operation(summary = "Consultar el emisor de un pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Emisor del pago"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/pagos/{id}/emisor")
    public ResponseEntity<EmisorPago> consultarEmisorPago(@PathVariable Integer id) {
        Optional<Pago> pago = pagosRepository.findById(id);
        if (pago.isPresent()) {
            return ResponseEntity.ok(pago.get().getEmisor());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Consultar el receptor de un pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receptor del pago"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/pagos/{id}/receptor")
    public ResponseEntity<ReceptorPago> consultarReceptorPago(@PathVariable Integer id) {
        Optional<Pago> pago = pagosRepository.findById(id);
        if (pago.isPresent()) {
            return ResponseEntity.ok(pago.get().getReceptor());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Cambiar el estatus de un pago y notificar por Kafka")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatus del pago actualizado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/pagos/{id}/estatus")
    public ResponseEntity<String> cambiarEstatusPago(@PathVariable Integer id, @RequestParam EstatusPago.NombreEstatus nuevoEstatus) {
        Optional<Pago> pago = pagosRepository.findById(id);
        if (pago.isPresent()) {
            Pago t = pago.get();
            EstatusPago estatusNuevo = estatusRepository.findByNombreEstatus(nuevoEstatus)
                    .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
            
            t.setEstatus(estatusNuevo);
            pagosRepository.save(t);
            kafkaProducerService.notificarCambioEstatus(t);
            return ResponseEntity.ok("Estatus del pago actualizado y notificado.");
        }
        return ResponseEntity.notFound().build();
    }
}
