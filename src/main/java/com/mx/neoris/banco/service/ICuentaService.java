package com.mx.neoris.banco.service;

import com.mx.neoris.banco.dto.CuentaDTO;
import com.mx.neoris.banco.models.Cuenta;

public interface ICuentaService {
	
	public Cuenta saveCuenta (CuentaDTO cuentaDTO);
	
	
}
