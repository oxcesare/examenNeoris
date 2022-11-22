package com.mx.neoris.banco.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.neoris.banco.dto.CuentaDTO;
import com.mx.neoris.banco.dto.MovimientoDTO;
import com.mx.neoris.banco.dto.MovimientoReporteDTO;
import com.mx.neoris.banco.dto.MovimientoResponseDTO;
import com.mx.neoris.banco.dto.ReporteDTO;
import com.mx.neoris.banco.models.Cuenta;
import com.mx.neoris.banco.service.CuentaServiceImpl;
import com.mx.neoris.banco.service.MovimientosServiceImpl;

@RestController
@RequestMapping("/api/banco/v1.0")
public class BancoController {

	@Autowired
	@Qualifier("cuentaService")
	private CuentaServiceImpl cuentaServiceImpl;

	@Autowired
	@Qualifier("movimientosService")
	private MovimientosServiceImpl movimientosServiceImpl;

	@GetMapping("/version")
	public ResponseEntity<String> inicial() {
		return new ResponseEntity<String>("Examen - César Ricardo Alducin Ruiz - Neoris Mexico" + "22 Noviembre 2022",
				HttpStatus.OK);
	}

	@PostMapping("/cuentas")
	public ResponseEntity<Cuenta> crearCuentaPorUsuario(@RequestBody CuentaDTO cuentaDTO) {
		return new ResponseEntity<Cuenta>(cuentaServiceImpl.saveCuenta(cuentaDTO), HttpStatus.CREATED);
	}

	@PutMapping("/movimientos")
	public ResponseEntity<MovimientoResponseDTO> crearMovimientoPorCuenta(@RequestBody MovimientoDTO movimientoDTO)
			throws ParseException {
		return new ResponseEntity<MovimientoResponseDTO>(movimientosServiceImpl.registrarMovimiento(movimientoDTO),
				HttpStatus.OK);
	}

	@GetMapping("/reporte")
	public ResponseEntity<List<MovimientoReporteDTO>> inicial(@RequestBody ReporteDTO reporteDTO)
			throws ParseException {
		return new ResponseEntity<List<MovimientoReporteDTO>>(movimientosServiceImpl.obtenerReporte(
				reporteDTO.getFechaInicial(), reporteDTO.getFechaFinal(), reporteDTO.getNumCuenta()), HttpStatus.OK);
	}

}
