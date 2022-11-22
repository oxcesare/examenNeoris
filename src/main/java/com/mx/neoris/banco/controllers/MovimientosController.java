package com.mx.neoris.banco.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.neoris.banco.dto.MovimientoDTO;
import com.mx.neoris.banco.dto.MovimientoResponseDTO;
import com.mx.neoris.banco.service.MovimientosServiceImpl;

@RestController
@RequestMapping("/api/banco/v1.0")
public class MovimientosController {
	
	@Autowired
	@Qualifier("movimientosService")
	private MovimientosServiceImpl movimientosServiceImpl;
		
	@PutMapping("/movimientos")
	public ResponseEntity<MovimientoResponseDTO> crearMovimientoPorCuenta(@RequestBody MovimientoDTO movimientoDTO)
			throws ParseException {
		return new ResponseEntity<MovimientoResponseDTO>(movimientosServiceImpl.registrarMovimiento(movimientoDTO),
				HttpStatus.OK);
	}

}
