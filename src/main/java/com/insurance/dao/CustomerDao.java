package com.insurance.dao;

import java.sql.Connection;

public class CustomerDao {
	
	private Connection connection = null;
	
	public CustomerDao(Connection connection) {
		this.connection = connection;
	}
	
}
