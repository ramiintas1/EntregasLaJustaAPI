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
@Table(name="ROL")
public class Rol {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROL_ID")	
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	private boolean borrado;
	
	@OneToMany(mappedBy= "rol", cascade = CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Rol() {
		this.borrado = false;
	}
	
	public Rol(String nombre) {
		//super();
		this.nombre = nombre;
		this.borrado = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

}
