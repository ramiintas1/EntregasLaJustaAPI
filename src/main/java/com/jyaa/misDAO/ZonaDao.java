package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.VehiculoDto;
import com.jyaa.misDTO.ZonaDto;
import com.jyaa.misInterfaces.IZonaDao;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Usuario;
import com.jyaa.misclases.Vehiculo;
import com.jyaa.misclases.Zona;

import jakarta.inject.Inject;

@Service
public class ZonaDao implements IZonaDao {
		
	@Inject EntityManager manager;

	public void save(Zona zona) {
		manager.getTransaction().begin();
		manager.persist(zona);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public Zona find(long l) {	
		manager.getTransaction().begin();
		Zona z = manager.find(Zona.class, l);
		manager.getTransaction().commit();
		//manager.close();
		return z;
	}
	
	public List<ZonaDto> findall(){
		manager.getTransaction().begin();
		List<Zona> zona  = (List<Zona>) manager.createQuery("FROM Zona").getResultList();
		List<ZonaDto> listaZona = new ArrayList<>();
		for(Zona z: zona) {
			if(!z.isBorrado()) {
				listaZona.add(new ZonaDto(z.getId(),z.getBarrio(),z.getDesdeLat(),z.getHastaLat(),z.getDesdeLong(),z.getHastaLong()));
			}
		}
		manager.getTransaction().commit();
		//manager.close();
		return listaZona;
	}
	
	public Zona find(String st) {
		Query query = manager.createQuery("Select z from Zona z where z.barrio = '" +st+"'");
		Zona z = (Zona) query.getSingleResult();	
		//manager.close();
		return z;
	}
	
	public List<Usuario> findUs(String st) {	
		Query query = manager.createQuery("Select z.usuarios from Zona z where z.barrio = '" +st+"'");
      	List<Usuario> us = (List<Usuario>) query.getResultList();
		//manager.close();
		return us;
	}
	
	public Zona find(float lat,float lon){
		List<Zona> zonas  = (List<Zona>) manager.createQuery("FROM Zona").getResultList();
		for(Zona z: zonas){
			if((z.getDesdeLat() < lat)&&(z.getHastaLat() > lat)&&(z.getDesdeLong() < lon)&&(z.getHastaLong() > lon)) {
				return z;
			}
		} 
		return null;
	}
	
	/*
	public void remove(Zona zona) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(zona) ? zona : manager.merge(zona));
		manager.getTransaction().commit();
		//manager.close();
	}
	*/
	public void remove(Zona zona) {
		manager.getTransaction().begin();
		zona.setBorrado(true);
		manager.merge(zona);
		manager.getTransaction().commit();
		//manager.close();
	}
	
	public void modify(Zona zonaModificado) {	
		manager.getTransaction().begin();
		manager.merge(zonaModificado);
		manager.getTransaction().commit();
		//manager.close();
	}
}
