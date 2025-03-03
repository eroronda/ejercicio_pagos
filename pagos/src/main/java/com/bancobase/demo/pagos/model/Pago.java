package com.bancobase.demo.pagos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPago;

	@ManyToOne
	@JoinColumn(name = "id_estatus", nullable = false)
	@JsonIgnore  
	private EstatusPago estatus;

	@ManyToOne
	@JoinColumn(name = "id_emisor_pago", nullable = false)
	@JsonIgnore  
	private EmisorPago emisor;

	@ManyToOne
	@JoinColumn(name = "id_receptor_pago", nullable = false)
	 @JsonIgnore  
	private ReceptorPago receptor;

	private Integer cantidadProductos;
	private BigDecimal monto;
	private String concepto;
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public EstatusPago getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusPago estatus) {
		this.estatus = estatus;
	}

	public EmisorPago getEmisor() {
		return emisor;
	}

	public void setEmisor(EmisorPago emisor) {
		this.emisor = emisor;
	}

	public ReceptorPago getReceptor() {
		return receptor;
	}

	public void setReceptor(ReceptorPago receptor) {
		this.receptor = receptor;
	}

	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getIdEstatus() {
	    return estatus != null ? estatus.getIdEstatus() : null;
	}

	public Integer getIdEmisorPago() {
	    return emisor != null ? emisor.getId() : null;
	}

	public Integer getIdReceptorPago() {
	    return receptor != null ? receptor.getId() : null;
	}

}