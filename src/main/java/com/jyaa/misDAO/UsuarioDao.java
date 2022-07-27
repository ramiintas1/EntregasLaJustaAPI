package com.jyaa.misDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jvnet.hk2.annotations.Service;

import com.jyaa.misDTO.LoginDto;
import com.jyaa.misDTO.UsuarioDto;
import com.jyaa.misInterfaces.IEstadoRepartidorDao;
import com.jyaa.misInterfaces.IRolDao;
import com.jyaa.misInterfaces.IUsuarioDao;
import com.jyaa.misInterfaces.IVehiculoDao;
import com.jyaa.misclases.Entrega;
import com.jyaa.misclases.Usuario;

import jakarta.inject.Inject;

@Service
public class UsuarioDao implements IUsuarioDao{
	
	@Inject EntityManager manager;
	
	@Inject IRolDao rolDao;
	@Inject IVehiculoDao vehiculoDao;
	@Inject IEstadoRepartidorDao estadoDao;

	public void save(Usuario usuario) {	
		manager.getTransaction().begin();		
		manager.persist(usuario);		
		manager.getTransaction().commit();
	}
	
	public UsuarioDto find(long l) {
		
		manager.getTransaction().begin();
		Usuario us = manager.find(Usuario.class, l);
		UsuarioDto us2 = null;
		if(us != null) {	
			if((us.getRol() != null)&&(us.getEstado() != null)&&(us.getVehiculo() != null)) {
				us2 = new UsuarioDto(us.getId(),us.getNombre(),us.getApellido(),us.getRol().getNombre(),us.getDni(),us.getEmail(),us.getDireccion(),us.getContrasena(),us.getFechaN(),us.getVehiculo().getTipo(),us.getEstado().getNombre(),us.getZonaAsignada().getBarrio());
			}else {
				us2 = new UsuarioDto(us.getId(),us.getNombre(),us.getApellido(),us.getRol().getNombre(),us.getDni(),us.getEmail());
			}
		}
		manager.getTransaction().commit();
		return us2;	
	}
	
	public Usuario findDNI(String st1) {		
		Query query = manager.createQuery("Select u from Usuario u where u.dni = '" +st1+"'");
      	Usuario us = (Usuario) query.getSingleResult();	
		return us;
	}
	
	public Usuario findEmailCont(LoginDto login) {		
		Query query = manager.createQuery("Select u from Usuario u where u.email = '" +login.getUsuario()+"' and u.contrasena = '"+login.getContrasena()+"'");
      	Usuario us = (Usuario) query.getSingleResult();	
		return us;
	}
	
	public Usuario findUs(long l) {		
		manager.getTransaction().begin();
		Usuario us = manager.find(Usuario.class, l);
		manager.getTransaction().commit();
		return us;		
	}
	
	public List<UsuarioDto> findall(){		
		manager.getTransaction().begin();
		List<Usuario> us = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
		List<UsuarioDto> us2 = new ArrayList<>();
		for(Usuario u: us) {
			if(!u.isBorrado()) {
				us2.add(new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN(),u.getVehiculo().getTipo(),u.getEstado().getNombre(),u.getZonaAsignada().getBarrio()));
			}	
		}	
		manager.getTransaction().commit();	
		return us2;
	}
	
	public List<UsuarioDto> findallAdm(){	
		manager.getTransaction().begin();
		List<Usuario> us = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
		List<UsuarioDto> us2 = new ArrayList<>();
		for(Usuario u: us) {
			if(!u.isBorrado()) {
				us2.add(new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN()));
			}
		}	
		manager.getTransaction().commit();
		return us2;
	}
	
	public List<UsuarioDto> findallRep(){	
		manager.getTransaction().begin();
		List<Usuario> us = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
		List<UsuarioDto> us2 = new ArrayList<>();
		for(Usuario u: us) {
			if((u.getRol() != null)||(u.getEstado() != null)||(u.getVehiculo() != null)) {
				if(!u.isBorrado()) {
					us2.add(new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN(),u.getVehiculo().getTipo(),u.getEstado().getNombre(),u.getZonaAsignada().getBarrio()));
				}
			}
		}	
		manager.getTransaction().commit();
		return us2;
	}
	
	public UsuarioDto find(String st1, String st2) {	
		Query query = manager.createQuery("Select u from Usuario u where u.nombre = '" +st1+"' and u.apellido = '"+st2+"'");
      	Usuario u = (Usuario) query.getSingleResult();
      	UsuarioDto us2 = new UsuarioDto(u.getId(),u.getNombre(),u.getApellido(),u.getRol().getNombre(),u.getDni(),u.getEmail(),u.getDireccion(),u.getContrasena(),u.getFechaN(),u.getVehiculo().getTipo(),u.getEstado().getNombre(),u.getZonaAsignada().getBarrio());
		return us2;
	}
	
	public List<Entrega> findEntregas(String st1, String st2) {
		Query query = manager.createQuery("Select u.entregas from Usuario u where u.nombre = '" +st1+"' and u.apellido = '"+st2+"'");
      	List<Entrega> e = (List<Entrega>) query.getResultList();
		return e;
	}
	
	public boolean validar(LoginDto login) {
		List<Usuario> usuarios = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
		for(Usuario u: usuarios) {
			if((login.getUsuario().equals(u.getEmail()))&&(login.getContrasena().equals(u.getContrasena()))) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean validarUs(UsuarioDto us) {
		List<Usuario> usuarios = (List<Usuario>) manager.createQuery("FROM Usuario").getResultList();
		for(Usuario u: usuarios) {
			if((us.getEmail().equals(u.getEmail()))&&(us.getContrasena().equals(u.getContrasena()))) {
				return false;
			}
		}
		return true;
	}
	
	public void remove(UsuarioDto u) {	
		manager.getTransaction().begin();
		Usuario us = manager.find(Usuario.class, u.getId());
		us.setBorrado(true);
		manager.merge(us);
		manager.getTransaction().commit();
	}
	
	
	public void modify(UsuarioDto usModificado) {
		
		manager.getTransaction().begin();
		
		Usuario usuario = manager.find(Usuario.class, usModificado.getId());
		usuario.setNombre(usModificado.getNombre());
		usuario.setApellido(usModificado.getApellido());
		usuario.setRol(rolDao.find(usModificado.getRol()));
		usuario.setDni(usModificado.getDni());
		usuario.setDireccion(usModificado.getDireccion());
		usuario.setEmail(usModificado.getEmail());
		usuario.setFechaN(usModificado.getFechaN());
		usuario.setEstado(estadoDao.find(usModificado.getEstado()));
		usuario.setVehiculo(vehiculoDao.find(usModificado.getVehiculo()));
		usuario.setContrasena(usModificado.getContrasena());
		
		manager.merge(usuario);
		manager.getTransaction().commit();
	}
	
}
