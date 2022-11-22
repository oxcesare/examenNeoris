package com.mx.neoris.banco.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.neoris.banco.dto.MovimientoReporteDTO;
import com.mx.neoris.banco.dto.ReporteDTO;
import com.mx.neoris.banco.service.MovimientosServiceImpl;

@RestController
@RequestMapping("/api/banco/v1.0")
public class ReporteController {

	@Autowired
	@Qualifier("movimientosService")
	private MovimientosServiceImpl movimientosServiceImpl;

	@GetMapping("/reporte")
	public ResponseEntity<List<MovimientoReporteDTO>> inicial(@RequestBody ReporteDTO reporteDTO)
			throws ParseException {
		return new ResponseEntity<List<MovimientoReporteDTO>>(movimientosServiceImpl.obtenerReporte(
				reporteDTO.getFechaInicial(), reporteDTO.getFechaFinal(), reporteDTO.getNumCuenta()), HttpStatus.OK);
	}
}
