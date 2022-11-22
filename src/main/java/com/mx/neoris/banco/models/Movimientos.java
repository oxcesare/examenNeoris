package com.mx.neoris.banco.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movimientos")
public class Movimientos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "numCuenta")
	private Long numCuenta;

	@Column(name = "tipoCuenta")
	private String tipo;

	@Column(name = "estado")
	private boolean estado;
	
	@Column(name = "fechaMovimiento")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "tipoMovimiento")
	private String tipoMovmiento;

	@Column(name = "cantidad")
	private BigDecimal cantidad;

	@Column(name = "saldoInicial")
	private BigDecimal saldo;

	@Column(name = "saldoDisponible")
	private BigDecimal saldoDisponible;

	public Movimientos() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovmiento() {
		return tipoMovmiento;
	}

	public void setTipoMovmiento(String tipoMovmiento) {
		this.tipoMovmiento = tipoMovmiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimientos other = (Movimientos) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Movimientos [id=" + id + ", fecha=" + fecha + ", tipoMovmiento=" + tipoMovmiento + ", tipo=" + tipo
				+ ", saldo=" + saldo + "]";
	}

}
