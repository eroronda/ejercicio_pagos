package com.bancobase.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancobase.demo.pagos.model.EmisorPago;

public interface EmisorPagoRepository extends JpaRepository<EmisorPago, Integer> {
}