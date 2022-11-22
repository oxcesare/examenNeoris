package com.mx.neoris.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.neoris.banco.models.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Long> {

}
