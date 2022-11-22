package com.mx.neoris.banco.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.mx.neoris.banco.dto.MovimientoDTO;
import com.mx.neoris.banco.dto.MovimientoReporteDTO;
import com.mx.neoris.banco.dto.MovimientoResponseDTO;
import com.mx.neoris.banco.models.Cuenta;

public interface IMovimientosService {
	
	public Optional<Cuenta>getCuenta(Long numCuenta);
	
	public MovimientoResponseDTO registrarMovimiento(MovimientoDTO movimientoDTO) throws ParseException;
	
	public List<MovimientoReporteDTO> obtenerReporte(String fechaInicial, String fechaFinal, Long numCuenta) throws ParseException;

}
