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
@Table(name="ZONA")
public class Zona {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ZONA_ID")	
	private Long id;
	
	@Column(name="BARRIO")
	private String barrio;
	
	@Column(name="LATITUD_INI")
	private Float desdeLat;
	
	@Column(name="LATITUD_FIN")
	private Float hastaLat;
	
	@Column(name="LONGUITUD_INI")
	private Float desdeLong;
	
	@Column(name="LONGUITUD_FIN")
	private Float hastaLong;
	
	private boolean borrado;
	
	@OneToMany(mappedBy= "zonaAsignada", cascade = CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<>();		//una zona puede tener varios usuarios(repartidores)
	
	@OneToMany(mappedBy= "zona", cascade = CascadeType.ALL)
	private List<Entrega> entregas = new ArrayList<>();	//una zona puede tener varias entregas

	
	public Zona() {
		this.borrado = false;
	}

	public Zona(String barrio, Float desdeLat, Float hastaLat, Float desdeLong, Float hastaLong) {
		//super();
		this.barrio = barrio;
		this.desdeLat = desdeLat;
		this.hastaLat = hastaLat;
		this.desdeLong = desdeLong;
		this.hastaLong = hastaLong;
		this.borrado = false;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Float getDesdeLat() {
		return desdeLat;
	}

	public void setDesdeLat(Float desdeLat) {
		this.desdeLat = desdeLat;
	}

	public Float getHastaLat() {
		return hastaLat;
	}

	public void setHastaLat(Float hastaLat) {
		this.hastaLat = hastaLat;
	}

	public Float getDesdeLong() {
		return desdeLong;
	}

	public void setDesdeLong(Float desdeLong) {
		this.desdeLong = desdeLong;
	}

	public Float getHastaLong() {
		return hastaLong;
	}

	public void setHastaLong(Float hastaLong) {
		this.hastaLong = hastaLong;
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
		return "Zona [id=" + id + ", barrio=" + barrio + "]";
	}
	
}
