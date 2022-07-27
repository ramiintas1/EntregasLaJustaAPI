package com.jyaa.rest.recursos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jyaa.misDAO.PedidoDao;
import com.jyaa.misDAO.ProductoDao;
import com.jyaa.misDTO.EntregaDto;
import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misDTO.ProductoDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misInterfaces.IPedidoDao;
import com.jyaa.misInterfaces.IProductoDao;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.Producto;
import com.jyaa.misclases.TransicionEstado;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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

@Path("/pedidos-justa")
public class PedidosJustaController {
	
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Inject IPedidoDao pedidoDao;
	@Inject IProductoDao productoDao;
	
	public static JSONObject pedidojson;
	
	private String mensaje;
	
	@GET
	@Operation(method= ("Guardar Pedidos"), description =("Guarda todos los pedidos del sistema La Justa"))
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response ApiCliente() throws IOException, IOException {
		
			String clave = TokenController.token;
            
            String url = "http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/cart?properties=%5B%7B%22key%22%3A%22delivered%22%2C%22value%22%3Afalse%7D%5D";
		
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet httpPost = new HttpGet(url);

			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.addHeader("Authorization","Bearer " + clave +" ");
			httpPost.setHeader("Authorization","Bearer " + clave +" ");			

			System.out.println("Mi token");
			System.out.println(clave);
			CloseableHttpResponse response = client.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			String respuesta = EntityUtils.toString(response.getEntity(), "UTF-8");
				
		        if(response.getStatusLine().getStatusCode() == 200) {
		        	ArrayList<PedidoDto> listadePedidos = saveJsonToList(respuesta);
	    	        
	    	        for(PedidoDto pedActual : listadePedidos) {		//aca hiria un if de si existe el id justa
	    	          if(pedidoDao.validar(pedActual.getId())) {
	    	        	Pedido ped = new Pedido();
	    	        	ped.setIdJusta(pedActual.getId());
	    	        	ped.setIdCliente(pedActual.getIdCliente());
	    	        	ped.setMonto(pedActual.getMonto());
	    	        	ped.setNombreCliente(pedActual.getNombreCliente());
	    	        	ped.setCalle(pedActual.getCalle());
	    	        	ped.setEntreCalles(pedActual.getEntreCalles());
	    	        	ped.setNumero(pedActual.getNumero());
	    	        	ped.setDepartamento(pedActual.getDepartamento());
	    	        	ped.setLatitud(pedActual.getLatitud());
	    	        	ped.setLongitud(pedActual.getLongitud());
	    	        	pedidoDao.save(ped);
	    	        	for(ProductoDto prodActual : pedActual.getProductos()) {
	    	        		Producto prod = new Producto();
	    	        		prod.setNombre(prodActual.getNombre());
	    	        		prod.setValor(prodActual.getValor());
	    	        		prod.setStock(prodActual.getStock());
	    	        		prod.setPedido(ped); //se manda el pedido completo
	    	        		productoDao.save(prod);
	    	        	}
	    	          }
	    	        }
		        	client.close();
		            return Response.status(Response.Status.CREATED).entity(listadePedidos).build();
		        }else {
		        	client.close();
		        	return Response.status(Response.Status.CONFLICT).build();
		        }

	}
	
