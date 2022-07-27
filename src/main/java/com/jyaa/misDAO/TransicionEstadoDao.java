package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.TransicionEstadoDto;
import com.jyaa.misInterfaces.ITransicionEstadoDao;
import com.jyaa.misclases.TransicionEstado;

import jakarta.inject.Inject;

@Service
public class TransicionEstadoDao implements ITransicionEstadoDao{
	
	@Inject EntityManager manager;

	public void save(TransicionEstado estado) {		
		manager.getTransaction().begin();
		manager.persist(estado);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public TransicionEstado find(long l) {
		manager.getTransaction().begin();
		TransicionEstado e = manager.find(TransicionEstado.class, l);
		manager.getTransaction().commit();
		//manager.close();
		return e;
	}
	
	public List<TransicionEstado> findEntr(long l) {
		Query query = manager.createQuery("Select e from TransicionEstado e where e.entrega = '" +l+"'");
		List<TransicionEstado> e = (List<TransicionEstado>) query.getResultList();
		//manager.close();
		return e;
	}
	
	public List<TransicionEstadoDto> findall(){
		manager.getTransaction().begin();
		List<TransicionEstado> transiciones = (List<TransicionEstado>) manager.createQuery("FROM TransicionEstado").getResultList();
		List<TransicionEstadoDto> t = new ArrayList<>();
		for(TransicionEstado en: transiciones) {
			if(!en.isBorrado()) {
				t.add(new TransicionEstadoDto(en.getId(),en.getActual().getNombre(),en.getFecha(),en.getMotivo(),en.getEntrega().getId()));
			}		
		}	
		manager.getTransaction().commit();
		//manager.close();	
		return t;
	}
	/*
	public void remove(TransicionEstado estado) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(estado) ? estado : manager.merge(estado));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	public void remove(TransicionEstado estado) {
		manager.getTransaction().begin();
		estado.setBorrado(true);
		manager.merge(estado);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(TransicionEstado estadoModificado) {
		manager.getTransaction().begin();
		manager.merge(estadoModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
