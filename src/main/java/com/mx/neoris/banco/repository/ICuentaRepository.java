package com.mx.neoris.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.neoris.banco.models.Cuenta;

public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {

}
