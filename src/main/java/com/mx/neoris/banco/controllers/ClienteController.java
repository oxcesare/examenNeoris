package com.mx.neoris.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.neoris.banco.dto.UsuarioDTO;
import com.mx.neoris.banco.models.Cliente;
import com.mx.neoris.banco.service.ClientesServiceImpl;

@RestController
@RequestMapping("/api/banco/v1.0")
public class ClienteController {

	@Autowired
	@Qualifier("clientesService")
	private ClientesServiceImpl clientesServicesImpl;

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> crearClientes(@RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<Cliente>(clientesServicesImpl.saveCliente(usuarioDTO), HttpStatus.CREATED);
	}

}
