package com.jyaa.misDTO;

public class EntregaDto {
	

	private Long id;
	private String zona;
	private String dniRepartidor;
	private String estado;
	private String fecha;
	private String motivo;
	private Long pedido;
	
	public EntregaDto() {
		
	}

	public EntregaDto(Long id, String zona, String dniRepartidor, String fecha, String motivo, Long pedido) {
		this.id = id;
		this.zona = zona;
		this.dniRepartidor = dniRepartidor;
		this.fecha = fecha;
		this.motivo = motivo;
		this.pedido = pedido;
	}
	
	public EntregaDto(Long id, String zona, String dniRepartidor, String fecha, String motivo, Long pedido, String estado) {
		this.id = id;
		this.zona = zona;
		this.dniRepartidor = dniRepartidor;
		this.fecha = fecha;
		this.motivo = motivo;
		this.pedido = pedido;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZona() {
		return zona;
	}


	public void setZona(String zona) {
		this.zona = zona;
	}


	public String getDniRepartidor() {
		return dniRepartidor;
	}


	public void setDniRepartidor(String dniRepartidor) {
		this.dniRepartidor = dniRepartidor;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Long getPedido() {
		return pedido;
	}


	public void setPedido(Long pedido) {
		this.pedido = pedido;
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

}
