package com.bancobase.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancobase.demo.pagos.model.Pago;

public interface PagosRepository extends JpaRepository<Pago, Integer> {
}