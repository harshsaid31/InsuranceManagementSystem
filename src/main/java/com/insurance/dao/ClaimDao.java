package com.insurance.dao;

import java.sql.Connection;

public class ClaimDao {
	
	private Connection connection = null;
	
	public ClaimDao(Connection connection) {
		this.connection = connection;
	}

}
