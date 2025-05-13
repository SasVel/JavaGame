package com.javaproject.data;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class CustomerDataTest {
	static ObjectMapper mapper = new ObjectMapper();
	static CustomerData data = new CustomerData("Goshko", "Guard");

	@Test
	public void testGetName() {
		String expected = "Goshko";
		String actual = data.getName();

		Assert.assertEquals(expected , actual);
	}

	@Test
	public void testGetOccupation() {
		String expected = "Guard";
		String actual = data.getOccupation();

		Assert.assertEquals(expected , actual);
	}

	@Test
	public void testGetImgPathRelative() {

		String expected = "/data/CustomerImages/" + data.getName().replace(" ", "") + ".png";
		String actual = data.getImgPathRelative();

		Assert.assertEquals(expected , actual);
	}

	@Test
	public void testDataLoading() {
		List<CustomerData> customerData;
		try {
			customerData = mapper.readValue(getClass().getResource("/data/customers.json"), new TypeReference<List<CustomerData>>() {});
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue(true);
	}
}
