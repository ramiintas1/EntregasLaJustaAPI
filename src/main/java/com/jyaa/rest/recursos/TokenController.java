package com.jyaa.rest.recursos;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jyaa.misclases.Login;
import com.jyaa.misclases.RetrofitClient;
//import com.sun.org.apache.xml.internal.utils.URI;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
//import java.net.URI;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Path("/token-justa")
public class TokenController {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	private String mensaje;
	public static String token;
	
	/*------Guarda token------*/
	@POST
	@Operation(method= ("Guardar Token"), description =("Guarda token del sistema La Justa"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Token()  throws IOException, IOException, URISyntaxException, JAXBException{

		String url = "http://ec2-35-173-57-183.compute-1.amazonaws.com:8080/api/token/generate-token";
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
	    
	    String json = "{\"userName\":\"ramiro2@ramiro.com\",\"userPassword\":\"123456\"}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");

	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response.getStatusLine().getStatusCode());
	    String respuesta = EntityUtils.toString(response.getEntity(), "UTF-8");
	    //System.out.println(respuesta);
		
        if(response.getStatusLine().getStatusCode() == 200) {
        	JSONObject jsontoken = new JSONObject(respuesta);
        	JsonObject objetojson = Json.createObjectBuilder().add("TOKEN",respuesta).build();
        	
        	token = jsontoken.getString("value");
        	System.out.println(token);
        	
        	client.close();
            return Response.status(Response.Status.CREATED).entity(objetojson).build();
        }else {
        	client.close();
        	return Response.status(Response.Status.CONFLICT).build();
        }
	}
}
