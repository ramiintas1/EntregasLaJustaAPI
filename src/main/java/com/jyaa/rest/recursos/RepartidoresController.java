package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misInterfaces.IEstadoRepartidorDao;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misInterfaces.IUsuarioDao;
import com.jyaa.misInterfaces.IVehiculoDao;
import com.jyaa.misInterfaces.IZonaDao;
import com.jyaa.misclases.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
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

@Path("/repartidores")
public class RepartidoresController {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IRolDao repDao;
	@Inject IUsuarioDao usDao;
	@Inject IVehiculoDao vehiculoDao;
	@Inject IEstadoRepartidorDao estadoDao;
	@Inject IZonaDao zonaDao;
	private String mensaje;
	
	/*-----Obtener todos los repartidores-----*/
	@GET
	@Operation(method= ("Todos los repartidas"), description =("Obtener todos los repartidores cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioDto> getUsuarios(){
		return repDao.findUs("Repartidor");
	}
	
	/*----------Obtener un usuario por su id--------------*/
	@GET
	@Operation(method= ("Encontrar usuario"), description =("Buscar usuarios por su id"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id){
		UsuarioDto usu = usDao.find(id);
		if ((usu != null)&&(usu.getRol().equalsIgnoreCase("Repartidor"))){
			return Response
					.ok()
					.entity(usu)
					.build();
		} else {
			mensaje = "No se encontró el usuario";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	/*------Guarda un nuevo repartidor (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Repartidor"), description =("Guarda un nuevo Repartidor ingresado por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(UsuarioDto usuario){
		if(usuario != null){ //podría validar si ya existe el usuario validar()
			Usuario us = new Usuario(usuario.getNombre(),usuario.getApellido(),usuario.getDni(),usuario.getEmail(),usuario.getDireccion(),usuario.getContrasena(),usuario.getFechaN());//,usuario.getImagen()
			us.setVehiculo(vehiculoDao.find(usuario.getVehiculo()));
			us.setRol(repDao.find("Repartidor"));
			us.setEstado(estadoDao.find(usuario.getEstado()));
			us.setZonaAsignada(zonaDao.find(usuario.getZona()));
			usDao.save(us);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	/*--------Edita un Repartidor (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Repartidor"), description =("Edita un repartidor existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(UsuarioDto us){
		UsuarioDto aux = usDao.find(us.getId());
		if ((aux != null)&&(aux.getRol().equalsIgnoreCase("Repartidor"))){
			
			usDao.modify(us);
			return Response.ok().entity(us).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("\"No se encontro el repartidor a modificar\"").build();
		}
	}
	
	/*---------Borra un Repartdior (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Repartidor"), description =("Borrar repartidor existente (ubicado por su id)"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		UsuarioDto aux = usDao.find(id);
		if ((aux != null)&&(aux.getRol().equalsIgnoreCase("Repartidor"))){
			usDao.remove(aux);
			return Response.noContent().build();
		} else {
			mensaje = "No existe el repartidor con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}