	/*----------Obtener un usuario por su id--------------*/
	@GET
	@Operation(method= ("Encontrar pedido de la justa"), description =("Buscar pedido por su id"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id)  throws IOException, IOException{
		
		String clave = TokenController.token;
		String url = "http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/cart/"+id;
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpPost = new HttpGet(url);

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.addHeader("Authorization","Bearer " + clave +" ");
		httpPost.setHeader("Authorization","Bearer " + clave +" ");	
		
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode());
		String respuesta = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		if(response.getStatusLine().getStatusCode() == 200) {
			
			JSONObject jsonPedido = new JSONObject(respuesta);
			PedidoDto pedido = new PedidoDto();
        	pedido.setId(jsonPedido.getInt(PedidoDto.ID));
        	pedido.setIdJusta(jsonPedido.getLong(PedidoDto.ID));
        	pedido.setEntregado(jsonPedido.getBoolean("delivered"));
        	pedido.setMonto(jsonPedido.getFloat(PedidoDto.MONTO));
        	//usuario
        	if(jsonPedido.get(PedidoDto.CLIENTE)!=null) {
        		JSONObject jsonUser = jsonPedido.getJSONObject(PedidoDto.CLIENTE);
        		pedido.setIdCliente(jsonUser.getLong("id"));
            	pedido.setNombreCliente(jsonUser.getString(PedidoDto.NOMBRECLIENTE));
        	}
        	if(jsonPedido.get("nodeDate")!=null) {
        		JSONObject jsonNodeDate = jsonPedido.getJSONObject("nodeDate");
        		if(jsonNodeDate.get("node")!=null) {
        			JSONObject jsonNode = jsonNodeDate.getJSONObject("node");
        			
        			if(jsonNode.get(PedidoDto.DIRECCION)!=null) {	
                		//delivery address
                    	JSONObject jsonAddress = jsonNode.getJSONObject(PedidoDto.DIRECCION);
                    	pedido.setCalle(jsonAddress.getString(PedidoDto.CALLE));	
                    	pedido.setEntreCalles(jsonAddress.getString(PedidoDto.ENTRECALLES));
                    	pedido.setNumero(jsonAddress.getString(PedidoDto.NUMERO));
                    	pedido.setDepartamento(jsonAddress.getString(PedidoDto.DEPARTAMENTO));
                    	pedido.setLatitud(jsonAddress.getFloat(PedidoDto.LATITUD));
                    	pedido.setLongitud(jsonAddress.getFloat(PedidoDto.LONGITUD));
                	}
        		}
        	}  	
        	if(jsonPedido.get(PedidoDto.CARROPRODUCTOS)!=null) { 		
        		JSONObject jsonProducto;
        		JSONArray jsonarrayProductos = jsonPedido.getJSONArray(PedidoDto.CARROPRODUCTOS);
        		List<ProductoDto> productos = new ArrayList();
            	for (int j = 0; j < jsonarrayProductos.length(); j++) {	
            		jsonProducto = jsonarrayProductos.getJSONObject(j);
            		if(jsonProducto.get(PedidoDto.PROD)!=null) {	
            			JSONObject jsonProd = jsonProducto.getJSONObject(PedidoDto.PROD);
            			ProductoDto producto = new ProductoDto();
                		producto.setId(jsonProd.getInt(PedidoDto.IDPROD));
                		producto.setNombre(jsonProd.getString(PedidoDto.NOMBREPROD));
                		producto.setValor(jsonProducto.getFloat(PedidoDto.VALORPROD));
                		producto.setStock(jsonProducto.getInt(PedidoDto.CANTIDAD));
                		producto.setIdPedido(jsonPedido.getInt(PedidoDto.ID)); 		
                		productos.add(producto);
            		}
            	}	
            	pedido.setProductos(productos);
        	}
			pedidojson = new JSONObject(respuesta);
			System.out.println(pedidojson.getBoolean("delivered"));
			pedidojson.put("delivered", true);
			System.out.println(pedidojson.toString());
		  	client.close();
            return Response.status(Response.Status.CREATED).entity(pedido).build();
        }else {
        	client.close();
        	return Response.status(Response.Status.CONFLICT).build();
        }
	}
	
