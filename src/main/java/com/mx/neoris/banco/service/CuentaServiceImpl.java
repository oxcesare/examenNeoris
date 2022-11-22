package com.mx.neoris.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.neoris.banco.dto.CuentaDTO;
import com.mx.neoris.banco.models.Cliente;
import com.mx.neoris.banco.models.Cuenta;
import com.mx.neoris.banco.repository.IClienteRepository;
import com.mx.neoris.banco.repository.ICuentaRepository;

@Service("cuentaService")
public class CuentaServiceImpl implements ICuentaService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private ICuentaRepository cuentaRepository;

	@Override
	public Cuenta saveCuenta(CuentaDTO cuentaDTO) {

		Optional<Cliente> cliente = clienteRepository.findById(cuentaDTO.getClienteId());
		Cuenta cuenta = new Cuenta();
		Cuenta cuentaSave = new Cuenta();
		
		if (cliente.isPresent()) {
			cuenta.setCliente(cliente.get());
			cuenta.setEstado(cuentaDTO.isEstado());			
			cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
			cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());

			cuentaSave = cuentaRepository.save(cuenta);

		} else {

		}

		return cuentaSave;
	}

}
