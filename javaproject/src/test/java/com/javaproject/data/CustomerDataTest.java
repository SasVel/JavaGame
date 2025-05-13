package com.javaproject.data;

import org.junit.Assert;
import org.junit.Test;

public class CustomerDataTest {
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
}
