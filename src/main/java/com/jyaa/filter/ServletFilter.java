package com.jyaa.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/ServletFilter")
public class ServletFilter implements Filter {

    public ServletFilter() {
    	
    }
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Origin", "GET, POST, DELETE, PUT");
		res.addHeader("Access-Control-Allow-Origin", "Context-Type, aoi_key, Authorization");
		chain.doFilter(request, response);
	}
	
    @Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
