package com.jyaa.misDTO;

public class ProductoDto {

	private long id;
	private String nombre;
	private String descripcion;
	private String marca;
	private String categoria;
	private String productor;
	private float valor;
	
	private float peso;
	private boolean frio;
	private long idPedido;
	private int stock;
	
	public ProductoDto() {
		
	}

	public ProductoDto(long id, String nombre, String descripcion, String marca, String categoria, String productor,
			float valor) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.productor = productor;
		this.valor = valor;
	}

	//constructor con todos los valores
	public ProductoDto(long id, String nombre, String descripcion, String marca, String categoria, String productor,
			float valor, float peso, boolean frio, long idPedido, int stock) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.productor = productor;
		this.valor = valor;
		this.peso = peso;
		this.frio = frio;
		this.idPedido = idPedido;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public boolean isFrio() {
		return frio;
	}

	public void setFrio(boolean frio) {
		this.frio = frio;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoDto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca
				+ ", categoria=" + categoria + ", productor=" + productor + ", valor=" + valor + "]";
	}
	
}
