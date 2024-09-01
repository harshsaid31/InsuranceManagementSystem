package com.insurance.models;

public class Policy {
	
	private int policyId;
	private int policyNumber;
	private String type;
	private double coverageAmount;
	private double premiumAmount;
	
	public Policy() {}
	
	public Policy(int policyNumber, String type, double coverageAmount, double premiumAmount) {
		this.policyNumber = policyNumber;
		this.type = type;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
	}
	
	public Policy(int policyId, int policyNumber, String type, double coverageAmount, double premiumAmount) {
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.type = type;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
	}
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
}
