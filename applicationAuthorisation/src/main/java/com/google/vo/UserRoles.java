package com.google.vo;

public class UserRoles {
	public String app;
	public String roles;
	public String privileges;
	
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	
	@Override
	    public String toString(){
		 return "app"+app+"roles"+roles+"privileges"+privileges;
	    }

}
