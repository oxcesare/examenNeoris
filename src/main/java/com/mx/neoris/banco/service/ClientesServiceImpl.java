package com.mx.neoris.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.neoris.banco.dto.UsuarioDTO;
import com.mx.neoris.banco.models.Cliente;
import com.mx.neoris.banco.repository.IClienteRepository;

@Service("clientesService")
public class ClientesServiceImpl  implements IClientesService{

	@Autowired
	private IClienteRepository clienteRepository;

	/**
	 * Definir el metodo que guardara los clientes
	 */

	@Override
	public Cliente saveCliente(UsuarioDTO usuarioDTO) {

		Cliente cliente = new Cliente();
		cliente.setNombre(usuarioDTO.getNombre());
		cliente.setDireccion(usuarioDTO.getDireccion());
		cliente.setEdad(usuarioDTO.getEdad());
		cliente.setEstado(usuarioDTO.isEstado());
		cliente.setPassword(usuarioDTO.getContrasena());
		cliente.setTelefono(usuarioDTO.getTelefono());
		cliente.setGenero(usuarioDTO.getGenero());
		
		Cliente saveCliente = clienteRepository.save(cliente);
		saveCliente.setClienteId(saveCliente.getIdentificacion());
		
		return saveCliente;

	}

}
