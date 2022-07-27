package com.jyaa.misDTO;

public class EstadoRepartidorDto {

	private Long id;
	private String nombre;
	
	public EstadoRepartidorDto() {
		
	}

	public EstadoRepartidorDto(String nombre) {
		super();
		this.nombre = nombre;
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
	
}
