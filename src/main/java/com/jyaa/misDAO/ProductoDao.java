package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misDTO.ProductoDto;
import com.jyaa.misInterfaces.IProductoDao;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.Producto;
import com.jyaa.misclases.Usuario;

import jakarta.inject.Inject;

@Service
public class ProductoDao implements IProductoDao {
	
	@Inject EntityManager manager;
		
	public void save(Producto producto) {	
		manager.getTransaction().begin();
		manager.persist(producto);
		manager.getTransaction().commit();
		//manager.close();	
	}
	
	public Producto find(long l) {
		manager.getTransaction().begin();
		Producto p = manager.find(Producto.class, l);	
		manager.getTransaction().commit();
		//manager.close();	
		return p;
	}
	
	public ProductoDto findDto(long l) {
		manager.getTransaction().begin();
		Producto p = manager.find(Producto.class, l);
		ProductoDto producto = new ProductoDto(p.getId(),p.getNombre(),p.getDescripcion(),null,null,null,p.getValor(),p.getPeso(),false,p.getPedido().getId(),p.getStock());
		manager.getTransaction().commit();
		//manager.close();	
		return producto;
	}
	
	public List<ProductoDto> findall(){
		manager.getTransaction().begin();
		List<Producto> producto  = (List<Producto>) manager.createQuery("FROM Producto").getResultList();
		List<ProductoDto> listaProductos = new ArrayList<>();
		for(Producto p: producto) {
			if(!p.isBorrado()) {
				listaProductos.add(new ProductoDto(p.getId(),p.getNombre(),p.getDescripcion(),null,null,null,p.getValor(),p.getPeso(),false,p.getPedido().getId(),p.getStock()));
			}
		}
		manager.getTransaction().commit();
		//manager.close();
		return listaProductos;
	}
	
	public Producto find(String st) {
		Query query = manager.createQuery("Select p from Producto p where p.nombre = '" +st+"'");
      	Producto p = (Producto) query.getSingleResult();
		//manager.close();
		return p;
	}
	/*
	public void remove(Producto producto) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(producto) ? producto : manager.merge(producto));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	
	public void remove(Producto producto) {
		manager.getTransaction().begin();
		producto.setBorrado(true);
		manager.merge(producto);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(Producto productoModificado) {
		manager.getTransaction().begin();
		manager.merge(productoModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
