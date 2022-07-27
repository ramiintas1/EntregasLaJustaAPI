package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misDTO.TransicionEstadoDto;
import com.jyaa.misInterfaces.IEntregaDao;
import com.jyaa.misInterfaces.IEstadoEntregaDao;
import com.jyaa.misInterfaces.IPedidoDao;
import com.jyaa.misInterfaces.ITransicionEstadoDao;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.TransicionEstado;

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

@Path("/transicionestado")
public class TransicionEstadosControler {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject ITransicionEstadoDao transicionDao;
	@Inject IEstadoEntregaDao estadoDao;
	@Inject IEntregaDao entregaDao;
	@Inject IPedidoDao pedidoDao;
	private String mensaje;
	
	/*--Obtener todas las zonas--*/
	@GET
	@Operation(method= ("Todas las transiciones"), description =("Obtener todas las transiciones cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransicionEstadoDto> getTransiciones(){
		return transicionDao.findall();
	}
	
	/*------Guarda una nueva zona (antes verifica si ya existe)------*/
	@POST
	@Operation(method= ("Guardar Transicion"), description =("Guarda una nueva Transicion ingresada por sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(TransicionEstadoDto transicion){
		if(transicion != null){
			TransicionEstado t = new TransicionEstado();
			t.setFecha(transicion.getFecha());
			t.setMotivo(transicion.getMotivo());
			t.setActual(estadoDao.find(transicion.getActual()));
			t.setEntrega(entregaDao.find(transicion.getEntrega()));
			transicionDao.save(t);
			//if(transicion.getActual().equals("Cancelado")||(transicion.getActual().equals("Entregado"))) {	
			//	if(transicion.getActual().equals("Cancelado")) {
			//		Pedido p = pedidoDao.find(entregaDao.find(transicion.getEntrega()).getPedido().getId());
			//		p.setEntrega(null);
			//		pedidoDao.modify(p);
			//	}
				//entregaDao.remove(entregaDao.find(transicion.getEntrega()));
			//}
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	/*---------Borra una transicion (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Transicion"), description =("Borrar Trnasicion existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		TransicionEstado aux = transicionDao.find(id);
		if (aux != null){
			transicionDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Transicion con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
