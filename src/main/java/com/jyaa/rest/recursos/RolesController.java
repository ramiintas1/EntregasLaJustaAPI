package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misclases.Rol;		//deberia tener dto?
import com.jyaa.misclases.Zona;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/roles")
public class RolesController {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IRolDao rolDao;
	private String mensaje;
	
	/*--Obtener todos los roles--*/
	@GET
	@Operation(method= ("Todos los roles"), description =("Obtener todos los roles cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDto> getRoles(){
		return rolDao.findall();
	}
	
	/*----------Obtener un rol por su id--------------*/
	
	/*------Guarda un nuevo rol (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Rol"), description =("Guarda un nuevo Rol ingresado por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(RolDto rol){
		if(rol != null){
			Rol r= new Rol(rol.getNombre());
			rolDao.save(r);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	
	/*--------Edita un rol (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Rol"), description =("Edita un rol existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(RolDto r){
		Rol aux = rolDao.find(r.getId());
		if (aux != null){
			aux.setNombre(r.getNombre());
			rolDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro el rol a modificar").build();
		}
	}
	
	/*---------Borra rol (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Rol"), description =("Borrar Rol existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Rol aux = rolDao.find(id);
		if (aux != null){
			rolDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Rol con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}

}
