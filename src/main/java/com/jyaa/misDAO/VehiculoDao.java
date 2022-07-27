package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.VehiculoDto;
import com.jyaa.misInterfaces.IVehiculoDao;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Vehiculo;
import com.jyaa.misclases.Zona;

import jakarta.inject.Inject;

@Service
public class VehiculoDao implements IVehiculoDao{
	
	@Inject EntityManager manager;

	public void save(Vehiculo vehiculo) {
		manager.getTransaction().begin();		
		manager.persist(vehiculo);		
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public Vehiculo find(long l) {
		manager.getTransaction().begin();
		Vehiculo v = manager.find(Vehiculo.class, l);
		manager.getTransaction().commit();
		//manager.close();
		return v;
	}
	
	public List<VehiculoDto> findall(){
		manager.getTransaction().begin();
		List<Vehiculo> vehiculo  = (List<Vehiculo>) manager.createQuery("FROM Vehiculo").getResultList();
		List<VehiculoDto> listaVehiculo = new ArrayList<>();
		for(Vehiculo v: vehiculo) {
			if(!v.isBorrado()) {
				listaVehiculo.add(new VehiculoDto(v.getTipo(),v.getPesoMaximo(),v.isCombustible()));
			}
		}
		manager.getTransaction().commit();
		//manager.close();
		return listaVehiculo;
	}
	
	public Vehiculo find(String st) {
		
		Query query = manager.createQuery("Select v from Vehiculo v where v.tipo = '" +st+"'");
		Vehiculo v = (Vehiculo) query.getSingleResult();
		//manager.close();
		return v;
	}
	
	public List<Usuario> findUs(String st) {
		Query query = manager.createQuery("Select v.usuarios from Vehiculo v where v.tipo = '" +st+"'");
      	List<Usuario> us = (List<Usuario>) query.getResultList();
		//manager.close();
		return us;
	}
	/*
	public void remove(Vehiculo vehiculo) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(vehiculo) ? vehiculo : manager.merge(vehiculo));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	public void remove(Vehiculo vehiculo) {
		manager.getTransaction().begin();
		vehiculo.setBorrado(true);
		manager.merge(vehiculo);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(Vehiculo vehiculoModificado) {
		manager.getTransaction().begin();
		manager.merge(vehiculoModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
