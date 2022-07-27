package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.VehiculoDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misInterfaces.IVehiculoDao;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Vehiculo;
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

@Path("/vehiculos")
public class VehiculosController {

	
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IVehiculoDao vehiculoDao;
	private String mensaje;
	
	/*--Obtener todos los vehiculos--*/
	@GET
	@Operation(method= ("Todos los vehiculos"), description =("Obtener todos los vehiculos cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<VehiculoDto> getVehiculos(){
		return vehiculoDao.findall();
	}
	
	/*----------Obtener un vehiculo por su id--------------*/
	
	/*------Guarda un nuevo vehiculo (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Vehiculo"), description =("Guarda un nuevo vehiculo ingresado por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(VehiculoDto vehiculo){
		if(vehiculo != null){
			Vehiculo v= new Vehiculo();
			v.setTipo(vehiculo.getTipo());
			v.setPesoMaximo(vehiculo.getPesoMax());
			v.setCombustible(vehiculo.isCombustible());
			vehiculoDao.save(v);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	/*--------Edita una zona (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar vehiculo"), description =("Edita un vehiculo existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(VehiculoDto v){
		Vehiculo aux = vehiculoDao.find(v.getId());
		if (aux != null){
			aux.setTipo(v.getTipo());
			aux.setPesoMaximo(v.getPesoMax());
			aux.setCombustible(v.isCombustible());
			vehiculoDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro el vehiculo a modificar").build();
		}
	}
	
	/*---------Borra una zona (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Vehiculo"), description =("Borrar Vehiculo existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Vehiculo aux = vehiculoDao.find(id);
		if (aux != null){
			vehiculoDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe el vehiculo con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}

}
