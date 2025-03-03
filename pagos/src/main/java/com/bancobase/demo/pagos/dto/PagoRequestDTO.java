package com.bancobase.demo.pagos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoRequestDTO {

	private Integer idEstatus;
	private Integer idEmisorPago;
	private Integer idReceptorPago;
	private Integer cantidadProductos;
	private BigDecimal monto;
	private String concepto;
	private LocalDateTime fechaCreacion;

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public Integer getIdEmisorPago() {
		return idEmisorPago;
	}

	public void setIdEmisorPago(Integer idEmisorPago) {
		this.idEmisorPago = idEmisorPago;
	}

	public Integer getIdReceptorPago() {
		return idReceptorPago;
	}

	public void setIdReceptorPago(Integer idReceptorPago) {
		this.idReceptorPago = idReceptorPago;
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
}
