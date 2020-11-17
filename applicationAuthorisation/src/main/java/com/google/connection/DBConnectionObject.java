package com.google.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionObject {
	public static Connection getMysqlDBconnection() {
		
		Connection connectionObj=null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		connectionObj=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","tiger@123");  
		return connectionObj;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connectionObj;
	}

}
