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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ENTREGA")
public class Entrega {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ENTREGA_ID")
	private Long id;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="MOTIVO")
	private String motivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ZONA_ID")	//cada entrega tiene una zona asignada
	private Zona zona;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID")	//cada entrega tiene un usuario (repartidor) asignado
	private Usuario repartidor;
	
	private boolean borrado;
	
	private long idJusta;
	
	@OneToMany(mappedBy= "entrega", cascade = CascadeType.ALL)
	private List<TransicionEstado> estados = new ArrayList<>();	//cada entrega tiene un historico de cambio de estado
	
	@OneToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;
	
	public Entrega() {
		this.borrado = false;
	}
	//falta estados
	public Entrega(String fecha, String motivo, Zona zona, Usuario repartidor, Pedido pedido) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.zona = zona;
		this.repartidor = repartidor;
		this.pedido = pedido;
		this.borrado = false;
	}
	
	public Entrega(String fecha, String motivo, Zona zona, Usuario repartidor) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.zona = zona;
		this.repartidor = repartidor;
		this.borrado = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TransicionEstado> getEstados() {
		return estados;
	}

	public void setEstados(List<TransicionEstado> estados) {
		this.estados = estados;
	}
	
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Usuario getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Usuario repartidor) {
		this.repartidor = repartidor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
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
	
	public boolean isBorrado() {
		return borrado;
	}
	
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	public long getIdJusta() {
		return idJusta;
	}
	
	public void setIdJusta(long idJusta) {
		this.idJusta = idJusta;
	}
	
}
