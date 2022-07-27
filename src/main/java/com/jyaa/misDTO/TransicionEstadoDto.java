package com.jyaa.misDTO;

public class TransicionEstadoDto {
	
	private Long id;
	private String actual;
	private String fecha;
	private String motivo;
	private long entrega;
	
	public TransicionEstadoDto(){
		
	}

	public TransicionEstadoDto(Long id, String actual, String fecha, String motivo, Long entrega) {
		this.id = id;
		this.actual = actual;
		this.fecha = fecha;
		this.motivo = motivo;
		this.entrega = entrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public long getEntrega() {
		return entrega;
	}

	public void setEntrega(long entrega) {
		this.entrega = entrega;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
