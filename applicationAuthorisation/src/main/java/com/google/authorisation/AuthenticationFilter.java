package com.google.authorisation;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.dao.PrivilegesMapping;
import com.google.vo.PrivilegeVo;
import com.google.vo.UserRoles;


@Component
@Order(1)
public class AuthenticationFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=null;
		httpRequest= (HttpServletRequest) request;
		HttpServletResponse  myResponse= (HttpServletResponse) response;
		System.out.println("Filter: URL called from AuthenticationFilter call.: "+httpRequest.getRequestURL().toString());
		
		StringBuffer url = httpRequest.getRequestURL();
		String uri = httpRequest.getRequestURI();
		String ctx = httpRequest.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
		System.out.println("base value"+base);
		PrivilegeVo priviligeObje= PrivilegesMapping.getUserPrivileges("Govindu Telugu");
		
		List<UserRoles> appAccess= priviligeObje.getAppRoles();
		
		boolean flag=false;
		for(UserRoles rolesObj:appAccess) {
			if(uri.contains(rolesObj.getApp().toLowerCase())) {
				flag=true;
			}
		}
		System.out.println("Printing flag value*******:"+flag);
		if(flag) {
			myResponse.setContentType("Success");
			chain.doFilter(request, myResponse);
			return;
		}
		else {
			myResponse.setContentType("fail");
			myResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
		    myResponse.getOutputStream().flush();
			myResponse.getOutputStream().println("-- User Dont have any privileges to this Application --");
	        return; // No hago nada.
		}
		
		//chain.doFilter(request, response);
	}

}
