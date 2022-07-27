package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.EstadoRepartidorDto;
import com.jyaa.misDTO.RolDto;
import com.jyaa.misInterfaces.IEstadoRepartidorDao;
import com.jyaa.misclases.EstadoRepartidor;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Vehiculo;

import jakarta.inject.Inject;

@Service
public class EstadoRepartidorDao implements IEstadoRepartidorDao {
	
	@Inject EntityManager manager;

	public void save(EstadoRepartidor estado) {
		manager.getTransaction().begin();
		manager.persist(estado);
		manager.getTransaction().commit();
		//manager.close();
	}
	public EstadoRepartidor find(long l) {
		manager.getTransaction().begin();
		EstadoRepartidor e = manager.find(EstadoRepartidor.class, l);
		manager.getTransaction().commit();
		//manager.close();
		return e;
	}
	
	public EstadoRepartidor find(String st) {
		Query query = manager.createQuery("Select e from EstadoRepartidor e where e.nombre = '" +st+"'");
		EstadoRepartidor e = (EstadoRepartidor) query.getSingleResult();
		//manager.close();
		return e;
	}

	public List<Usuario> findUs(String st) {
		Query query = manager.createQuery("Select e.usuarios from EstadoRepartidor e where e.nombre = '" +st+"'");
      	List<Usuario> us = (List<Usuario>) query.getResultList();
		//manager.close();
		return us;
	}
	
	public List<EstadoRepartidorDto> findall(){
		manager.getTransaction().begin();
		List<EstadoRepartidor> estado  = (List<EstadoRepartidor>) manager.createQuery("FROM EstadoRepartidor").getResultList();
		List<EstadoRepartidorDto> listaEstados = new ArrayList<>();
		for(EstadoRepartidor e: estado) {
			if(!e.isBorrado()) {
				listaEstados.add(new EstadoRepartidorDto(e.getNombre()));
			}
		}
		manager.getTransaction().commit();
		//manager.close();
		return listaEstados;
	}
	/*
	public void remove(EstadoRepartidor estado) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(estado) ? estado : manager.merge(estado));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	
	public void remove(EstadoRepartidor estado) {
		manager.getTransaction().begin();
		estado.setBorrado(true);
		manager.merge(estado);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(EstadoRepartidor estadoModificado) {
		manager.getTransaction().begin();
		manager.merge(estadoModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
