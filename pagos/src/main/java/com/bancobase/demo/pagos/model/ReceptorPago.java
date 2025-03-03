package com.bancobase.demo.pagos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "receptores_pago")
public class ReceptorPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private RolPago rol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public RolPago getRol() {
		return rol;
	}

	public void setRol(RolPago rol) {
		this.rol = rol;
	}
}