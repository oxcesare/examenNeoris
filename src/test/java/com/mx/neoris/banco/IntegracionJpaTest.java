package com.mx.neoris.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mx.neoris.banco.models.Cliente;
import com.mx.neoris.banco.models.Cuenta;
import com.mx.neoris.banco.models.Movimientos;
import com.mx.neoris.banco.repository.IClienteRepository;
import com.mx.neoris.banco.repository.ICuentaRepository;
import com.mx.neoris.banco.repository.IMovimientoRepository;

@DataJpaTest
public class IntegracionJpaTest {

	@Autowired
	private IClienteRepository clientesService;

	@Autowired
	private ICuentaRepository cuentaRepository;
	
	@Autowired
	private IMovimientoRepository iMovimientoRepository;

	@Test
	@DisplayName("Test Save Cuenta")
	void testSaveCuenta() {

		// Given
		Cuenta cuenta = new Cuenta();
		Cliente cliente = new Cliente();
		cliente.setNombre("Cesar Ricardo Alducin Ruiz");
		cuenta.setCliente(cliente);
		cuenta.setEstado(true);
		cuenta.setNumCuenta(1L);
		cuenta.setSaldoInicial(new BigDecimal("1200.00"));

		// When
		Cuenta saveCuenta = cuentaRepository.save(cuenta);

		// Then
		assertEquals(new BigDecimal("1200.00"), saveCuenta.getSaldoInicial());
		assertEquals(1L, saveCuenta.getNumCuenta());

	}

	@Test
	@DisplayName("Test Save Cliente")
	void testSaveCliente() {

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

		// When
		Cliente saveCliente = clientesService.save(cliente);

		// Then
		assertEquals("Cesar Ricardo Alducin Ruiz", saveCliente.getNombre());
		assertEquals("5575121799", saveCliente.getTelefono());

	}
	
	@Test
	@DisplayName("Test Save Movimiento")
	void testSaveMovimiento() {

		// Given
		Movimientos movimientos = new Movimientos();
		movimientos.setCantidad(new BigDecimal("1200.00"));
		movimientos.setEstado(true);
		movimientos.setFecha(new Date());
		movimientos.setNumCuenta(1L);
		movimientos.setSaldo(new BigDecimal("170.00"));
		movimientos.setTipo("Corriente");
		movimientos.setTipoMovmiento("Retiro");

		// When
		Movimientos saveMovimientos = iMovimientoRepository.save(movimientos);

		// Then
		assertEquals(new BigDecimal("1200.00"), saveMovimientos.getCantidad());
		assertEquals("Retiro", saveMovimientos.getTipoMovmiento());

	}
	
	
}
