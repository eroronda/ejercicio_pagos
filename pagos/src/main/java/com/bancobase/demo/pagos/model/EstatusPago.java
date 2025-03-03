package com.bancobase.demo.pagos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estatus_pago")
public class EstatusPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstatus;

	public enum NombreEstatus {
		PENDIENTE, APROBADO, RECHAZADO
	}
	
	@Enumerated(EnumType.STRING)
	private NombreEstatus nombreEstatus;

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public NombreEstatus getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(NombreEstatus nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}
}