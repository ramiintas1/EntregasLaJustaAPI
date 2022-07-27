package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IPedidoDao;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.Zona;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/pedidos")
public class PedidosController {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IPedidoDao pedidoDao;
	private String mensaje;
	
	/*-----Obtener todos los pedidos-----*/
	@GET
	@Operation(method= ("Todos los pedidos"), description =("Obtener todos los pedidos cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<PedidoDto> getPedidos(){
		return pedidoDao.findall();
	}
	
	/*----------Obtener un pedido por su id--------------*/
	@GET
	@Operation(method= ("Encontrar pedido"), description =("Buscar pedido por su id"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id){
		PedidoDto ped = pedidoDao.findDto(id);
		if (ped != null){
			return Response
					.ok()
					.entity(ped)
					.build();
		} else {
			mensaje = "No se encontró el pedido";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	/*--------Edita una pedido (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Pedido"), description =("Edita un Pedido existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(PedidoDto p){
		Pedido aux = pedidoDao.find(p.getId());
		if (aux != null){
			aux.setLatitud(p.getLatitud());
			aux.setLongitud(p.getLongitud());
			aux.setCalle(p.getCalle());
			aux.setEntreCalles(p.getEntreCalles());
			aux.setNumero(p.getNumero());
			aux.setDepartamento(p.getDepartamento());
			aux.setDescripcion(p.getDescripcion());
			aux.setNombreCliente(p.getNombreCliente());
			aux.setMonto(p.getMonto());
			aux.setDniCliente(p.getEmailCliente());
			pedidoDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro el pedido a modificar").build();
		}
	}
	
	/*---------Borra un pedido (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Pedido"), description =("Borrar Pedido existente (ubicado por su id)"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Pedido aux = pedidoDao.find(id);
		if (aux != null){
			pedidoDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe pedido con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
