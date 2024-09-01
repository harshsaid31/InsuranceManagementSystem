package com.insurance.utils;

import java.sql.*;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/insurance";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Mysql Driver not found " + e.getMessage());
		}
		
		return connection;
	}
	
}
