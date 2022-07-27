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
@Table(name="USUARIO")
public class Usuario {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USUARIO_ID")	
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDO")
	private String apellido;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "ROL_ID")
	private Rol rol;
	
	@Column(name="USUARIO_DNI")
	private String dni;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="CONTRASEÑA")
	private String contrasena;
	
	@Column(name="FECHA_NACIMIENTO")
	private String fechaN;
	
	private boolean borrado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICULO_ID")	//muchos usuarios pueden tener un mismo vehiculo
	private Vehiculo vehiculo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ESTADOR_ID")	//cada usuario tiene un estado asignado (disponible - no disponible) -> unidireccional
	private EstadoRepartidor estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ZONA_ID")	//muchos usuarios pueden tener una misma zona
	private Zona zonaAsignada;
	
	@OneToMany(mappedBy= "repartidor", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Entrega> entregas = new ArrayList<>();		//un usuario(repartidor puede tener muchos entregas)
	
	public Usuario() {
		this.borrado = false;
	}
	
	public Usuario(String nombre, String apellido, String dni, String email, String direccion,
			String contrasena, String fechaN) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.fechaN = fechaN;
		this.borrado = false;
	}
		
	public Usuario(String nombre, String apellido, Rol rol, String dni, String email, String direccion,
			String contrasena, String fechaN, Vehiculo vehiculo, EstadoRepartidor estado, Zona zonaAsignada) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.dni = dni;
		this.email = email;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.fechaN = fechaN;
		this.vehiculo = vehiculo;
		this.estado = estado;
		this.zonaAsignada = zonaAsignada;
		this.borrado = false;
	}
	
	

	public Usuario(Long id, String nombre, String apellido, Rol rol, String dni, String email, String direccion,
			String contrasena, String fechaN, Vehiculo vehiculo, EstadoRepartidor estado) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.dni = dni;
		this.email = email;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.fechaN = fechaN;
		this.vehiculo = vehiculo;
		this.estado = estado;
		this.borrado = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getFechaN() {
		return fechaN;
	}

	public void setFechaN(String fechaN) {
		this.fechaN = fechaN;
	}

	public EstadoRepartidor getEstado() {
		return estado;
	}

	public void setEstado(EstadoRepartidor estado) {
		this.estado = estado;
	}

	public Zona getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(Zona zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
