package com.bancobase.demo.pagos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancobase.demo.pagos.model.EstatusPago;

public interface EstatusPagoRepository extends JpaRepository<EstatusPago, Integer> {
	 Optional<EstatusPago> findByNombreEstatus(EstatusPago.NombreEstatus nombreEstatus);
}