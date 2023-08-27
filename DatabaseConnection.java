package Petmanagement;

import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;




public class DatabaseConnection {
	  
	public static Connection getConnection() {
		Connection con;
	try {
		
		  String URL = "jdbc:mysql://localhost:3306/pet_db";
		  String Username = "root";
		  String Password = "Mrunal@2001";
		 con = DriverManager.getConnection(URL,Username,Password);
		
		return con;
	}
	catch (Exception e) {
		System.out.println(e);
	}
	
	return null;
	
}
}

