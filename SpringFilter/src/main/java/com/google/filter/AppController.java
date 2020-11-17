package com.google.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AppController {
	@Autowired
	LoggerMessage logger;
	
	@GetMapping("*/*/*/*")
	public String validateResponse(HttpServletRequest request,HttpServletResponse response)
	{	
		String jsonString ="";
		ResponseObject responseObj = new ResponseObject();
		try {
		if(response.getContentType().equalsIgnoreCase("Success")) {
			responseObj.setResponse("Success");
			responseObj.setUserAccess("User have access.");
		} else {
			responseObj.setResponse("Failure 403");
			responseObj.setUserAccess("User dont have access.");
		}
		ObjectMapper mapper = new ObjectMapper();
		 jsonString = mapper.writeValueAsString(responseObj);
		 
		 return jsonString;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
		
	}
}
