package com.jyaa.rest.recursos;


import java.util.List;

import com.jyaa.misDAO.EntregaDao;
import com.jyaa.misDAO.EstadoEntregaDao;
import com.jyaa.misDAO.IFactoriaDao;
import com.jyaa.misDAO.PedidoDao;
import com.jyaa.misDAO.TransicionEstadoDao;
import com.jyaa.misDAO.UsuarioDao;
import com.jyaa.misDAO.ZonaDao;
import com.jyaa.misDTO.EntregaDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IEntregaDao;
import com.jyaa.misInterfaces.IEstadoEntregaDao;
import com.jyaa.misInterfaces.IPedidoDao;
import com.jyaa.misInterfaces.ITransicionEstadoDao;
import com.jyaa.misInterfaces.IUsuarioDao;
import com.jyaa.misInterfaces.IZonaDao;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.TransicionEstado;
import com.jyaa.misclases.Usuario;
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

@Path("/entregas")
public class EntregasController {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IUsuarioDao usDao;
	@Inject IEntregaDao entregaDao;
	@Inject IZonaDao zonaDao;
	@Inject IEstadoEntregaDao estadoEntrega;
	@Inject ITransicionEstadoDao transicionEstado;
	@Inject IPedidoDao pedido;
	
	private String mensaje;
	
	/*-----Obtener todas las entregas-----*/
	@GET
	@Operation(method= ("Todas las entregas"), description =("Obtener todas las entregas cargadas en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntregaDto> getUsuarios(){
		return entregaDao.findallEntregas();
	}
	
	
	/*----------Obtener un entrega por su id--------------*/
	@GET
	@Operation(method= ("Encontrar entrega"), description =("Buscar entregas por su id"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id){
		EntregaDto e = entregaDao.findDto(id);
		if (e != null){
			return Response
					.ok()
					.entity(e)
					.build();
		} else {
			mensaje = "No se encontró el usuario";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	/*------Guarda una nueva entrega (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Entrega"), description =("Crea una nueva Entrega ingresado por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(EntregaDto entrega){
		if(entrega != null){ //podría validar si ya existe el usuario validar()
			//Entrega e = new Entrega(entrega.getFecha(),entrega.getMotivo(),zonaDao.find(entrega.getZona()),usDao.findDNI(entrega.getDniRepartidor()),pedido.find(entrega.getPedido()));
			Entrega e = new Entrega(entrega.getFecha(),entrega.getMotivo(),zonaDao.find(entrega.getZona()),usDao.findDNI(entrega.getDniRepartidor()));
			e.setIdJusta(entrega.getPedido());
			//pedido.find(entrega.getPedido()).setEntrega(e);
			
			entregaDao.save(e);
			TransicionEstado historico = new TransicionEstado(entrega.getFecha(),entrega.getMotivo());
			historico.setActual(estadoEntrega.find(entrega.getEstado()));
			historico.setEntrega(e);
			transicionEstado.save(historico);
		
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	
	/*--------Edita una entrega (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Entrega"), description =("Edita una entrega existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(EntregaDto e){
		Entrega aux = entregaDao.find(e.getId());
		if (aux != null){
			aux.setFecha(e.getFecha());
			aux.setMotivo(e.getMotivo());
			aux.setZona(zonaDao.find(e.getZona()));
			aux.setRepartidor(usDao.findDNI(e.getDniRepartidor()));
			//aux.setEstados(transicionEstado.find(e.getEstado()));
			entregaDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro la entrega a modificar").build();
		}
	}
	
	/*---------Borra una Entrega (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Entrega"), description =("Borrar Entrega existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Entrega aux = entregaDao.find(id);
		if (aux != null){
			entregaDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Entrega con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
