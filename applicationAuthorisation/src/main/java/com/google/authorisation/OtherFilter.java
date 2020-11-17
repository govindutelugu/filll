
package com.google.authorisation;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class OtherFilter implements Filter{

	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=null;
		
		httpRequest= (HttpServletRequest) request;
		HttpServletResponse  myResponse= (HttpServletResponse) response;
		System.out.println("OtherFilter: URL"
				+ " called: "+httpRequest.getRequestURL().toString());
		
		if (myResponse.getHeader("PROFE")!=null)
			System.out.println("OtherFilter: Header contains PROFE: "+myResponse.getHeader("PROFE"));		

		chain.doFilter(request, response);
	}

}