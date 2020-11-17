package com.google.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.connection.DBConnectionObject;
import com.google.vo.AppRoles;
import com.google.vo.PrivilegeResponseVo;
import com.google.vo.PrivilegeVo;
import com.google.vo.PrivilegesVo;
import com.google.vo.UserRoles;

public class PrivilegesMapping {

	public static PrivilegeVo getUserPrivileges(String userId) {
		List<UserRoles> rolesObj =  new ArrayList<UserRoles>();
		List<PrivilegesVo> privilegeList =  new ArrayList<PrivilegesVo>();
		List<String> privilgeArray =  new ArrayList<String>();
		List<AppRoles> appRole =  new ArrayList<AppRoles>();
		PrivilegeVo privilegeObj = new PrivilegeVo();
		try{  
			Connection dbConnectionObj=DBConnectionObject.getMysqlDBconnection();
			
			Statement privilegestmt=dbConnectionObj.createStatement();
			String sqlQuery = "select privilege_id,description from privilege_group;";
			ResultSet privilegeResultSet= privilegestmt.executeQuery(sqlQuery);
			
			String preparedstatementQuery = "select pg.description,up.role,u.name,up.privilege_group_id from USER_PRIVILEGES up,privilege_group pg,user u where up.user_id=(select user_id from user where name=?)\n"
											 + "and pg.privilege_id=up.privilege_group_id\n"
											 + "and u.user_id = up.user_id;";
			PreparedStatement userRolePrepareStatment=dbConnectionObj.prepareStatement(preparedstatementQuery);  
			userRolePrepareStatment.setString(1,userId);
			ResultSet userRoleRs=userRolePrepareStatment.executeQuery(); 
			
			while(privilegeResultSet.next()) {
				PrivilegesVo privilegevoObj = new PrivilegesVo();
				privilegevoObj.setPrivilegeId(privilegeResultSet.getInt(1));
				privilegevoObj.setPrivilegeName(privilegeResultSet.getString(2));
				privilgeArray.add(privilegeResultSet.getString(2));
				privilegeList.add(privilegevoObj);
			}
			
			while(userRoleRs.next()) { 
				UserRoles userRoleObj = new UserRoles();
				userRoleObj.setApp(userRoleRs.getString(1));
				userRoleObj.setRoles(userRoleRs.getString(2));
				userRoleObj.setPrivileges("Apps_"+userRoleRs.getString(2));
				rolesObj.add(userRoleObj);
				AppRoles approleObj = new AppRoles();
				approleObj.setAppName(userRoleRs.getString(1));
				approleObj.setRolesName(userRoleRs.getString(2));
				approleObj.setPrivilege(userRoleRs.getString(3));
				approleObj.setPrivilgeId(userRoleRs.getInt(4));
				appRole.add(approleObj);
			}
			 Iterator<PrivilegesVo> privilegeIterator =privilegeList.iterator();
			 List<String> appName = new ArrayList<String>();
			 List<PrivilegeResponseVo> privilegeResponse =  new ArrayList<PrivilegeResponseVo>();
				 while(privilegeIterator.hasNext()) {
					 PrivilegesVo privilegeVoObj = (PrivilegesVo)privilegeIterator.next();
					 List<String> userRolesList= new ArrayList<String>();
					 for (int k=0;appRole.size()>k;k++) {
						 AppRoles appRolesObj = (AppRoles)appRole.get(k);
						 if( privilegeVoObj.getPrivilegeId()==appRolesObj.getPrivilgeId()) {
							 userRolesList.add(appRolesObj.getRolesName());
							 appName.add(appRolesObj.getAppName());
						 }
					 }
					 if(userRolesList.size()>0) {
						 PrivilegeResponseVo responseObj= new PrivilegeResponseVo();
						 responseObj.setApp(privilegeVoObj.privilegeName);
						 responseObj.setPrivilegeList(userRolesList);
						 privilegeResponse.add(responseObj);
					 }
					 
				 }
				 
			System.out.println("Printing user roles List."+privilegeResponse);
			
			if(dbConnectionObj!=null) {dbConnectionObj.close();}
			
			privilegeObj.setApps(privilgeArray);
			privilegeObj.setAppRoles(rolesObj);
			privilegeObj.setAppPrivileges(privilegeResponse);
			privilegeObj.setAppRole(appRole);
			return privilegeObj;
			}catch(Exception e){
				e.printStackTrace();
			}  
			return privilegeObj;
	}
}
