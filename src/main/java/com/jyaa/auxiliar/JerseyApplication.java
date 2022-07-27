package com.jyaa.auxiliar;

import org.glassfish.jersey.server.ResourceConfig;

import com.jyaa.utils.MyApplicationBinder;

public class JerseyApplication extends ResourceConfig {
	 public JerseyApplication() {
		 packages("com.jyaa.rest.recursos");
		 register(new MyApplicationBinder());
	 }
}
