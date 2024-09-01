package com.insurance.interfaces;

import com.insurance.models.Customer;

public interface ICustomerDao {
	public void registerCustomer(Customer customer);
	public void viewCustomer(int customerId);
	public void updateCustomer(int customerId, Customer customer);
	public void deleteCustomer(int customerId);
}
