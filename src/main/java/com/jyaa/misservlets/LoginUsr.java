package com.jyaa.misservlets;

import java.io.IOException;
import java.util.Hashtable;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginUsr")
public class LoginUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Hashtable<String , Usuario> login = new Hashtable();
	private int cantidadUs;
	private String acceso;
	private Usuario user_aux;

    public LoginUsr() {
    }
    
    /*Se toman los valores correspondientes a los usuarios del archivo .xml y se alojan en una tabla de hash*/
    public void init(ServletConfig config) throws ServletException{
    	cantidadUs = Integer.parseInt(config.getInitParameter("cantidad"));
    	for (int i = 0; i < cantidadUs ; i++ ) {
    		Usuario usuario = new Usuario (config.getInitParameter("us"+i), config.getInitParameter("pass"+i), config.getInitParameter("role"+i));
    		login.put(config.getInitParameter("us"+i), usuario);
    	}
    }
    
    /*Se verifica si los datos ingresados en el formulario son correctos. Si es asi se guardan datos del usuario*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String us_ingresado = request.getParameter("user");
		String cont_ingresada = request.getParameter("password");
		
		user_aux = login.get(us_ingresado);
		if((user_aux != null) && (user_aux.getPassword().equals(cont_ingresada))) {
			HttpSession sesion = request.getSession(true);

			sesion.setAttribute("usuario", user_aux);
			if(user_aux.getRole().equals("Administrador")) {
				response.sendRedirect("Administrador.html");
			}else if (user_aux.getRole().equals("Repartidor")){
				response.sendRedirect("Repartidor.html");
			}else {
				response.sendRedirect("login.html");
			}		
		}else {
			response.sendRedirect("login.html");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
