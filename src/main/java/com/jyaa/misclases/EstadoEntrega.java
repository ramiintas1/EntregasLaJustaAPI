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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ESTADO_ENTREGA")
public class EstadoEntrega {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ESTADOE_ID")	
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	private boolean borrado;
	
	@OneToMany(mappedBy= "actual", cascade = CascadeType.ALL)
	private List<TransicionEstado> actuales = new ArrayList<>();	
	
	public EstadoEntrega() {
		this.borrado = false;
	}

	public EstadoEntrega(String nombre) {
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

	public List<TransicionEstado> getActuales() {
		return actuales;
	}

	public void setActuales(List<TransicionEstado> actuales) {
		this.actuales = actuales;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "EstadoEntrega [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
