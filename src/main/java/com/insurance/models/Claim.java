package com.insurance.models;

public class Claim {
	
	private int claimId;
	private int policyId;
	private int customerId;
	private String claimDate;
	private String status;
	
	public Claim(int claimId, int policyId, int customerId, String claimDate, String status) {
		this.claimId = claimId;
		this.policyId = policyId;
		this.customerId = customerId;
		this.claimDate = claimDate;
		this.status = status;
	}
	
	public Claim(int policyId, int customerId, String claimDate, String status) {
		this.policyId = policyId;
		this.customerId = customerId;
		this.claimDate = claimDate;
		this.status = status;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
