package com.insurance.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneralUtilities {
    public static boolean checkPolicyExist(int policyId) {
		try {
            Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Policy WHERE policy_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, policyId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Policy with ID " + policyId + " does not exist.");
	        	return true;
	        }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
		return false;
	}

	public static boolean checkCustomerExist(int customerId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Customer WHERE customer_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, customerId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Customer with ID " + customerId + " does not exist.");
	        	return true;
	        }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
		return false;
	}

	public static boolean checkClaimExist(int claimId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Claim WHERE claim_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, claimId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Claim with ID " + claimId + " does not exist.");
	        	return true;
	        }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
		return false;
	}

	public static boolean checkClaimAssociatedWithCustomer(int customerId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Claim WHERE customer_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
			checkStatement.setInt(1, customerId);
			ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Cannot delete customer with ID " + customerId + " as it is associated with claim");
                return true;
            }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
		return false;
	}

	public static boolean checkClaimAssociatedWithPolicy(int policyId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Claim WHERE policy_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
			checkStatement.setInt(1, policyId);
			ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Cannot delete Policy with ID " + policyId + " as it is associated with claim");
                return true;
            }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
		return false;
	}
}
