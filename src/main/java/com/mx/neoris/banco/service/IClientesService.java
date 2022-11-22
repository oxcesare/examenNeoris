package com.mx.neoris.banco.service;

import com.mx.neoris.banco.dto.UsuarioDTO;
import com.mx.neoris.banco.models.Cliente;

public interface IClientesService {

	public Cliente saveCliente(UsuarioDTO usuarioDTO);

}
