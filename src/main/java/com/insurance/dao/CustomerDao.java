package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.insurance.interfaces.ICustomerDao;
import com.insurance.models.Customer;

public class CustomerDao implements ICustomerDao {
	
	private Connection connection = null;
	
	public CustomerDao(Connection connection) {
		this.connection = connection;
	}
	
	private void checkCustomerExist(int customerId) {
		try {
		String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Customer with ID " + customerId + " does not exist.");
                return;
            }
        } catch (Exception e) {
        	System.out.println("Some error occured");
        }
	}

	@Override
	public void registerCustomer(Customer customer) {
		try {
			
			String sql = "INSERT INTO Customer (name, email, phone_number, address) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getPhoneNumber());
			preparedStatement.setString(4, customer.getAddress());
			
			int rowInserted = preparedStatement.executeUpdate();
			if (rowInserted > 0) {
				System.out.println("Customer Registered Successfully");
			}
			
		} catch (Exception e) {
			System.out.println("Some error occured while regestring customer");
			e.printStackTrace();
		}
	}

	@Override
	public void viewCustomer(int customerId) {
		try {

			String sql = "SELECT * FROM Customer WHERE customer_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			
			ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
            	System.out.println("Customer ID: " + result.getInt("customer_id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Email: " + result.getString("email"));
                System.out.println("Phone Number: " + result.getString("phone_number"));
                System.out.println("Address: " + result.getString("address"));
            } else {
                System.out.println("Customer not found.");
            }
			
		} catch (Exception e) {
			System.out.println("Some error occured while viewing Customer");
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomer(int customerId, Customer customer) {
		try {
			
			// check if customer exist
			this.checkCustomerExist(customerId);
			
			// update customer 
			String sql = "UPDATE Customer SET name = ?, email = ?, phone_number = ?, address = ? WHERE customer_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getPhoneNumber());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setInt(5, customerId);
			
			int rowUpdated = preparedStatement.executeUpdate();
			if (rowUpdated > 0) {
				System.out.println("Customer Updated Successfully");
			} else {
				System.out.println("Customer Not Updated");
			}
			
		} catch (Exception e) {
			System.out.println("Some error occured while updating customer");
		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		
	}
	
}
