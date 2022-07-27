package com.jyaa.rest.recursos;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class Intercepter implements ContainerRequestFilter {
	
	

	public void filter (ContainerRequestContext request) throws IOException{
		
		String clave = LoginController.jwt;
		
		String url = request.getUriInfo().getAbsolutePath().toString();
		if(url.contains("/api/pedidos-justa")){
			return;
		}
		if(url.contains("/api/token-justa")){
			return;
		}
		if(url.contains("/api/auth")){
			return;
		}
		if(url.contains("/api/administradores")){
			return;
		}
		if(url.contains("/api/roles")){
			return;
		}
		if(url.contains("/api/estadosentrega")){
			return;
		}
		if(url.contains("/api/estadosrepartidor")){
			return;
		}
		String token = request.getHeaderString("Authorization");
		if(token==null) {
			JsonObject json = Json.createObjectBuilder()
								.add("mensaje", "credenciales son necesarias")
								.build();
			request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
								.entity(json).type(MediaType.APPLICATION_JSON).build());
			return;
		}
		if(!token.equals(clave)) {
			JsonObject json = Json.createObjectBuilder()
								.add("mensaje", "credenciales incorrectas")
								.build();
			request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
					.entity(json).type(MediaType.APPLICATION_JSON).build());
			return;
			
		}
		
	}

}
