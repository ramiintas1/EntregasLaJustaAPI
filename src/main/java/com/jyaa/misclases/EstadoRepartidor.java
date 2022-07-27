package com.jyaa.misclases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ESTADO_REPARTIDOR")
public class EstadoRepartidor {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ESTADOR_ID")	
	private long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	private boolean borrado;
	
	@OneToMany(mappedBy= "estado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Usuario> usuarios = new ArrayList<>();
	
	public EstadoRepartidor() {
		this.borrado = false;
	}

	public EstadoRepartidor(String nombre) {
		this.nombre = nombre;
		this.borrado = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "EstadoRepartidor [id=" + id + ", nombre=" + nombre + "]";
	}

}
