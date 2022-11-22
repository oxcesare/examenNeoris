package com.mx.neoris.banco.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.neoris.banco.exception.SaldoNoDisponible;

@Component
public class Operaciones {
	
	private static final Logger log = LoggerFactory.getLogger(Operaciones.class);

	@Autowired
	CalMath calMath;

	public BigDecimal getSaldoDisponibleAbonos(BigDecimal saldoInicial, BigDecimal abonos) {
		return calMath.redondear(calMath.suma(saldoInicial, abonos));
	}

	public BigDecimal getSaldoDisponibleRetiros(BigDecimal saldoInicial, BigDecimal retiros) {

		try {
			return calMath.redondear(calMath.resta(saldoInicial, retiros));
		} catch (SaldoNoDisponible e) {
			log.error("Error al Calcular Retiros", e.getMessage());
			throw new SaldoNoDisponible("Saldo No Disponible");			
		}
	}
	

	public BigDecimal getSaldoDisponibleRetiro(BigDecimal saldoInicial, BigDecimal retiro) {

		try {
			return calMath.redondear(calMath.resta(saldoInicial, retiro));
		} catch (SaldoNoDisponible e) {
			log.error("Error al Calcular Retiros", e.getMessage());
			throw new SaldoNoDisponible("Saldo No Disponible");			
		}
	}
	
	
	public BigDecimal getSaldoDisponibleAbono(BigDecimal saldoInicial, BigDecimal abono) {

		try {
			return calMath.redondear(calMath.suma(saldoInicial, abono));
		} catch (SaldoNoDisponible e) {
			log.error("Error al Calcular Retiros", e.getMessage());
			throw new SaldoNoDisponible("Saldo No Disponible");			
		}
	}
	
	
	
}
