package com.javaproject.data;

import org.junit.*;

public class CurrencyDataTest {
	static CurrencyData data = new CurrencyData();

	@Before
	public void configure() {
		data.setCurrency(10.5);
	}


	@Test
	public void testGetSetCurrency() {
		double expected = 10.5;
		double actual = data.getCurrency();

		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void testGetName() {
		String expected = "10.5";
		String actual = data.getName();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetImgPathRelative() {
		String expected = "/data/Props/coins.png";
		String actual = data.getImgPathRelative();

		Assert.assertEquals(expected, actual);
	}
}
