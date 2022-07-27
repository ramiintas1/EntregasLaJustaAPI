package com.jyaa.misDTO;

public class EstadoEntregaDto {
	
	private Long id;
	private String nombre;
	
	public EstadoEntregaDto() {
		
	}

	public EstadoEntregaDto(Long id, String nombre) {
		super();
		this.id = id;
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
