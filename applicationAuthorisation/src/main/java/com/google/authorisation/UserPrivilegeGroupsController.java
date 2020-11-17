package com.google.authorisation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.dao.MysqlDataObject;
import com.google.vo.PrivilegeVo;
import com.google.vo.ResponseObject;

@RestController
public class UserPrivilegeGroupsController {


    @RequestMapping("/eesy/portal/userPrivileges/{userId}")
    public String getUserRoles(@PathVariable("userId") String userId ) {
        String jsonString="";
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	PrivilegeVo userRoles= MysqlDataObject.getUserPrivileges(userId);
           if(userRoles.getAppRoles().size()>0) {
        	   userRoles.setResponse("Success.");
        	   jsonString = mapper.writeValueAsString(userRoles);
        	   return jsonString;
           }else {
        	   userRoles.setApps(new ArrayList<String>());
        	   userRoles.setResponse("User does not exist 401 error ");
        	   jsonString = mapper.writeValueAsString(userRoles);
        	   return jsonString;
           }
         } catch (Exception e) {
            e.printStackTrace();
         }
        return jsonString;
    }



    @GetMapping("redirected")
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
