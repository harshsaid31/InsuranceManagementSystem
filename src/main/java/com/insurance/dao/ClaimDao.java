package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.insurance.interfaces.IClaimDao;
import com.insurance.models.Claim;
import com.insurance.utils.*;

public class ClaimDao implements IClaimDao {
	
	private Connection connection = null;
	
	public ClaimDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void submitClaim(Claim claim) {
		try {
			// check if policy exist's
			GeneralUtilities.checkPolicyExist(connection, claim.getPolicyId());

			// check if customer exist's
			GeneralUtilities.checkCustomerExist(connection, claim.getCustomerId());

			// submit claim
			String sql = "INSERT INTO Claim (policy_id, customer_id, claim_date, status) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, claim.getPolicyId());
			preparedStatement.setInt(2, claim.getCustomerId());
			preparedStatement.setString(3, claim.getClaimDate());
			preparedStatement.setString(4, claim.getStatus());

			int rowInserted = preparedStatement.executeUpdate();
			if (rowInserted > 0) {
				System.out.println("Claim Submitted Successfully");
			} 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void viewClaim(int claimId) {
		try {
			String sql = "SELECT cl.claim_id, p.type, cu.name, cl.claim_date, cl.status FROM claim cl INNER JOIN policy p on cl.policy_id = p.policy_id INNER JOIN customer cu ON cl.customer_id = cu.customer_id where cl.claim_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, claimId);

            ResultSet result = preparedStatement.executeQuery();
            
            if (result.next()) {
            	System.out.println();
    			System.out.println("----------------------------------------------------------------------------");
    			System.out.printf("%-15s %-15s %-20s %-15s %-15s\n", "Claim ID", "Policy Type", "Customer Name", "Claim Date", "Status");
    			System.out.println("----------------------------------------------------------------------------");
                
            	
                int claim_id = result.getInt("claim_id");
                String policyType = result.getString("type");
                String name = result.getString("name");
                String claimDate = result.getString("claim_date");
                String status = result.getString("status");
                
				System.out.printf("%-15s %-15s %-20s %-15s %-15s\n", claim_id, policyType , name, claimDate, status);
            } else {
                System.out.println("Claim not found.");
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateClaim(int claimId, String status) {
		try {

			// check if claim exist
			GeneralUtilities.checkClaimExist(connection, claimId);

			// update claim
			String sql = "UPDATE Claim SET status = ? WHERE claim_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, claimId);

			int rowUpdated = preparedStatement.executeUpdate();
			if (rowUpdated > 0) {
				System.out.println("Claim Updated Successfully");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteClaim(int claimId) {
		try {
			// check if claim exist
			GeneralUtilities.checkClaimExist(connection, claimId);
			
			// delete claim
			String sql = "DELETE FROM Claim WHERE claim_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, claimId);

			int rowDeleted = preparedStatement.executeUpdate();
			if (rowDeleted > 0) {
				System.out.println("Claim Deleted Successfully");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
