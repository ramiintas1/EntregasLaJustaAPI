package com.jyaa.misservlets;

public class Usuario {
	private String id;
	private String mail;
	private String password;
	private String nombre;
	private String apellido;
	private int phone;
	private String role;
	
	public Usuario (String u,String p, String r) {
		mail = u;
		password = p;
		role= r;
	}

	public Usuario (String u,String p, String n , String a, String c, int ph) {
		mail = u;
		password = p;
		nombre= n ;
		apellido= a;
		phone= ph;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}


}
