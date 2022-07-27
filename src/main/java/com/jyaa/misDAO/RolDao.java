package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.ProductoDto;
import com.jyaa.misDTO.RolDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misclases.Producto;
import com.jyaa.misclases.Rol;
import com.jyaa.misclases.Usuario;

import jakarta.inject.Inject;

@Service
public class RolDao implements IRolDao {
	
	@Inject EntityManager manager;
	
	public void save(Rol rol) {
		manager.getTransaction().begin();		
		manager.persist(rol);		
		manager.getTransaction().commit();
	}
	
	public Rol find(long l) {	
		manager.getTransaction().begin();
		Rol r = manager.find(Rol.class, l);		
		manager.getTransaction().commit();	
		return r;
	}
	
	public Rol find(String st) {
		
		Query query = manager.createQuery("Select r from Rol r where r.nombre = '" +st+"'");
      	Rol r = (Rol) query.getSingleResult();
		return r;
	}
	
	public List<UsuarioDto> findUs(String st) {
		Query query = manager.createQuery("Select r.usuarios from Rol r where r.nombre = '" +st+"'");
      	List<Usuario> us = (List<Usuario>) query.getResultList();
		List<UsuarioDto> us2 = new ArrayList<>();
		for(Usuario u: us) {
			if(!u.isBorrado()) {
				us2.add(new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN(),u.getVehiculo().getTipo(),u.getEstado().getNombre(),u.getZonaAsignada().getBarrio()));
			}
		}	
		return us2;
	}
	
	public List<UsuarioDto> findAdmin(String st) {
		Query query = manager.createQuery("Select r.usuarios from Rol r where r.nombre = '" +st+"'");
      	List<Usuario> us = (List<Usuario>) query.getResultList();
		List<UsuarioDto> us2 = new ArrayList<>();
		for(Usuario u: us) {
			if(!u.isBorrado()) {
				us2.add(new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN()));
			}
		}	
		return us2;
	}
	
	public List<RolDto> findall(){
		manager.getTransaction().begin();
		List<Rol> rol  = (List<Rol>) manager.createQuery("FROM Rol").getResultList();
		List<RolDto> listaRoles = new ArrayList<>();
		for(Rol r: rol) {
			if(!r.isBorrado()) {
				listaRoles.add(new RolDto(r.getId(),r.getNombre()));
			}	
		}
		manager.getTransaction().commit();
		return listaRoles;
	}	
	
	public void remove(Rol r) {
		manager.getTransaction().begin();
		r.setBorrado(true);
		manager.merge(r);
		manager.getTransaction().commit();
	}
	
	public void modify(Rol rModificado) {
		manager.getTransaction().begin();
		manager.merge(rModificado);
		manager.getTransaction().commit();
	}

}
