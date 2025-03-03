package com.bancobase.demo.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancobase.demo.pagos.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
