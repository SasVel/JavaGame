package com.javaproject.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.UI.TypePanel;
import com.javaproject.data.CustomerData;
import com.javaproject.models.Customer;

public class CustomersManager {
	
	private final Random rand = new Random();
	private final ObjectMapper mapper = new ObjectMapper();
	private TypePanel typePanel;
	private ItemsManager itemsManager;

	private HashSet<CustomerData> customersData;
	private Iterator<CustomerData> customersIterator;

	private static long customersNum = 0;
	
	public CustomersManager(TypePanel _typePanel, ItemsManager _itemsManager) {

		typePanel = _typePanel;
		itemsManager = _itemsManager;

		try {
			customersData = mapper.readValue(getClass().getResource("/data/customers.json"), new TypeReference<HashSet<CustomerData>>() {});
			customersIterator = customersData.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long getCustomersNum() {
		return customersNum;
	}

	public Set<CustomerData> getAllCustomersData() {
		return customersData;
	}

	public Customer getNewCustomer(CustomerData data) {
		customersNum++;
		return new Customer(450, 800, 350, 300, customersNum, data, typePanel, itemsManager);
	}

	private CustomerData getRandCustomerData() {
		customersIterator = customersData.iterator();
		CustomerData res = customersIterator.next();
		int randIdx = rand.nextInt(customersData.size());
		int currIdx = 0;

		while (customersIterator.hasNext()) {
			if (currIdx == randIdx) break;
			res = customersIterator.next();
			currIdx++;
		}
		return res;
	}

	private Customer getNewRandCustomer() {
		customersNum++;
		return new Customer(450, 800, 350, 300, customersNum, getRandCustomerData(), typePanel, itemsManager);
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
