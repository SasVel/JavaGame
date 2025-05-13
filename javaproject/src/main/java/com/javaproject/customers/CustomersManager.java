package com.javaproject.customers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.UI.TypePanel;
import com.javaproject.data.Customer;
import com.javaproject.data.CustomerData;
import com.javaproject.items.ItemsManager;

public class CustomersManager {
	
	Random rand = new Random();
	ObjectMapper mapper = new ObjectMapper();
	private TypePanel typePanel;
	private ItemsManager itemsManager;

	private List<CustomerData> customersData;
	
	public CustomersManager(TypePanel _typePanel, ItemsManager _itemsManager) {

		typePanel = _typePanel;
		itemsManager = _itemsManager;

		try {
			customersData = mapper.readValue(getClass().getResource("/data/customers.json"), new TypeReference<List<CustomerData>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CustomerData> getAllCustomersData() {
		return customersData;
	}

	private CustomerData getRandCustomerData() {
		return customersData.get(rand.nextInt(customersData.size() - 1));
	}

	private Customer getNewRandCustomer() {
		return new Customer(450, 800, 350, 300, getRandCustomerData(), typePanel, itemsManager);
	}

	public List<CustomerData> getRandCustomerDataInRange(int lower, int upper) {
		int count = rand.nextInt(lower, upper + 1);

		List<CustomerData> resCustomers = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resCustomers.add(getRandCustomerData());
		}
		return resCustomers;
	}

	public List<Customer> getRandCustomersInRange(int lower, int upper) {
		int count = rand.nextInt(lower, upper + 1);

		List<Customer> resCustomers = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resCustomers.add(getNewRandCustomer());
		}
		return resCustomers;
	}


}
