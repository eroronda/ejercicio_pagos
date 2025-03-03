package com.bancobase.demo.pagos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles_pago")
public class RolPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;

	public enum NombreRol {
		EMISOR, RECEPTOR
	}

	@Enumerated(EnumType.STRING)
	private NombreRol nombreRol;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public NombreRol getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(NombreRol nombreRol) {
		this.nombreRol = nombreRol;
	}
}