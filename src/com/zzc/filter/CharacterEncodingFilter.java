package com.zzc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements javax.servlet.Filter {

	String encodeString = null;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if(null != encodeString){
			request.setCharacterEncoding(encodeString);
			response.setCharacterEncoding(encodeString);
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encodeString = filterConfig.getInitParameter("encoding");
	}
	
}
