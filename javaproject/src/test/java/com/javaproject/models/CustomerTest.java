package com.javaproject.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.javaproject.UI.TypePanel;
import com.javaproject.data.CustomerData;
import com.javaproject.managers.CustomersManager;
import com.javaproject.managers.ItemsManager;

public class CustomerTest {

	CustomerData data = new CustomerData("Goshko", "Guard");
	TypePanel typePanel = new TypePanel(null, null);
	ItemsManager itemsManager;
	CustomersManager customersManager;
	Customer customer;

	@Before
	public void configureBeforeTest() {
		itemsManager = new ItemsManager();
		customersManager = new CustomersManager(typePanel, itemsManager);
		customer = customersManager.getNewCustomer(data);
	}

	@Test
	public void testGetCurrItemIdx() {

		int expected = 0;
		int actual = customer.getCurrItemIdx();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetId() {
		customer = customersManager.getNewCustomer(data);

		long expected = CustomersManager.getCustomersNum();
		long actual = customer.getId();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetImgRelativePath() {
		String expected = data.getImgPathRelative();
		String actual = customer.getData().getImgPathRelative();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetName() {
		String expected = "Goshko";
		String actual = customer.getName();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetPriceCombined() {
		double expectedPrice = customer
			.getItems()
			.stream()
			.mapToDouble(i -> i.getData().getPrice())
			.sum();
		double actualPrice = customer.getPriceCombined();

		Assert.assertEquals(expectedPrice, actualPrice, 0.001);
	}

	@Test
	public void testSetNextItem() {
		Item prevItem = customer.getCurrItem();

		customer.setNextItem();

		Item expected = prevItem;
		Item actual = customer.getCurrItem();

		Assert.assertNotEquals(expected, actual);
	}
}
