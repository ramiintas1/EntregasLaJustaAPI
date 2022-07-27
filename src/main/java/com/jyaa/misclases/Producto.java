package com.jyaa.misclases;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTO")
public class Producto {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCTO_ID")	
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PESO")
	private float peso;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="VALOR")
	private float valor;
	
	@Column(name="N_FRIO")
	private boolean frio;
	
	private boolean borrado;
	
	private String imagen;
	
	@Column(name="MARCA")
	private String marca;
	
	@Column(name="PRODUCTOR")
	private String productor;
	
	@Column(name="CATEGORIA")
	private String categoria;
	
	@Column(name="STOCK")
	private int stock;	 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;
	
	public Producto() {
		this.borrado = false;
	}

	public Producto(String nombre, float peso, String descripcion, float valor, boolean frio, String imagen,
			String marca, String productor, String categoria, int stock) {
		//super();
		this.nombre = nombre;
		this.peso = peso;
		this.descripcion = descripcion;
		this.valor = valor;
		this.frio = frio;
		this.imagen = imagen;
		this.marca = marca;
		this.productor = productor;
		this.categoria = categoria;
		this.stock = stock;
		this.borrado = false;
	}

	public boolean isFrio() {
		return frio;
	}

	public void setFrio(boolean frio) {
		this.frio = frio;
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

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}	
	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", peso=" + peso + ", descripcion=" + descripcion
				+ ", valor=" + valor + ", frio=" + frio + ", imagen=" + imagen + ", marca=" + marca + ", productor="
				+ productor + ", categoria=" + categoria + ", stock=" + stock + "]";
	}
}
