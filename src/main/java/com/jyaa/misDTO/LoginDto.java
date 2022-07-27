package com.jyaa.misDTO;

import com.google.gson.annotations.SerializedName;

public class LoginDto {

	private String usuario;
	private String contrasena;
	
	public LoginDto(){
		
	}

	public LoginDto(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
