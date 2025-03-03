package com.bancobase.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancobase.demo.pagos.model.ReceptorPago;

public interface ReceptorPagoRepository extends JpaRepository<ReceptorPago, Integer> {
}