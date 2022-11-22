package com.mx.neoris.banco.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.neoris.banco.controllers.ClienteController;
import com.mx.neoris.banco.dto.UsuarioDTO;
import com.mx.neoris.banco.models.Cliente;
import com.mx.neoris.banco.service.IClientesService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IClientesService clientesService;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	void testGuardar() throws Exception {
		// Given
		Cliente cliente = new Cliente();
		cliente.setClienteId(1L);
		cliente.setDireccion("Xalapa");
		cliente.setEdad(35);
		cliente.setEstado(true);
		cliente.setGenero("Masculino");
		cliente.setIdentificacion(1987L);
		cliente.setNombre("Cesar Ricardo Alducin Ruiz");
		cliente.setPassword("123456789");
		cliente.setTelefono("5575121799");

		UsuarioDTO dto = new UsuarioDTO();
		dto.setContrasena("12345");
		dto.setDireccion("Xalapa");
		dto.setEdad(35);
		dto.setNombre("Cesar Ricardo Alducin Ruiz");

		// when
		mvc.perform(post("/api/banco/v1.0/clientes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cliente)))
				// Then
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.clienteId", is(1L))).andExpect(jsonPath("$.password", is("12345")))
				.andExpect(jsonPath("$.estado", is(true)));

		verify(clientesService).saveCliente(dto);

	}

}
