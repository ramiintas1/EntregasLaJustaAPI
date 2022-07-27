package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.EntregaDto;
import com.jyaa.misDTO.PedidoDto;
import com.jyaa.misInterfaces.IEntregaDao;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.Pedido;

import jakarta.inject.Inject;

@Service
public class EntregaDao implements IEntregaDao {
	
	@Inject EntityManager manager;

	public void save(Entrega entrega) {		
		manager.getTransaction().begin();
		manager.persist(entrega);
		manager.getTransaction().commit();
	}
	
	public Entrega find(long l) {
		manager.getTransaction().begin();
		Entrega e = manager.find(Entrega.class, l);		
		manager.getTransaction().commit();
		return e;
	}
	
	public EntregaDto findDto(long l) {			
		manager.getTransaction().begin();
		Entrega en = manager.find(Entrega.class, l);	
		//EntregaDto entrega = new EntregaDto(en.getId(),en.getZona().getBarrio(),en.getRepartidor().getDni(),en.getFecha(),en.getMotivo(),en.getPedido().getId(),en.getEstados().get(en.getEstados().size()-1).getActual().getNombre());
		EntregaDto entrega = new EntregaDto(en.getId(),en.getZona().getBarrio(),en.getRepartidor().getDni(),en.getFecha(),en.getMotivo(),en.getIdJusta(),en.getEstados().get(en.getEstados().size()-1).getActual().getNombre());
		manager.getTransaction().commit();
		return entrega;			
	}
	
	public List<Entrega> findall(){
		return null;
	}
	
	public List<EntregaDto> findallEntregas(){
		manager.getTransaction().begin();
		List<Entrega> e = (List<Entrega>) manager.createQuery("FROM Entrega").getResultList();
		List<EntregaDto> e2 = new ArrayList<>();
		for(Entrega en: e) {
			if(!en.isBorrado()) {
				//e2.add(new EntregaDto(en.getId(),en.getZona().getBarrio(),en.getRepartidor().getDni(),en.getFecha(),en.getMotivo(),en.getPedido().getId(),en.getEstados().get(en.getEstados().size()-1).getActual().getNombre()));
				e2.add(new EntregaDto(en.getId(),en.getZona().getBarrio(),en.getRepartidor().getDni(),en.getFecha(),en.getMotivo(),en.getIdJusta(),en.getEstados().get(en.getEstados().size()-1).getActual().getNombre()));
			}
		}	
		manager.getTransaction().commit();
		return e2;
	}
	
	public void remove(Entrega entrega) {	
		manager.getTransaction().begin();
		entrega.setBorrado(true);
		manager.merge(entrega);
		manager.getTransaction().commit();
	}
	
	public void modify(Entrega entregaModificado) {	
		manager.getTransaction().begin();
		manager.merge(entregaModificado);
		manager.getTransaction().commit();
	}
}
