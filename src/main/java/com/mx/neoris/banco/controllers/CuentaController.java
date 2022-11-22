package com.mx.neoris.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.neoris.banco.dto.CuentaDTO;
import com.mx.neoris.banco.models.Cuenta;
import com.mx.neoris.banco.service.CuentaServiceImpl;

@RestController
@RequestMapping("/api/banco/v1.0")
public class CuentaController {

	@Autowired
	@Qualifier("cuentaService")
	private CuentaServiceImpl cuentaServiceImpl;

	@PostMapping("/cuentas")
	public ResponseEntity<Cuenta> crearCuentaPorUsuario(@RequestBody CuentaDTO cuentaDTO) {
		return new ResponseEntity<Cuenta>(cuentaServiceImpl.saveCuenta(cuentaDTO), HttpStatus.CREATED);
	}
}
