package com.mx.neoris.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.neoris.banco.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
	

}
