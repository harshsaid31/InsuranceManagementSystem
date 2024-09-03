package com.insurance.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.insurance.exceptions.CustomerDeleteException;
import com.insurance.exceptions.ClaimNotFoundException;
import com.insurance.exceptions.CustomerNotFoundException;
import com.insurance.exceptions.PolicyDeleteException;
import com.insurance.exceptions.PolicyNotFoundException;

public class GeneralUtilities {
    public static void checkPolicyExist(Connection connection, int policyId) throws PolicyNotFoundException, SQLException {
		String sql = "SELECT * FROM Policy WHERE policy_id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(sql);
	    checkStatement.setInt(1, policyId);
	    ResultSet resultSet = checkStatement.executeQuery();
	    if (!resultSet.next()) {
	        throw new PolicyNotFoundException("Policy with ID " + policyId + " does not exist.");
	    }
	}

	public static void checkCustomerExist(Connection connection, int customerId) throws CustomerNotFoundException, SQLException {
		String sql = "SELECT * FROM Customer WHERE customer_id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(sql);
		checkStatement.setInt(1, customerId);
	    ResultSet resultSet = checkStatement.executeQuery();
	    if (!resultSet.next()) {
	    	throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
	    }
	}

	public static void checkClaimExist(Connection connection, int claimId) throws ClaimNotFoundException, SQLException {
			String sql = "SELECT * FROM Claim WHERE claim_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, claimId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	throw new ClaimNotFoundException("Claim with ID " + claimId + " does not exist.");
	        }
	}

	public static void checkClaimAssociatedWithCustomer(Connection connection, int customerId) throws CustomerDeleteException, SQLException {
		String sql = "SELECT * FROM Claim WHERE customer_id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(sql);
		checkStatement.setInt(1, customerId);
		ResultSet resultSet = checkStatement.executeQuery();
		if (resultSet.next()) {
			throw new CustomerDeleteException("Cannot delete customer with ID " + customerId + " as it is associated with claim");
		}
	}

	public static void checkClaimAssociatedWithPolicy(Connection connection, int policyId) throws PolicyDeleteException, SQLException {
		String sql = "SELECT * FROM Claim WHERE policy_id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(sql);
		checkStatement.setInt(1, policyId);
		ResultSet resultSet = checkStatement.executeQuery();
		if (resultSet.next()) {
			throw new PolicyDeleteException("Cannot delete Policy with ID " + policyId + " as it is associated with claim");
		}
	}
}
