package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.EstadoRepartidorDto;
import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IEstadoEntregaDao;
import com.jyaa.misInterfaces.IEstadoRepartidorDao;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misclases.EstadoRepartidor;
import com.jyaa.misclases.Rol;
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

@Path("/estadosrepartidor")
public class EstadosRepartidorController {
	
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IEstadoRepartidorDao estadoDao;
	private String mensaje;
	
	/*--Obtener todos los roles--*/
	@GET
	@Operation(method= ("Todos los estados"), description =("Obtener todos los estados de repartidores cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<EstadoRepartidorDto> getEstados(){
		return estadoDao.findall();
	}
	
	/*----------Obtener un estado por su id--------------*/
	
	/*------Guarda un nuevo rol (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Estado"), description =("Guarda un nuevo Estado de repartidor ingresado por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(EstadoRepartidorDto estado){
		if(estado != null){
			EstadoRepartidor e= new EstadoRepartidor(estado.getNombre());
			estadoDao.save(e);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	/*--------Edita una estado (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Estado Repartidor"), description =("Edita un estado de repartidor existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(EstadoRepartidorDto e){
		EstadoRepartidor aux = estadoDao.find(e.getId());
		if (aux != null){
			aux.setNombre(e.getNombre());
			estadoDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro el estado a modificar").build();
		}
	}
	
	/*---------Borra un estado (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Estado Repartidor"), description =("Borrar Estado de Repartidor existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		EstadoRepartidor aux = estadoDao.find(id);
		if (aux != null){
			estadoDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Estado con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}

}
