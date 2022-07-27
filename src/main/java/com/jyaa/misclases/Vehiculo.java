package com.jyaa.misclases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VEHICULO")
public class Vehiculo {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VEHICULO_ID")	
	private Long id;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="PESO_MAX")
	private Integer pesoMaximo;
	
	@Column(name="COBUSTIBLE")
	private boolean combustible;
	
	private boolean borrado;
	
	@OneToMany(mappedBy= "vehiculo", cascade = CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<>();		//un tipo de vehiculo puede tenerlo varios usuarios
	
	public Vehiculo() {
		this.borrado = false;
	}


	public Vehiculo(String tipo, Integer pesoMaximo, boolean combustible) {
		//super();
		this.tipo = tipo;
		this.pesoMaximo = pesoMaximo;
		this.combustible = combustible;
		this.borrado = false;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(Integer pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public boolean isCombustible() {
		return combustible;
	}

	public void setCombustible(boolean combustible) {
		this.combustible = combustible;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isBorrado() {
		return borrado;
	}


	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", tipo=" + tipo + ", pesoMaximo=" + pesoMaximo + ", combustible=" + combustible
				+ "]";
	}	

}
