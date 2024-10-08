package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.insurance.interfaces.IPolicyDao;
import com.insurance.models.Policy;
import com.insurance.utils.GeneralUtilities;
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
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void viewPolicy(int policyId) {
		try {
			String sql = "SELECT * FROM Policy WHERE policy_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, policyId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				System.out.println();
				System.out.println("-----------------------------------------------------------------------");
				System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", "Policy ID", "Policy Number", "Type", "Coverage", "Premium");
				System.out.println("-----------------------------------------------------------------------");
				
				int policy_id = resultSet.getInt("policy_id");
				String policyNumber = resultSet.getString("policy_number");
				String type = resultSet.getString("type");
				double coverageAmount = resultSet.getDouble("coverage_amount");
				double premiumAmount = resultSet.getDouble("premium_amount");
				
				System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", policy_id, policyNumber, type, coverageAmount, premiumAmount);
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
			GeneralUtilities.checkPolicyExist(connection, policyId);
	        
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
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void viewAllPolicies() {
		try {
			
			// header
			System.out.println("-----------------------------------------------------------------------");
			System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", "Policy ID", "Policy Number", "Type", "Coverage", "Premium");
			System.out.println("-----------------------------------------------------------------------");
				
			String sql = "SELECT * FROM Policy";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int policyId = resultSet.getInt("policy_id");
				String policyNumber = resultSet.getString("policy_number");
				String type = resultSet.getString("type");
				double coverageAmount = resultSet.getDouble("coverage_amount");
				double premiumAmount = resultSet.getDouble("premium_amount");

				System.out.printf("%-15s %-15s %-15s %-15s %-15s\n", policyId, policyNumber, type, coverageAmount, premiumAmount);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletePolicy(int policyId) {
		try {
			// check if policy exists
			GeneralUtilities.checkPolicyExist(connection, policyId);

			// check if there are claims associated with the policy
			GeneralUtilities.checkClaimAssociatedWithPolicy(connection, policyId);

			// delete policy
			String sql = "DELETE FROM Policy WHERE policy_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, policyId);
			int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Policy deleted successfully");
            }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
