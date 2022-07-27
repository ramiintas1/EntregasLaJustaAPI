package com.jyaa.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jyaa.misInterfaces.IRolDao;
import jakarta.inject.Inject;

import com.jyaa.misDAO.EntregaDao;
import com.jyaa.misDAO.EstadoEntregaDao;
import com.jyaa.misDAO.EstadoRepartidorDao;
import com.jyaa.misDAO.IFactoriaDao;
import com.jyaa.misDAO.PedidoDao;
import com.jyaa.misDAO.ProductoDao;
import com.jyaa.misDAO.RolDao;
import com.jyaa.misDAO.TransicionEstadoDao;
import com.jyaa.misDAO.UsuarioDao;
import com.jyaa.misDAO.VehiculoDao;
import com.jyaa.misDAO.ZonaDao;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.EstadoEntrega;
import com.jyaa.misclases.EstadoRepartidor;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.Producto;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.TransicionEstado;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Vehiculo;
import com.jyaa.misclases.Zona;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jyaa.misDTO.*;

public class main {
	
	@Inject IRolDao rolDao;
	
	/*--Testeo fase 1--*/
	public static final boolean CREAR_ROLES = false;
	public static final boolean CREAR_ESTADOS_REP = false;
	public static final boolean CREAR_VEHICULOS = false;
	public static final boolean CREAR_ESTADOS_ENT = false;
	public static final boolean CREAR_ZONAS = false;
	/*--Testeo fase 2--*/
	public static final boolean CREAR_USUARIOS = false;
	public static final boolean CREAR_PEDIDOS = false;
	public static final boolean CREAR_PRODUCTOS = false;	
	public static final boolean CREAR_ENTREGAS = false;
	/*--Testeo fase 3--*/
	public static final boolean TESTEO_TRES = false;
	/*--Testeo fase 4--*/
	public static final boolean TESTEO_CUATRO = false;
	
	public static final boolean CARGA_DE_DATOS = false;
	
	
	
	public static void main(String[] args) throws IOException {
		
		/*--Se crean los Dao a partir de Factoria Dao para manipular los datos en la base de datos--*/
		ZonaDao zona = IFactoriaDao.getZoneDao();	
		VehiculoDao vehiculo = IFactoriaDao.getVehiculoDao();	
		UsuarioDao usuario = IFactoriaDao.getUsuarioDao();	
		TransicionEstadoDao transicionEstado = IFactoriaDao.getTransicionEstadoDao();
		RolDao rol = IFactoriaDao.getRolDao();	
		PedidoDao pedido = IFactoriaDao.getPedidoDao();
		ProductoDao producto = IFactoriaDao.getProductoDao();	
		
		EstadoRepartidorDao estadoRepartidor = IFactoriaDao.getEstadoRepartidorDao();	
		EstadoEntregaDao estadoEntrega = IFactoriaDao.getEstadoEntregaDao();	
		EntregaDao entrega = IFactoriaDao.getEntregaDao();
		
		
		/*--Se cargan datos estaticos que se utilizaran para indicar estados y clasificaciones ya determinadas--*/
		//guardarRoles(rol);
		//rol.roles();
		guardarVehiculos(vehiculo);
		guardarEstadosRepartidor(estadoRepartidor);
		guardarEstadosEntrega(estadoEntrega);
		guardarZonas(zona);
		
		/*--Se cargan datos que se utilizaran para el testeo del programa--*/
		//guardarPedidos(pedido,producto);	//Se cargan 2 Pedidos
		guardarProductos(producto,pedido);	//Se guardan 6 productos
		guardarUsuarios(usuario,rol,vehiculo,estadoRepartidor,zona);	//Se cargan 3 usuarios
		
		/*--Se generan dos pedidos y sus respectivas entregas y se asingan a dos repartidores  --*/		
		guardarEntregas(entrega,usuario,zona,estadoEntrega,pedido,transicionEstado,estadoRepartidor);	//Se cargan 2 Entregas
		
		/*--Se efectuan las entregas y una de ellas se modifica quitansole un producto--*/
		TesteoTerceraParte(transicionEstado,estadoEntrega,entrega,usuario,producto,pedido);
		
		/*--Una pedido es cancelado y eliminado. Un usuario renuncia y es borrado--*/
		TesteoCuartaParte(entrega,usuario,producto,pedido);
		
		ApiCliente(producto,pedido);

	}
	
