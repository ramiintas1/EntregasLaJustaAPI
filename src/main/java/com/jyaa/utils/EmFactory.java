package com.jyaa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Contract;

public class EmFactory implements Factory<EntityManager>{
	
	public EntityManager provide() {
		EntityManager manager;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia2");
		manager = emf.createEntityManager();

		return manager;
	}
	
	public void dispose(EntityManager manager){

		manager.close();
	}

}
