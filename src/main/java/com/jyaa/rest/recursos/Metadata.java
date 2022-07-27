package com.jyaa.rest.recursos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(title = "Swagger de Trabajo Final", version = "1.0.0", description = "Swagger para el manejo de Repartidores y Entregas" ),
		servers = @Server(url ="/TrabajoFinal/api"))
public class Metadata {

}
