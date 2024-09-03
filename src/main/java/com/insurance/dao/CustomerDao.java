package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.insurance.interfaces.ICustomerDao;
import com.insurance.models.Customer;
import com.insurance.utils.GeneralUtilities;

public class CustomerDao implements ICustomerDao {
	
	private Connection connection = null;
	
	public CustomerDao(Connection connection) {
		this.connection = connection;
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
			System.out.println(e.getMessage());
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
            	System.out.println();
    			System.out.println("----------------------------------------------------------------------------------");
    			System.out.printf("%-15s %-15s %-25s %-15s %-15s\n", "Customer ID", "Name", "Email", "Phone Number", "Address");
    			System.out.println("----------------------------------------------------------------------------------");
    			
            	int customer_id = result.getInt("customer_id");
                String name = result.getString("name");
                String email = result.getString("email");
                String phoneNumber = result.getString("phone_number");
                String address = result.getString("address");
                
				System.out.printf("%-15s %-15s %-25s %-15s %-15s\n", customer_id, name, email, phoneNumber, address);
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
			GeneralUtilities.checkCustomerExist(connection, customerId);
			
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
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		try {
			// check if customer exist's
			GeneralUtilities.checkCustomerExist(connection, customerId);

			// check if any claim is associated with the customer
			GeneralUtilities.checkClaimAssociatedWithCustomer(connection, customerId);

			String sql = "DELETE FROM Customer WHERE customer_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully!");
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
}
