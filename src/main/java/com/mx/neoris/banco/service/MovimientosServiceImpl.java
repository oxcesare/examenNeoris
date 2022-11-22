package com.mx.neoris.banco.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mx.neoris.banco.dto.MovimientoDTO;
import com.mx.neoris.banco.dto.MovimientoReporteDTO;
import com.mx.neoris.banco.dto.MovimientoResponseDTO;
import com.mx.neoris.banco.exception.SaldoNoDisponible;
import com.mx.neoris.banco.models.Cuenta;
import com.mx.neoris.banco.models.Movimientos;
import com.mx.neoris.banco.repository.ICuentaRepository;
import com.mx.neoris.banco.repository.IMovimientoRepository;
import com.mx.neoris.banco.utils.Operaciones;
import com.mx.neoris.banco.utils.Utilerias;

@Service("movimientosService")
public class MovimientosServiceImpl implements IMovimientosService {

	private static final Logger log = LoggerFactory.getLogger(MovimientosServiceImpl.class);

	@Autowired
	private ICuentaRepository cuentaRepository;

	@Autowired
	private IMovimientoRepository iMovimientoRepository;

	@Autowired
	private Operaciones operaciones;

	@Override
	public Optional<Cuenta> getCuenta(Long numCuenta) {
		Optional<Cuenta> cuenta = cuentaRepository.findById(numCuenta);
		return cuenta;
	}

	@Override
	public MovimientoResponseDTO registrarMovimiento(MovimientoDTO movimientoDTO) throws ParseException {

		log.info("Ejecucción Metodo - registrarMovimiento");

		Optional<Cuenta> cuenta = getCuenta(movimientoDTO.getNumCuenta());

		MovimientoResponseDTO movimientoResponse = new MovimientoResponseDTO();

		Movimientos movimientos = new Movimientos();

		BigDecimal retiros = BigDecimal.ZERO;

		BigDecimal abonos = BigDecimal.ZERO;

		BigDecimal saldoInicial = BigDecimal.ZERO;

		BigDecimal saldoDisponible = BigDecimal.ZERO;

		if (cuenta.isPresent()) {

			retiros = getRetirosPorCuenta(movimientoDTO.getNumCuenta());

			abonos = getAbonosPorCuenta(movimientoDTO.getNumCuenta());

			saldoInicial = cuenta.get().getSaldoInicial();

			saldoInicial = operaciones.getSaldoDisponibleAbonos(saldoInicial, abonos);

			saldoInicial = operaciones.getSaldoDisponibleRetiros(saldoInicial, retiros);

			if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
				throw new SaldoNoDisponible("Saldo No Disponible");
			} else {

				if (movimientoDTO.getTipoMovimiento().equals("Abono")) {
					saldoInicial = operaciones.getSaldoDisponibleAbono(saldoInicial, movimientoDTO.getCantidad());
				} else {
					saldoInicial = operaciones.getSaldoDisponibleRetiro(saldoInicial, movimientoDTO.getCantidad());

					if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
						throw new SaldoNoDisponible("Saldo No Disponible");
					}
				}
			}

			saldoDisponible = saldoInicial;
			movimientos.setCantidad(movimientoDTO.getCantidad());
			movimientos.setEstado(movimientoDTO.isEstado());
			movimientos.setFecha(Utilerias.formatLocaDateTime(movimientoDTO.getFecha()));
			movimientos.setSaldoDisponible(saldoDisponible);
			movimientos.setTipo(cuenta.get().getTipoCuenta());
			movimientos.setSaldo(cuenta.get().getSaldoInicial());
			movimientos.setNumCuenta(movimientoDTO.getNumCuenta());
			movimientos.setTipoMovmiento(movimientoDTO.getTipoMovimiento());

			iMovimientoRepository.save(movimientos);

			movimientoResponse.setMensaje("Movimento registrado existosamente");
			movimientoResponse.setNumCuenta(movimientoDTO.getNumCuenta());
			movimientoResponse.setSaldoDisponible(saldoDisponible);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Cuenta no existente", movimientoDTO.getNumCuenta()));
		}

		return movimientoResponse;
	}

	/**
	 * Metodo para sumar todos los retiros
	 */

	private BigDecimal getRetirosPorCuenta(Long numCuenta) {

		BigDecimal retiros = BigDecimal.ZERO;

		List<BigDecimal> lsRetiros = iMovimientoRepository.findRetirosbyNumCuenta(numCuenta);

		for (BigDecimal bigDecimal : lsRetiros) {
			retiros = retiros.add(bigDecimal);
		}

		return retiros;
	}

	/**
	 * Metodo para sumar todos los Abonos
	 */

	private BigDecimal getAbonosPorCuenta(Long numCuenta) {

		BigDecimal abonos = BigDecimal.ZERO;

		List<BigDecimal> lsAbonos = iMovimientoRepository.findAbonosbyNumCuenta(numCuenta);

		for (BigDecimal bigDecimal : lsAbonos) {
			abonos = abonos.add(bigDecimal);
		}

		return abonos;
	}

	@Override
	public List<MovimientoReporteDTO> obtenerReporte(String fechaInicial, String fechaFinal, Long numCuenta)
			throws ParseException {

		List<MovimientoReporteDTO> resultado = new ArrayList<>();
		
		Optional<Cuenta> validarCuenta = getCuenta(numCuenta);
		
		if(validarCuenta.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Numero de cuenta no Existente",
							numCuenta));		
		}	
		
		if(fechaInicial.equals("")||fechaFinal.equals("")|| numCuenta==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("Datos de Entrada incompletos",
							numCuenta));
		}

		try {
			
			List<Movimientos> reporte = iMovimientoRepository.findAllByDate(Utilerias.formatLocaDateTime(fechaInicial),
					Utilerias.formatLocaDateTime(fechaFinal));
			
			if(reporte.size()>0) {
				
				log.info("Size()", reporte.size());

				resultado = reporte.stream().filter(c -> c.getNumCuenta().equals(numCuenta)).map(temp -> {

					Optional<Cuenta> cuenta = getCuenta(numCuenta);
					MovimientoReporteDTO dto = new MovimientoReporteDTO();
					dto.setFecha(Utilerias.formatLocaDateTimeSinT(temp.getFecha()));
					dto.setCliente(cuenta.get().getCliente().getNombre());
					dto.setNumCuenta(temp.getNumCuenta());
					dto.setTipo(temp.getTipo());
					dto.setSaldoInicial(temp.getSaldo());
					dto.setEstado(temp.isEstado());
					dto.setMovimiento(temp.getCantidad());
					dto.setSaldoDisponible(temp.getSaldoDisponible());
					return dto;
				}).collect(Collectors.toList());
				
				return resultado;
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontraron registros con la informacion proporcionada",
								numCuenta));
			}
		} catch (DataAccessException e) {				
				log.error("Error",e.getMessage());
				throw new ResponseStatusException(HttpStatus.SEE_OTHER, String.format("Error Base de Datos",
						numCuenta));
		}
		
	}

}