	public static void guardarRoles(RolDao rol) {
		if(CREAR_ROLES) {
			rol.save(new Rol("Administrador"));
			rol.save(new Rol("Repartidor"));
		}
	}
	public static void guardarVehiculos(VehiculoDao vehiculo) {
		if(CREAR_VEHICULOS) {
			vehiculo.save(new Vehiculo("Moto",30,true));
			vehiculo.save(new Vehiculo("Auto",120,true));
			vehiculo.save(new Vehiculo("Bici",20,false));
		}
	}
	public static void guardarEstadosRepartidor(EstadoRepartidorDao estadoRepartidor) {
		if(CREAR_ESTADOS_REP) {
			estadoRepartidor.save(new EstadoRepartidor("Disponible"));
			estadoRepartidor.save(new EstadoRepartidor("No Disponible"));
		}
	}
	public static void guardarEstadosEntrega(EstadoEntregaDao estadoEntrega) {
		if(CREAR_ESTADOS_ENT) {
			estadoEntrega.save(new EstadoEntrega("Entregado"));
			estadoEntrega.save(new EstadoEntrega("En Curso"));
			estadoEntrega.save(new EstadoEntrega("Rechazado"));
			estadoEntrega.save(new EstadoEntrega("En Preparacion"));
		}
	}
	public static void guardarZonas(ZonaDao zona) {
		if(CREAR_ZONAS) {
			zona.save(new Zona("La Plata Centro",10f,10f,10f,10f));
			zona.save(new Zona("Sicardi",10f,10f,10f,10f));
			zona.save(new Zona("Segui",10f,10f,10f,10f));
			zona.save(new Zona("Gorina",10f,10f,10f,10f));
			zona.save(new Zona("Villa Elisa",10f,10f,10f,10f));
			zona.save(new Zona("Tolosa",10f,10f,10f,10f));
		}
	}
	public static void guardarProductos(ProductoDao producto, PedidoDao pedido) {
		if(CREAR_PRODUCTOS){
			Producto prod1 = new Producto("Queso Sardo", 1f, "Estilo sardo", 480f, true,"imagen","Don Julian","Don Julian","Quesos",3);
			prod1.setPedido(pedido.find(1L));
			Producto prod2 = new Producto("Salame Criollo", 240f, "Picado Grueso", 240f, true,"imagen","Stella Maris","Familia Meglio","Embutidos",10);
			prod2.setPedido(pedido.find(1L));
			Producto prod3 = new Producto("Mermelada de Tomate", 360f, "Elaborada Artesanalmente", 220f, true,"imagen","Arte en conserva","Arte en conserva","mermeladas",15);
			prod3.setPedido(pedido.find(1L));
			Producto prod4 = new Producto("Jugo clid", 1f, "Sabor Naranja", 50f, false,"imagen","Sancor","Sancor","Jugos",3);
			prod4.setPedido(pedido.find(2L));
			Producto prod5 = new Producto("Arroz", 50f, "Arroz gallo", 120f, true,"imagen","Gallo","Gallo","Arroz",3);
			prod5.setPedido(pedido.find(2L));
			Producto prod6 = new Producto("Playadito", 1f, "Yerba", 480f, true,"imagen","PLayadito","PLayadito","Infusion",3);
			prod6.setPedido(pedido.find(3L));
			
			producto.save(prod1);
			producto.save(prod2);
			producto.save(prod3);
			producto.save(prod4);
			producto.save(prod5);
			producto.save(prod6);
			
			Pedido p1 = pedido.find(1L);
			p1.setMonto(prod1.getValor() + prod2.getValor() + prod3.getValor());
			pedido.modify(p1);
			Pedido p2 = pedido.find(2L);
			p2.setMonto(prod4.getValor() + prod5.getValor());
			pedido.modify(p2);
			Pedido p3 = pedido.find(3L);
			p3.setMonto(prod5.getValor());
			pedido.modify(p3);
		}
	}
	public static void guardarUsuarios(UsuarioDao usuario, RolDao rol, VehiculoDao vehiculo, EstadoRepartidorDao estadoRepartidor, ZonaDao zona) {
		/*
		if(CREAR_USUARIOS) {
			Usuario us1 = new Usuario("Ramiro","Intas","39432","ramiro@ramiro.com","Carlos Gardel", "12345","10/10/1996","imagen");
			
			us1.setRol(rol.find("Repartidor"));			
			us1.setVehiculo(vehiculo.find("Auto"));		
			us1.setEstado(estadoRepartidor.find("Disponible"));			
			us1.setZonaAsignada(zona.find("La Plata Centro"));			
			usuario.save(us1);
			
			Usuario us2 = new Usuario("Juan Ignacio","De Gaetano","38432","juan@juan.com","Bagliardi", "12345","10/10/1996","imagen");
			
			us2.setRol(rol.find("Administrador"));				
			usuario.save(us2);
			
			Usuario us3 = new Usuario("Marcelo","Tinelli","19432312","marcelo@marcelo.com","12 y 50", "12345","10/10/1996","imagen");
			
			us3.setRol(rol.find("Repartidor"));			
			us3.setVehiculo(vehiculo.find("Moto"));			
			us3.setEstado(estadoRepartidor.find("Disponible"));			
			us3.setZonaAsignada(zona.find("La Plata Centro"));		
			usuario.save(us3);
			
			Usuario us4 = new Usuario("Jose Maria","Listorti","13432312","josema@josema.com","7 y 60", "12345","10/10/1996","imagen");
			
			us4.setRol(rol.find("Repartidor"));				
			us4.setVehiculo(vehiculo.find("Bici"));				
			us4.setEstado(estadoRepartidor.find("Disponible"));		
			us4.setZonaAsignada(zona.find("La Plata Centro"));		
			usuario.save(us4);
		}		
	}
	public static void guardarPedidos(PedidoDao pedido, ProductoDao producto) {
		if(CREAR_PEDIDOS) {
			Pedido ped1 = new Pedido ();
			ped1.setDescripcion("Este pedido contiene queso y salame");
			ped1.setDniCliente("14569678");
			
			Pedido ped2 = new Pedido();
			ped2.setDescripcion("Este pedido contiene Mermelada");
			ped2.setDniCliente("235476586");
			
			Pedido ped3 = new Pedido();
			ped3.setDescripcion("Este pedido contiene yerba");
			ped3.setDniCliente("435476586");
			
			pedido.save(ped1);
			pedido.save(ped2);
			pedido.save(ped3);
		}
		*/
	}
	public static void guardarEntregas(EntregaDao entrega, UsuarioDao usuario, ZonaDao zona, EstadoEntregaDao estadoEntrega, PedidoDao pedido, TransicionEstadoDao transicionEstado, EstadoRepartidorDao estadoRepartidor) {
		if(CREAR_ENTREGAS) {
			List<Usuario> disponibles = estadoRepartidor.findUs("Disponible");
		/*	
			Entrega entr1 = new Entrega();
			entr1.setDireccion("8 y 50 n°1111");
			entr1.setLatitud(10f);
			entr1.setLongitud(10f);			
			entr1.setRepartidor(disponibles.get(0));		//buscar en rep disponibles usuario.find(1L)
			entr1.setZona(zona.find("La Plata Centro"));	
			entr1.setPedido(pedido.find(1L));
			entrega.save(entr1);
			
			
			TransicionEstado historico1 = new TransicionEstado("6/6/2021","Entrega iniciada y asignada");
			historico1.setActual(estadoEntrega.find("En Curso"));	
			historico1.setEntrega(entrega.find(1L));
			transicionEstado.save(historico1);		
			
			Entrega entr2 = new Entrega();
			entr2.setDireccion("2 y 51 n°1111");
			entr2.setLatitud(10f);
			entr2.setLongitud(10f);		
			entr2.setRepartidor(disponibles.get(1));	//usuario.find(3L)
			entr2.setZona(zona.find("La Plata Centro"));		
			entr2.setPedido(pedido.find(2L));	
			entrega.save(entr2);

			
			TransicionEstado historico2 = new TransicionEstado("6/6/2021","Entrega iniciada y asignada");
			historico2.setActual(estadoEntrega.find("En Curso"));	
			historico2.setEntrega(entrega.find(2L));
			transicionEstado.save(historico2);
			
			Entrega entr3 = new Entrega();
			entr3.setDireccion("69 y 18 n°1111");
			entr3.setLatitud(10f);
			entr3.setLongitud(10f);		
			entr3.setRepartidor(disponibles.get(2));	//	usuario.find(4L)
			entr3.setZona(zona.find("Sicardi"));		
			entr3.setPedido(pedido.find(3L));
			entrega.save(entr3);

			
			TransicionEstado historico3 = new TransicionEstado("6/6/2021","Entrega iniciada y asignada");
			historico3.setActual(estadoEntrega.find("En Curso"));		
			historico3.setEntrega(entrega.find(3L));
			transicionEstado.save(historico3);
		*/	
		}
	}
	
