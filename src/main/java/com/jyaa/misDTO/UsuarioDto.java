package com.jyaa.misDTO;

public class UsuarioDto {
	
	private Long id;
	private String nombre;	
	private String apellido;	
	private String rol;
	private String dni;
	private String email;
	private String direccion;
	private String contrasena;
	private String fechaN;	
	private String imagen;
	private String vehiculo;
	private String estado;
	private String zona;
	
	public UsuarioDto() {
		
	}
	
	public UsuarioDto(Long id, String nombre, String apellido, String rol, String dni, String email, String direccion,
			String contrasena, String fechaN, String vehiculo, String estado, String zona) {
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
		this.zona = zona;
	}
	
	public UsuarioDto(Long id, String nombre, String apellido, String rol, String dni, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.dni = dni;
		this.email = email;
	}
	
	public UsuarioDto(Long id, String nombre, String apellido, String rol, String dni, String email, String direccion,
			String contrasena, String fechaN) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.dni = dni;
		this.email = email;
		this.direccion = direccion;
		this.contrasena = contrasena;
		this.fechaN = fechaN;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
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

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getFechaN() {
		return fechaN;
	}

	public void setFechaN(String fechaN) {
		this.fechaN = fechaN;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
