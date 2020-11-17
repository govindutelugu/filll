package com.google.authorisation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.dao.MysqlDataObject;
import com.google.vo.PrivilegeVo;

@RestController
public class UserRolesController {

    @RequestMapping("/UserRoles/{userId}")
    public String getUserRoles(@PathVariable("userId") String userId ) {
        PrivilegeVo userRoles = new PrivilegeVo();
        String jsonString="";
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	userRoles= MysqlDataObject.getUserRoles(userId);
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

}
