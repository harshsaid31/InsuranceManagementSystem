package com.insurance.utils;

import java.util.Scanner;

import com.insurance.dao.CustomerDao;
import com.insurance.dao.PolicyDao;
import com.insurance.models.Customer;
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
	
	private void setCustomerDetails(Scanner scanner, Customer customer) {
		try {
			 scanner.nextLine();
			 System.out.println("Enter Customer Name: ");
			 String name = scanner.nextLine();

			 System.out.println("Enter Email: ");
			 String email = scanner.nextLine();
			 
			 System.out.println("Enter Phone Number: ");
			 String phoneNumber = scanner.nextLine();
			 
			 System.out.println("Enter Address: ");
			 String address = scanner.nextLine();
			 
			 customer.setName(name);
			 customer.setEmail(email);
			 customer.setPhoneNumber(phoneNumber);
			 customer.setAddress(address);
		} catch (Exception e) {
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
		case 4: {
			System.out.println("Delete policy");
			break;
		}
		default:
			System.out.println("Invalid choice");
		}
	}
	
	public void manageCustomers(Scanner scanner, CustomerDao customerDao) {
		System.out.println("\nCustomer Management");
        System.out.println("1. Register Customer");
        System.out.println("2. View Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        
        switch (choice) {
		case 1: {
			Customer customer = new Customer();
			
			// take customer details
			this.setCustomerDetails(scanner, customer);
			customerDao.registerCustomer(customer);
			break;
		}
		case 2: {
			System.out.println("Enter Customer Id: ");
			int customerId = scanner.nextInt();
			customerDao.viewCustomer(customerId);
			break;
		}
		case 3: {
			System.out.println("Enter Customer Id: ");
			int customerId = scanner.nextInt();
			
			// update customer details
			Customer customer = new Customer();
			this.setCustomerDetails(scanner, customer);
			customerDao.updateCustomer(customerId, customer);
			break;
		}
		default:
			System.out.println("Invalid choice");
		}
        
	}
	
}
