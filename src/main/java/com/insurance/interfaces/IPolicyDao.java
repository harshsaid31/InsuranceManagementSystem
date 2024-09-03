package com.insurance.interfaces;

import com.insurance.models.Policy;

public interface IPolicyDao {
	public void addPolicy(Policy policy);
	public void viewPolicy(int policyId);
	public void updatePolicy(int policyId, Policy policy);
	public void deletePolicy(int policyId);
	public void viewAllPolicies();
}
