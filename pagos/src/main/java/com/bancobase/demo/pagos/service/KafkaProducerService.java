package com.bancobase.demo.pagos.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bancobase.demo.pagos.model.Pago;

@Service
public class KafkaProducerService {

	@Value("${kafka.topic.name}")
	private String topicName;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void notificarCambioEstatus(Pago transaccion) {
		String mensaje = "Pago ID: " + transaccion.getIdPago() + " ha cambiado su estatus a: "
				+ transaccion.getEstatus().getNombreEstatus();

		kafkaTemplate.send(topicName, mensaje);
	}
}
