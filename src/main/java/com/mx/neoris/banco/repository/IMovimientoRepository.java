package com.mx.neoris.banco.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.neoris.banco.models.Movimientos;

public interface IMovimientoRepository extends JpaRepository<Movimientos, Long> {

	@Query("SELECT u.cantidad FROM Movimientos u where u.numCuenta=:numCuenta and u.tipoMovmiento='Retiro'")
	public List<BigDecimal> findRetirosbyNumCuenta(Long numCuenta);

	@Query("SELECT u.cantidad FROM Movimientos u where u.numCuenta=:numCuenta and u.tipoMovmiento='Abono'")
	public List<BigDecimal> findAbonosbyNumCuenta(Long numCuenta);

	@Query(value = " SELECT u FROM Movimientos u WHERE u.fecha BETWEEN ?1 and ?2")
	List<Movimientos> findAllByDate(Date startDate, Date endDate);
	

}
