package com.google.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeVo {

	
	public List<String> apps;
	public List<UserRoles> appRoles;
	
	@JsonProperty("appPrivileges")
	public List<PrivilegeResponseVo> appPrivileges;
	
	public String response;
	public List<String> getApps() {
		return apps;
	}
	public void setApps(List<String> apps) {
		this.apps = apps;
	}
	

	public List<UserRoles> getAppRoles() {
		return appRoles;
	}
	public void setAppRoles(List<UserRoles> appRoles) {
		this.appRoles = appRoles;
	}
	
	public List<PrivilegeResponseVo> getAppPrivileges() {
		return appPrivileges;
	}
	public void setAppPrivileges(List<PrivilegeResponseVo> appPrivileges) {
		this.appPrivileges = appPrivileges;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	


		
	
}