	public static void TesteoTerceraParte(TransicionEstadoDao transicionEstado, EstadoEntregaDao estadoEntrega, EntregaDao entrega, UsuarioDao usuario, ProductoDao producto, PedidoDao pedido) {
		if(TESTEO_TRES) {
			/*--Se realiza una entrega con exito--*/
			TransicionEstado historicoNuevo = new TransicionEstado("6/6/2021","Entrega realizada con exito");
			historicoNuevo.setActual(estadoEntrega.find("Entregado"));	
			historicoNuevo.setEntrega(entrega.find(1L));
			transicionEstado.save(historicoNuevo);
			
			Entrega entregaFinalizada = entrega.find(1L);
			entrega.modify(entregaFinalizada);
			
			/*--Se borra un producto de otra (Sequiere borrar arroz)--*/
			Producto prodABorrar = producto.find("Arroz");
			Pedido ped = pedido.find(prodABorrar.getPedido().getId());
			ped.setMonto((ped.getMonto() - prodABorrar.getValor()));			
			pedido.modify(ped);
			producto.remove(prodABorrar);
			
			/*--Y luego se entrega con exito--*/
			TransicionEstado historicoNuevo2 = new TransicionEstado("6/6/2021","Entrega realizada con exito");
			historicoNuevo2.setActual(estadoEntrega.find("Entregado"));	
			historicoNuevo2.setEntrega(entrega.find(2L));
			transicionEstado.save(historicoNuevo2);
			
			Entrega entregaFinalizada2 = entrega.find(2L);
			entrega.modify(entregaFinalizada2);
		
		}
	}
	
