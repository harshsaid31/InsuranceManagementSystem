package com.insurance;

import java.sql.Connection;
import java.util.Scanner;

import com.insurance.dao.ClaimDao;
import com.insurance.dao.CustomerDao;
import com.insurance.dao.PolicyDao;
import com.insurance.utils.ChoiceManager;
import com.insurance.utils.DatabaseConnection;

/**
 * Insurance Management System
 */
public class App {
    public static void main(String[] args) {
    	
    	try {
	    	Scanner scanner = new Scanner(System.in);
	    	Connection connection = DatabaseConnection.getConnection();
	    	
	    	PolicyDao policyDao = new PolicyDao(connection);
	    	CustomerDao customerDao = new CustomerDao(connection);
	    	ClaimDao claimDao = new ClaimDao(connection);
	    	
	    	ChoiceManager choiceManager = new ChoiceManager();
	    	
	    	while (true) {
	    		System.out.println("\nInsurance Management System");
	            System.out.println("1. Manage Policies");
	            System.out.println("2. Manage Customers");
	            System.out.println("3. Manage Claims");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");
	            
	            int choice = scanner.nextInt();
	            
	            switch (choice) {
				case 1: {
					choiceManager.managePolicies(scanner, policyDao);
					break;
				}
				case 2: {
					choiceManager.manageCustomers(scanner, customerDao);
					break;
				}
				case 4: {
					break;
				}
				default:
					System.out.println("Invalid Choice");
				}
	    	}
	    	
    	} catch (Exception e) {
    		System.out.println("Some error occured please try again");
    		e.printStackTrace();
		}
    }
}
