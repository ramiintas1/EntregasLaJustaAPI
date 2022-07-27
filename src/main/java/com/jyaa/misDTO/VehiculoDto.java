package com.jyaa.misDTO;

public class VehiculoDto {
	
	private Long id;
	private String tipo;
	private int pesoMax;
	private boolean combustible;
	
	public VehiculoDto() {
		
	}

	public VehiculoDto(String tipo, int pesoMax, boolean combustible) {
		this.tipo = tipo;
		this.pesoMax = pesoMax;
		this.combustible = combustible;
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

	public int getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}

	public boolean isCombustible() {
		return combustible;
	}

	public void setCombustible(boolean combustible) {
		this.combustible = combustible;
	}

}
