package com.jyaa.rest.recursos;

import java.util.List;

import com.jyaa.misDTO.ProductoDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IProductoDao;
import com.jyaa.misclases.Producto;
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

@Path("/productos")
public class ProductosController {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IProductoDao productoDao;
	private String mensaje;
	
	/*-----Obtener todos los productos-----*/
	@GET
	@Operation(method= ("Todos los productos"), description =("Obtener todos los productos cargados en sistema"))
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductoDto> getPedidos(){
		return productoDao.findall();
	}
	
	/*----------Obtener un producto por su id--------------*/
	@GET
	@Operation(method= ("Encontrar producto"), description =("Buscar producto por su id"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id){
		ProductoDto prod = productoDao.findDto(id);
		if (prod != null){
			return Response
					.ok()
					.entity(prod)
					.build();
		} else {
			mensaje = "No se encontró el producto";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	
	/*--------Edita una zona (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Producto"), description =("Edita un producto existente (ubicado por su id)"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(ProductoDto p){
		Producto aux = productoDao.find(p.getId());
		if (aux != null){
			aux.setNombre(p.getNombre());
			aux.setProductor(p.getProductor());
			aux.setValor(p.getValor());
			aux.setStock(p.getStock());
			aux.setDescripcion(p.getDescripcion());
			//
			aux.setMarca(p.getMarca());
			aux.setCategoria(p.getCategoria());
			productoDao.modify(aux);
			return Response.ok().entity(aux).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encontro la producto a modificar").build();
		}
	}
	
	/*---------Borra una zona (si existe) enviando su id--------------------*/
	
	@DELETE
	@Operation(method= ("Borrar Producto"), description =("Borrar Producto existente (ubicado por su id)"))
	@Path("{id}")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("id") Integer id){
		Producto aux = productoDao.find(id);
		if (aux != null){
			productoDao.remove(aux);
			return Response.noContent().build();
		}else {
			mensaje = "No existe Producto con ese id";
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
