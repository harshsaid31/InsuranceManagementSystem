package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import com.insurance.interfaces.IPolicyDao;
import com.insurance.models.Policy;
import com.mysql.cj.protocol.Resultset;

public class PolicyDao implements IPolicyDao {

	private Connection connection = null;
	
	public PolicyDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void addPolicy(Policy policy) {
		try {
            
			String sql = "INSERT INTO Policy (policy_number, type, coverage_amount, premium_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, policy.getPolicyNumber());
            preparedStatement.setString(2, policy.getType());
            preparedStatement.setDouble(3, policy.getCoverageAmount());
            preparedStatement.setDouble(4, policy.getPremiumAmount());
            
            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
            	System.out.println("Policy Added");
            }
		
		} catch (Exception e) {
			System.out.println("Some error occured while adding policy");
			e.printStackTrace();
		}
	}

	@Override
	public void viewPolicy(int policyId) {
		try {
			
			String sql = "SELECT * FROM Policy WHERE policy_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, policyId);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
                System.out.println("Policy ID: " + result.getInt("policy_id"));
                System.out.println("Policy Number: " + result.getString("policy_number"));
                System.out.println("Type: " + result.getString("type"));
                System.out.println("Coverage Amount: " + result.getDouble("coverage_amount"));
                System.out.println("Premium Amount: " + result.getDouble("premium_amount"));
            } else {
                System.out.println("Policy not found.");
            }
			
		} catch (Exception e) {
			System.out.println("Some error occured while viewing policy");
			e.printStackTrace();
		}
	}

	@Override
	public void updatePolicy(int policyId, Policy policy) {
		try {
			
			// check if policy exists
			String checkSql = "SELECT * FROM Policy WHERE policy_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	        checkStatement.setInt(1, policyId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Policy with ID " + policyId + " does not exist.");
	        	return;
	        }
	        
	        // update policy
			String sql = "UPDATE Policy SET policy_number = ?, type = ?, coverage_amount = ?, premium_amount = ? WHERE policy_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, policy.getPolicyNumber());
			preparedStatement.setString(2, policy.getType());
			preparedStatement.setDouble(3, policy.getCoverageAmount());
			preparedStatement.setDouble(4, policy.getPremiumAmount());
			preparedStatement.setInt(5, policyId);
			
			int rowUpdated = preparedStatement.executeUpdate();
			if (rowUpdated > 0) {
				System.out.println("Policy Updated Successfully");
			} else {
				System.out.println("Policy Not Updated");
			}
			
		} catch (Exception e) {
			System.out.println("Some error occured while updating policy");
			e.printStackTrace();
		}
	}

	@Override
	public void deletePolicy(int policyId) {

	}

}
