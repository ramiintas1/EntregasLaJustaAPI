package com.jyaa.misclases;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="HISTORICO_ESTADO")
public class TransicionEstado {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRANSICION_ID")	
	private long id;
	
	//@OneToOne(cascade = {CascadeType.ALL})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ESTADOE_ID")	//cada transicion tiene un estado asignado (actual) 
	private EstadoEntrega actual;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="MOTIVO")
	private String motivo;
	
	private boolean borrado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTREGA_ID")	//cada transicion tiene una entrega asinada
	private Entrega entrega;
	
	
	public TransicionEstado() {
		this.borrado = false;
	}

	public TransicionEstado(String fecha, String motivo) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.borrado = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstadoEntrega getActual() {
		return actual;
	}

	public void setActual(EstadoEntrega actual) {
		this.actual = actual;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "TransicionEstado [id=" + id + ", actual=" + actual +"]";
	}

}
