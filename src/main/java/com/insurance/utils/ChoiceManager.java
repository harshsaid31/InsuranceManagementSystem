package com.insurance.utils;

import java.util.Scanner;

import com.insurance.dao.PolicyDao;
import com.insurance.models.Policy;

public class ChoiceManager {
	
	private void setPolicyDetails(Scanner scanner, Policy policy) {
		try {
			System.out.println("Enter Policy Number: ");
	        int policyNumber = scanner.nextInt();
	        
	        scanner.nextLine();
	        System.out.println("Enter Policy Type: ");
	        String type = scanner.nextLine();
	        
	        System.out.println("Enter Coverage Amount: ");
	        double coverageAmount = scanner.nextDouble();
	        
	        System.out.println("Enter Premium Amount: ");
	        double premiumAmount = scanner.nextDouble(); 
	        
	        policy.setPolicyNumber(policyNumber);
	        policy.setType(type);
	        policy.setCoverageAmount(coverageAmount);
	        policy.setPremiumAmount(premiumAmount);
		}
        catch (Exception e) {
        	System.out.println("Please enter correct input");
        }
	}
	
	public void managePolicies(Scanner scanner, PolicyDao policyDao) {
		System.out.println("\nManage Policy");
        System.out.println("1. Add Policy");
        System.out.println("2. View Policy");
        System.out.println("3. Update Policy");
        System.out.println("4. Delete Policy");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        
        switch (choice) {
		case 1: {
			Policy policy = new Policy();    
			
			// take policy details from user
			this.setPolicyDetails(scanner, policy);
			policyDao.addPolicy(policy);
			break;
		}
		case 2: {
			System.out.println("Enter Policy Id: ");
			int policyId = scanner.nextInt();
			policyDao.viewPolicy(policyId);
			break;
		}
		case 3: {
			System.out.println("Enter Policy Id: ");
			int policyId = scanner.nextInt();
			
			// update policy
			Policy policy = new Policy();
			this.setPolicyDetails(scanner, policy);
			policyDao.updatePolicy(policyId, policy);
			break;
		}
		default:
			System.out.println("Invalid choice");
		}
	}
	
}
