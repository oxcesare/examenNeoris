package com.mx.neoris.banco.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class CalMath {

	public BigDecimal divide(BigDecimal a, BigDecimal b) {
		return a.divide(b, MathContext.DECIMAL32);
	}

	public BigDecimal suma(BigDecimal a, BigDecimal b) {
		return a.add(b, MathContext.DECIMAL32);
	}

	public BigDecimal resta(BigDecimal a, BigDecimal b) {
		return a.add(b.multiply(BigDecimal.valueOf(-1), MathContext.DECIMAL32), MathContext.DECIMAL32);
	}

	public BigDecimal multiplica(BigDecimal a, BigDecimal b) {
		return a.multiply(b, MathContext.DECIMAL32);
	}

	public BigDecimal redondear(BigDecimal a) {
		return a.setScale(2, RoundingMode.HALF_EVEN);
	}

}
