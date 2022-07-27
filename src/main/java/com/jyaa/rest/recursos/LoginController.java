package com.jyaa.rest.recursos;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

import org.json.JSONObject;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misInterfaces.IUsuarioDao;
import com.jyaa.misclases.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/auth")
public class LoginController {
	
	public static String jwt;
	
	@Inject IUsuarioDao usDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validar (LoginDto usuario) {	
		
		boolean status = usDao.validar(usuario);
		if(status) {
			Usuario ingresado = usDao.findEmailCont(usuario);
			String KEY ="mi_clave";
			long tiempo = System.currentTimeMillis();
			jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, KEY).setSubject(ingresado.getNombre() + ingresado.getApellido()).setIssuedAt(new Date(tiempo)).setExpiration(new Date(tiempo + 1800000)).claim("email", ingresado.getEmail()).compact();
			
			
			JsonObject json = Json.createObjectBuilder().add("JWT",jwt).add("idUsuario", ingresado.getId().toString()).add("rol",ingresado.getRol().getNombre()).build();
			return Response.status(Response.Status.CREATED).entity(json).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

}