	public static void TesteoCuartaParte(EntregaDao entrega, UsuarioDao usuario, ProductoDao producto, PedidoDao pedido) {
		if(TESTEO_CUATRO) {
			
			/*--Un Pedido es cancelado, y por lo tanto borrado--*/
			entrega.remove(pedido.find(3L).getEntrega());
			pedido.remove(pedido.find(3L));
			
			/*--Por ultimo un repartidor renuncia--*/
//			Usuario renuncia = usuario.find("Marcelo","Tinelli");
//			usuario.remove(renuncia);
		}
	}
	

	public static void ApiCliente(ProductoDao productoDao, PedidoDao pedidoDao) throws IOException, IOException {
		
		if(CARGA_DE_DATOS) {

			//String URL_API = "http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/cart";
			////String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYW1pcm8yQHJhbWlyby5jb20iLCJST0xFX0FETUlOSVNUUkFET1IsIFJPTEVfVVNVQVJJTyI6IlJPTEVfVVNVQVJJTyIsImlhdCI6MTYyNzQ5MTIyOCwiZXhwIjoxNjI3NTc3NjI4fQ.ElBghM7U13pogmaRyM5iZbyo_NUk7xEefW9qZOTusvo";
			//String URL_API = "https://jsonplaceholder.typicode.com/users";
	        
			/* Cliente para la conexión*/
	        ////Client client = ClientBuilder.newClient();
	        
	        /* Definición de URL*/
	        ////WebTarget target = client.target(URL_API);
	        ////String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,"Bearer" + TOKEN).get(String.class);
	        
	        /* Escribimos por consola el resultado de json*/
	        ////System.out.println(response);
	        ////System.out.println("Imprimir");
	 
	        /*Almacenamos la información del json en una lista java*/
	        //if(response != null) {
	        //	ArrayList<PedidoDto> listadePedidos = saveJsonToList(response);
	        //}

	        /*--------------------------------------------------------------------*/
	       
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();

	        HttpHost targetHost = new HttpHost("ec2-35-173-57-183.compute-1.amazonaws.com", 8080, "http");
	        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	        		AuthScope.ANY,		// new AuthScope(targetHost.getHostName(), targetHost.getPort())
	                new UsernamePasswordCredentials("ramiro2@ramiro.com", "123456"));

	        // Create AuthCache instance
	        AuthCache authCache = new BasicAuthCache();
	        // Generate BASIC scheme object and add it to the local auth cache
	        BasicScheme basicAuth = new BasicScheme();
	        authCache.put(targetHost, basicAuth);

	        // Add AuthCache to the execution context
	        HttpClientContext context = HttpClientContext.create();
	        context.setCredentialsProvider(credsProvider);
	        context.setAuthCache(authCache);
	        
	        HttpGet httpget = new HttpGet("/api/cart");
	        
	        CloseableHttpResponse responsee = httpclient.execute(targetHost, httpget, context);	//targetHost,
            int status= responsee.getStatusLine().getStatusCode();
            if(status == 200) {
            	HttpEntity entity = responsee.getEntity();
            	String respuesta = EntityUtils.toString(entity, "UTF-8");
    	        JSONObject json = new JSONObject(respuesta);
    	        System.out.println(json.getInt("totalElements"));
    	        //ArrayList<PedidoDto> listadePedidos = saveJsonToList(respuesta);
    	        ArrayList<PedidoDto> listadePedidos = saveJsonToList2(respuesta);
    	        for(PedidoDto pedActual : listadePedidos) {
    	        	Pedido ped = new Pedido();
    	        	ped.setId(pedActual.getId());
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
    	        		prod.setId(prodActual.getId());
    	        		prod.setNombre(prod.getNombre());
    	        		prod.setValor(prodActual.getValor());
    	        		prod.setStock(prodActual.getStock());
    	        		prod.setPedido(ped); //se manda el pedido completo
    	        		productoDao.save(prod);
    	        	}
    	        }

            }
            System.out.println(status);            
            
