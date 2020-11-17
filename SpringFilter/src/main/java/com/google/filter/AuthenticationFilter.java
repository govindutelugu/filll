package com.google.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.google.dao.PrivilegesMapping;
import com.google.vo.AppRoles;
import com.google.vo.PrivilegeVo;


@Component
@Order(1)
public class AuthenticationFilter implements Filter{

	@Autowired
	LoggerMessage logger;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=null;
		httpRequest= (HttpServletRequest) request;
		logger.debug("Filter: URL called from AuthenticationFilter call.: "+httpRequest.getRequestURL().toString());
		
		StringBuffer url = httpRequest.getRequestURL();
		String uri = httpRequest.getRequestURI();
		String contextObj = httpRequest.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + contextObj.length()) + "/";
		logger.debug("base value"+base);
		logger.debug("contextObj value***"+contextObj);
		PrivilegeVo priviligeObje= PrivilegesMapping.getUserPrivileges("Govindu Telugu");
		
		List<AppRoles> appAccess= priviligeObje.getAppRole();
		
		boolean flag=false;
		for(AppRoles rolesObj:appAccess) {
			if(uri.contains(rolesObj.getAppName().toLowerCase())) {
				flag=true;
			}
		}
		if(flag) {
			response.setContentType("Success");
		}else {response.setContentType("Fail");}
		
		chain.doFilter(request, response);
	}

}
