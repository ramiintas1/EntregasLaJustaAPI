package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.EstadoEntregaDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IEstadoEntregaDao;
import com.jyaa.misclases.EstadoEntrega;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Zona;

import jakarta.inject.Inject;

@Service
public class EstadoEntregaDao implements IEstadoEntregaDao {
	
	@Inject EntityManager manager;
	
	public void save(EstadoEntrega estado) {		
		manager.getTransaction().begin();		
		manager.persist(estado);		
		manager.getTransaction().commit();
		//manager.close();	
	}
	
	public EstadoEntrega find(long l) {
		manager.getTransaction().begin();
		EstadoEntrega e = manager.find(EstadoEntrega.class, l);		
		manager.getTransaction().commit();
		//manager.close();
		return e;
	}
	
	public EstadoEntrega find(String st) {
		Query query = manager.createQuery("Select e from EstadoEntrega e where e.nombre = '" +st+"'");
		EstadoEntrega e = (EstadoEntrega) query.getSingleResult();
		//manager.close();
		return e;
	}
	
	public List<EstadoEntregaDto> findall(){
		manager.getTransaction().begin();
		List<EstadoEntrega> estados  = (List<EstadoEntrega>) manager.createQuery("FROM EstadoEntrega").getResultList();
		List<EstadoEntregaDto> listaEstados = new ArrayList<>();
		for(EstadoEntrega e: estados) {
			if(!e.isBorrado()) {
				listaEstados.add(new EstadoEntregaDto(e.getId(),e.getNombre()));
			}		
		}
		manager.getTransaction().commit();
		//manager.close();
		return listaEstados;
	}
	/*
	public void remove(EstadoEntrega estado) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(estado) ? estado : manager.merge(estado));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	
	public void remove(EstadoEntrega estado) {
		manager.getTransaction().begin();
		estado.setBorrado(true);
		manager.merge(estado);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(EstadoEntrega estadoModificado) {
		manager.getTransaction().begin();
		manager.merge(estadoModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