	/*--------Edita un Repartidor (si este existe)-----------*/
	@PUT
	@Operation(method= ("Editar Pedido Justa"), description =("Edita un Pedido existente (ubicado por su id)"))
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(@PathParam("id") Integer id) throws ClientProtocolException, IOException{
		
		String clave = TokenController.token;
			
			String url = "http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/cart/";
		
			CloseableHttpClient client = HttpClients.createDefault();
		    
		    HttpPut httpPut = new HttpPut(url);
		    
		    String json = pedidojson.toString();
		    System.out.println(json);
		    StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
		    
		    httpPut.setEntity(entity);
		    //httpPut.setHeader("Accept", "application/json");
		    httpPut.setHeader("Content-type", "application/json");
		    httpPut.addHeader("Authorization","Bearer " + clave +" ");
		    //httpPut.setHeader("Authorization","Bearer " + clave +" ");	

		    CloseableHttpResponse response = client.execute(httpPut);
		    System.out.println(response.getStatusLine().getStatusCode());
		    String respuesta = EntityUtils.toString(response.getEntity(), "UTF-8");
		    System.out.println(respuesta);
			
	        if(response.getStatusLine().getStatusCode() == 200) {
	        	client.close();
	            return Response.status(Response.Status.CREATED).build();
	        }else {
	        	client.close();
	        	return Response.status(Response.Status.CONFLICT).build();
	        }
	}
	
	
	private static ArrayList<PedidoDto> saveJsonToList(String response) {
		
		ArrayList<PedidoDto> listaPedidos = new ArrayList<PedidoDto>();
		
		PedidoDto pedido;
		ProductoDto producto;
		
		JSONObject jsonPedido;
		JSONObject json = new JSONObject(response);
		JSONArray jsonarray= json.getJSONArray("page");
        
        for (int i = 0; i < jsonarray.length(); i++) {
        	 
        	jsonPedido = jsonarray.getJSONObject(i);
        	
        	pedido = new PedidoDto();
        	pedido.setId(jsonPedido.getInt(PedidoDto.ID));
        	pedido.setIdJusta(jsonPedido.getLong(PedidoDto.ID));
        	pedido.setEntregado(jsonPedido.getBoolean("delivered"));
        	pedido.setMonto(jsonPedido.getFloat(PedidoDto.MONTO));
        	//usuario
        	if(jsonPedido.get(PedidoDto.CLIENTE)!=null) {
        		JSONObject jsonUser = jsonPedido.getJSONObject(PedidoDto.CLIENTE);
        		pedido.setIdCliente(jsonUser.getLong("id"));
            	pedido.setNombreCliente(jsonUser.getString(PedidoDto.NOMBRECLIENTE));
        	}
        	if(jsonPedido.get("nodeDate")!=null) {
        		JSONObject jsonNodeDate = jsonPedido.getJSONObject("nodeDate");
        		if(jsonNodeDate.get("node")!=null) {
        			JSONObject jsonNode = jsonNodeDate.getJSONObject("node");
        			
        			if(jsonNode.get(PedidoDto.DIRECCION)!=null) {	
                		//delivery address
                    	JSONObject jsonAddress = jsonNode.getJSONObject(PedidoDto.DIRECCION);
                    	pedido.setCalle(jsonAddress.getString(PedidoDto.CALLE));	
                    	pedido.setEntreCalles(jsonAddress.getString(PedidoDto.ENTRECALLES));
                    	pedido.setNumero(jsonAddress.getString(PedidoDto.NUMERO));
                    	pedido.setDepartamento(jsonAddress.getString(PedidoDto.DEPARTAMENTO));
                    	pedido.setLatitud(jsonAddress.getFloat(PedidoDto.LATITUD));
                    	pedido.setLongitud(jsonAddress.getFloat(PedidoDto.LONGITUD));
                	}
        		}
        	}
        	
        	
        	if(jsonPedido.get(PedidoDto.CARROPRODUCTOS)!=null) {
        		
        		
        		JSONObject jsonProducto;
        		JSONArray jsonarrayProductos = jsonPedido.getJSONArray(PedidoDto.CARROPRODUCTOS);
        		List<ProductoDto> productos = new ArrayList();
            	for (int j = 0; j < jsonarrayProductos.length(); j++) {
            		
            		jsonProducto = jsonarrayProductos.getJSONObject(j);
            		
            
            		if(jsonProducto.get(PedidoDto.PROD)!=null) {
            			
            			JSONObject jsonProd = jsonProducto.getJSONObject(PedidoDto.PROD);
            			producto = new ProductoDto();
                		
                		producto.setId(jsonProd.getInt(PedidoDto.IDPROD));
                		producto.setNombre(jsonProd.getString(PedidoDto.NOMBREPROD));
                		producto.setValor(jsonProducto.getFloat(PedidoDto.VALORPROD));
                		producto.setStock(jsonProducto.getInt(PedidoDto.CANTIDAD));
                		producto.setIdPedido(jsonPedido.getInt(PedidoDto.ID));
                		
                		productos.add(producto);
            		}
            	}	
            	pedido.setProductos(productos);
        	}
        	listaPedidos.add(pedido);
       }
		return listaPedidos;
	}

}
