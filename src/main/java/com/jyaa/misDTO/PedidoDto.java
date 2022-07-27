package com.jyaa.misDTO;

import java.util.ArrayList;
import java.util.List;

import com.jyaa.misDTO.ProductoDto;

public class PedidoDto {
	
	public static String ID ="id";
	public static String DESCRIPCION ="description";
	public static String MONTO ="total";
	
	public static String CLIENTE ="user";
	public static String EMAILCLIENTE ="email";
	public static String TELEFONOCLIENTE ="phone";
	public static String NOMBRECLIENTE ="firstName";
	public static String APELLIDOCLIENTE ="lastName";
	
	public static String DIRECCION ="address";
	public static String CALLE ="street";
	public static String ENTRECALLES ="betweenStreets";
	public static String NUMERO ="number";
	public static String DEPARTAMENTO ="apartment";
	public static String LATITUD ="latitude";
	public static String LONGITUD ="longitude";
	public static String OBSERVACION ="observation";
	public static String CARROPRODUCTOS ="cartProducts";
	
	//Datos de producto
	public static String PROD = "product";
	public static String IDPROD ="id";
	public static String NOMBREPROD ="title";
	public static String DESCRIPCIONPROD ="unitDescription";
	public static String MARCAPROD ="id";
	public static String CATEGORIAPROD ="id";
	public static String PRODUCTORPROD ="id";
	public static String VALORPROD ="price";
	public static String CANTIDAD ="quantity";
	
	//DATOS PRODUCTO

	private long id;
	private String descripcion;
	private float monto;
	private long idCliente;
	private String emailCliente;	//Lo usariamos en vez del DNI
	private String telefonoCliente;
	private String nombreCliente;
	private String ApellidoCliente;
	private Long entrega;				//id de entrega (deberia ir?)
	private long idJusta;
	private boolean entregado;
	
	//valores para direccion
	private String calle;
	private String entreCalles;
	private String numero;
	private String departamento;
	private float latitud;
	private float longitud;
	private String observacion;
	
	private List<ProductoDto> productos = new ArrayList();
	
	public PedidoDto() {
		
	}

	//constructor sin inicializar lista de productos
	public PedidoDto(long id, String descripcion, float monto, String emailCliente, String telefonoCliente,
			String nombreCliente, String apellidoCliente, String calle, String entreCalles, String numero,
			String departamento, float latitud, float longitud, String observacion,long entrega, long idJusta) {
		this.id = id;
		this.descripcion = descripcion;
		this.monto = monto;
		this.emailCliente = emailCliente;
		this.telefonoCliente = telefonoCliente;
		this.nombreCliente = nombreCliente;
		this.ApellidoCliente = apellidoCliente;
		this.calle = calle;
		this.entreCalles = entreCalles;
		this.numero = numero;
		this.departamento = departamento;
		this.latitud = latitud;
		this.longitud = longitud;
		this.observacion = observacion;
		this.entrega = entrega;
		this.idJusta = idJusta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return ApellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		ApellidoCliente = apellidoCliente;
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

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<ProductoDto> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDto> productos) {
		this.productos = productos;
	}
	
	public Long getEntrega() {
		return entrega;
	}

	public void setEntrega(Long entrega) {
		this.entrega = entrega;
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

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	@Override
	public String toString() {
		return "PedidoDto [id=" + id + ", descripcion=" + descripcion + ", monto=" + monto + ", emailCliente="
				+ emailCliente + ", telefonoCliente=" + telefonoCliente + ", nombreCliente=" + nombreCliente
				+ ", ApellidoCliente=" + ApellidoCliente + ", calle=" + calle + ", entreCalles=" + entreCalles
				+ ", numero=" + numero + "]";
	}

}