	        /*
	        for (int i = 0; i < 3; i++) {
	            CloseableHttpResponse responsee = httpclient.execute(targetHost, httpget, context);	//targetHost,
	            try {
	                HttpEntity entity = responsee.getEntity();
	                int status= responsee.getStatusLine().getStatusCode();
	    	        System.out.println(status);
	            } finally {
	                responsee.close();
	            }
	        }
	        */
	        responsee.close();
		}
	}
/*	
	private static ArrayList<PedidoDto> saveJsonToList(String response) {
		
		ArrayList<PedidoDto> listaPedidos = new ArrayList<PedidoDto>();
		
		PedidoDto pedido;
		ProductoDto producto;
		List<ProductoDto> productos = new ArrayList(); //deberia ser una lista tambien?
		
		JSONObject jsonPedido;
		JSONObject json = new JSONObject(response);
        //JSONArray jsonarray = new JSONArray(response);
		JSONArray jsonarray= json.getJSONArray("page");
        
        for (int i = 0; i < jsonarray.length(); i++) {
        	 
        	jsonPedido = jsonarray.getJSONObject(i);
        	
        	pedido = new PedidoDto();
        	pedido.setId(jsonPedido.getInt(PedidoDto.ID));
        	pedido.setMonto(jsonPedido.getFloat(PedidoDto.MONTO));
        	pedido.setObservacion(jsonPedido.getString(PedidoDto.OBSERVACION));
        	//usuario
        	if(jsonPedido.get(PedidoDto.CLIENTE)!=null) {
        		JSONObject jsonUser = jsonPedido.getJSONObject(PedidoDto.CLIENTE);
            	//pedido.setEmailCliente(jsonUser.getString(PedidoDto.EMAILCLIENTE));
            	//pedido.setTelefonoCliente(jsonUser.getString(PedidoDto.TELEFONOCLIENTE));
            	pedido.setNombreCliente(jsonUser.getString(PedidoDto.NOMBRECLIENTE));
            	//pedido.setApellidoCliente(jsonUser.getString(PedidoDto.APELLIDOCLIENTE));
            	//pedido.setDescripcion(jsonUser.getString(PedidoDto.DESCRIPCION));		//Los null no los toma
            	
            	if(jsonUser.get(PedidoDto.DIRECCION)!=null) {		//la direccion esta en otro lado, (son los nodos siempre)
            		//delivery address
                	JSONObject jsonAddress = jsonUser.getJSONObject(PedidoDto.DIRECCION);
                	pedido.setCalle(jsonAddress.getString(PedidoDto.CALLE));	
                	pedido.setEntreCalles(jsonAddress.getString(PedidoDto.ENTRECALLES));
                	pedido.setNumero(jsonAddress.getString(PedidoDto.NUMERO));
                	pedido.setDepartamento(jsonAddress.getString(PedidoDto.DEPARTAMENTO));
                	pedido.setLatitud(jsonAddress.getFloat(PedidoDto.LATITUD));
                	pedido.setLongitud(jsonAddress.getFloat(PedidoDto.LONGITUD));
            	}
        	}
        	
        	if(jsonPedido.get(PedidoDto.CARROPRODUCTOS)!=null) {
        		
        		JSONObject jsonProducto;
        		JSONArray jsonarrayProductos = jsonPedido.getJSONArray(PedidoDto.MONTO);
            	for (int j = 0; j < jsonarrayProductos.length(); j++) {
            		
            		jsonProducto = jsonarrayProductos.getJSONObject(i);
            		
            		if(jsonProducto.get(PedidoDto.PROD)!=null) {
            			
            			JSONObject jsonProd = jsonProducto.getJSONObject(PedidoDto.PROD);
                		producto = new ProductoDto();
                		
                		producto.setId(jsonProd.getInt(PedidoDto.IDPROD));
                		producto.setNombre(jsonProd.getString(PedidoDto.NOMBREPROD));
                		producto.setDescripcion(jsonProd.getString(PedidoDto.DESCRIPCIONPROD));
                		//producto.setMarca(jsonProducto.getString(PedidoDto.MARCAPROD));
                		//producto.setCategoria(jsonProducto.getString(PedidoDto.CATEGORIAPROD));
                		//producto.setProductor(jsonProducto.getString(PedidoDto.PRODUCTORPROD));
                		producto.setValor(jsonProd.getFloat(PedidoDto.VALORPROD));
                		producto.setIdPedido(jsonPedido.getInt(PedidoDto.ID));
                		
                		productos.add(producto);
            		}
            	}	
        	}
        	pedido.setProductos(productos);
        	listaPedidos.add(pedido);
       }
		return listaPedidos;
	}
*/	
private static ArrayList<PedidoDto> saveJsonToList2(String response) {
		
		ArrayList<PedidoDto> listaPedidos = new ArrayList<PedidoDto>();
		
		PedidoDto pedido;
		ProductoDto producto;
		List<ProductoDto> productos = new ArrayList(); //deberia ser una lista tambien?
		
		JSONObject jsonPedido;
		JSONObject json = new JSONObject(response);
        //JSONArray jsonarray = new JSONArray(response);
		JSONArray jsonarray= json.getJSONArray("page");
        
        for (int i = 0; i < jsonarray.length(); i++) {
        	 
        	jsonPedido = jsonarray.getJSONObject(i);
        	
        	pedido = new PedidoDto();
        	pedido.setId(jsonPedido.getInt(PedidoDto.ID));
        	pedido.setMonto(jsonPedido.getFloat(PedidoDto.MONTO));
        	//pedido.setObservacion(jsonPedido.getString(PedidoDto.OBSERVACION));
        	//usuario
        	if(jsonPedido.get(PedidoDto.CLIENTE)!=null) {
        		JSONObject jsonUser = jsonPedido.getJSONObject(PedidoDto.CLIENTE);
            	//pedido.setEmailCliente(jsonUser.getString(PedidoDto.EMAILCLIENTE));
            	//pedido.setTelefonoCliente(jsonUser.getString(PedidoDto.TELEFONOCLIENTE));
            	pedido.setNombreCliente(jsonUser.getString(PedidoDto.NOMBRECLIENTE));
            	//pedido.setApellidoCliente(jsonUser.getString(PedidoDto.APELLIDOCLIENTE));
            	//pedido.setDescripcion(jsonUser.getString(PedidoDto.DESCRIPCION));		//Los null no los toma
        	}
        	if(jsonPedido.get("nodeDate")!=null) {
        		JSONObject jsonNodeDate = jsonPedido.getJSONObject("nodeDate");
        		if(jsonNodeDate.get("node")!=null) {
        			JSONObject jsonNode = jsonNodeDate.getJSONObject("node");
        			
        			if(jsonNode.get(PedidoDto.DIRECCION)!=null) {		//la direccion esta en otro lado, (son los nodos siempre)
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
            	for (int j = 0; j < jsonarrayProductos.length(); j++) {
            		
            		jsonProducto = jsonarrayProductos.getJSONObject(j);
            		
            		
            		if(jsonProducto.get(PedidoDto.PROD)!=null) {
            			
            			JSONObject jsonProd = jsonProducto.getJSONObject(PedidoDto.PROD);
            			producto = new ProductoDto();
                		
                		producto.setId(jsonProd.getInt(PedidoDto.IDPROD));
                		producto.setNombre(jsonProd.getString(PedidoDto.NOMBREPROD));
                		//producto.setDescripcion(jsonProd.getString(PedidoDto.DESCRIPCIONPROD));
                		//producto.setMarca(jsonProducto.getString(PedidoDto.MARCAPROD));
                		//producto.setCategoria(jsonProducto.getString(PedidoDto.CATEGORIAPROD));
                		//producto.setProductor(jsonProducto.getString(PedidoDto.PRODUCTORPROD));
                		producto.setValor(jsonProducto.getFloat(PedidoDto.VALORPROD));
                		producto.setStock(jsonProducto.getInt(PedidoDto.CANTIDAD));
                		producto.setIdPedido(jsonPedido.getInt(PedidoDto.ID));
                		
                		productos.add(producto);
            		}
            	}	
        	}
        	pedido.setProductos(productos);
        	listaPedidos.add(pedido);
       }
		return listaPedidos;
	}

}
