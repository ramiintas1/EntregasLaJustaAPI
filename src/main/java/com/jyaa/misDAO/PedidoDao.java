package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misInterfaces.IPedidoDao;
import com.jyaa.misclases.Pedido;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Zona;

import jakarta.inject.Inject;

@Service
public class PedidoDao implements IPedidoDao {
	
	@Inject EntityManager manager;
	
	public void save(Pedido pedido) {
		manager.getTransaction().begin();
		manager.persist(pedido);
		manager.getTransaction().commit();
	}
	
	public Pedido find(long l) {			
		manager.getTransaction().begin();
		Pedido p = manager.find(Pedido.class, l);
		manager.getTransaction().commit();
		return p;			
	}
	
	public PedidoDto findDto(long l) {			
		manager.getTransaction().begin();
		Pedido p = manager.find(Pedido.class, l);
		if(p.getEntrega() != null) {
			//PedidoDto pedido = new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId());
			//PedidoDto pedido = new PedidoDto(p.getIdJusta(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId());
			PedidoDto pedido = new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId(),p.getIdJusta());
			manager.getTransaction().commit();
			return pedido;			
		}else{
			//PedidoDto pedido = new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0);
			//PedidoDto pedido = new PedidoDto(p.getIdJusta(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0);
			PedidoDto pedido = new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0,p.getIdJusta());
			manager.getTransaction().commit();
			return pedido;	
		}		
	}
	
	public boolean validar(long l) {
		List<Pedido> pedidos = (List<Pedido>) manager.createQuery("FROM Pedido").getResultList();
		for(Pedido u: pedidos) {
			if(l == u.getIdJusta()) {
				return false;
			}
		}
		return true;
	}
	
	public List<PedidoDto> findall(){
		manager.getTransaction().begin();
		List<Pedido> pedidos  = (List<Pedido>) manager.createQuery("FROM Pedido").getResultList();
		List<PedidoDto> listaPedidos = new ArrayList<>();
		for(Pedido p: pedidos) {
			if(!p.isBorrado()) {
				if(p.getEntrega() != null) {
					//listaPedidos.add(new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId()));
					//listaPedidos.add(new PedidoDto(p.getIdJusta(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId()));
					listaPedidos.add(new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),p.getEntrega().getId(),p.getIdJusta()));
				}else {
					//listaPedidos.add(new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0));
					//listaPedidos.add(new PedidoDto(p.getIdJusta(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0));
					listaPedidos.add(new PedidoDto(p.getId(),p.getDescripcion(),p.getMonto(),p.getDniCliente(),null,p.getNombreCliente(),null,p.getCalle(),p.getEntreCalles(),p.getNumero(),p.getDepartamento(),p.getLatitud(),p.getLongitud(),p.getDescripcion(),0,p.getIdJusta()));
				}
			}
		}
		manager.getTransaction().commit();
		return listaPedidos;
	}
	
	public void remove(Pedido pedido) {
		manager.getTransaction().begin();
		pedido.setBorrado(true);
		manager.merge(pedido);
		manager.getTransaction().commit();
	}
	
	public void modify(Pedido pedidoModificado) {
		manager.getTransaction().begin();
		manager.merge(pedidoModificado);
		manager.getTransaction().commit();
	}
}
