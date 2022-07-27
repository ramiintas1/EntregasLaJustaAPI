package com.jyaa.misclases;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PEDIDO")
public class Pedido {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PEDIDO_ID")	
	private Long id;
	
	@Column(name="MONTO")
	private float monto;
 
	@Column(name="DNI_CLIENTE")
	private String dniCliente;
	
	@Column(name="DESCRIPCION")
	private String descripcion;

	@Column(name="CALLE")
	private String calle;

	@Column(name="ENTRECALLES")
	private String entreCalles;
	
	@Column(name="NUMERO")
	private String numero;
	
	@Column(name="DEPARTAMENTO")
	private String departamento;

	@Column(name="LATITUD_P")
	private Float latitud;
	
	@Column(name="LONGUITUD_P")
	private Float longitud;
	
	@Column(name="NOMBRECLIENTE")
	private String nombreCliente;

	private boolean borrado;
	
	private long idCliente;
	
	private long idJusta;
	
	@OneToMany(mappedBy= "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>();	
	
	@OneToOne(optional = true, mappedBy="pedido")
	@JoinColumn(name = "ENTREGA_ID")
	private Entrega entrega;
	
	public Pedido() {
		this.borrado = false;
	}

	public Pedido(float monto, String dniCliente, Usuario repartidorId, String descripcion) {
		this.monto = monto;
		this.dniCliente = dniCliente;
		this.descripcion = descripcion;
		this.borrado = false;
	}
	
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getEntreCalles() {
		return entreCalles;
	}

	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdJusta() {
		return idJusta;
	}

	public void setIdJusta(long idJusta) {
		this.idJusta = idJusta;
	}
	
}