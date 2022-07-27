package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IZonaDao;
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

@Path("/zonas")
public class ZonasController {
	
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IZonaDao zonaDao;
	private String mensaje;
	
	/*--Obtener todas las zonas--*/
	@GET
	@Operation(method= ("Todas las zonas"), description =("Obtener todas las zonas cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<ZonaDto> getZonas(){
		return zonaDao.findall();
	}
	
	/*----------Obtener una zona por su id--------------*/
	
	/*------Guarda una nueva zona (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Zona"), description =("Guarda una nueva zona ingresada por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(ZonaDto zona){
		if(zona != null){
			Zona z= new Zona();
			z.setBarrio(zona.getBarrio());
			z.setDesdeLat(Float.valueOf(zona.getDesdeLat()));
			z.setHastaLat(Float.valueOf(zona.getHastaLat()));
			z.setDesdeLong(Float.valueOf(zona.getDesdeLong()));
			z.setHastaLong(Float.valueOf(zona.getHastaLong()));
			zonaDao.save(z);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	/*--------Edita una zona (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Zona"), description =("Edita un zona existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(ZonaDto z){
		Zona aux = zonaDao.find(z.getId());
		if (aux != null){
			aux.setBarrio(z.getBarrio());
			aux.setDesdeLat(z.getDesdeLat());
			aux.setHastaLat(z.getHastaLat());
			aux.setDesdeLong(z.getDesdeLong());
			aux.setHastaLong(z.getHastaLong());
			zonaDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro la zona a modificar").build();
		}
	}
	
	/*---------Borra una zona (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Zona"), description =("Borrar Zona existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Zona aux = zonaDao.find(id);
		if (aux != null){
			zonaDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Zona con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}

}
