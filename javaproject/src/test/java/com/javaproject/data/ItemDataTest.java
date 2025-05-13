package com.javaproject.data;

import org.junit.Assert;
import org.junit.Test;

public class ItemDataTest {
	static ItemData data = new ItemData("Ember Root", "Rare roots used as a potent fire starter.", (double)12.5, (short)1);

	@Test
	public void testGetName() {
		String expected = "Ember Root";
		String actual = data.getName();

		Assert.assertEquals(expected , actual);
	}

	@Test
	public void testGetDesc() {
		String expected = "Rare roots used as a potent fire starter.";
		String actual = data.getDesc();

		Assert.assertEquals(expected , actual);
	}

	@Test
	public void testGetPrice() {
		double expected = 12.5;
		double actual = data.getPrice();

		Assert.assertEquals(expected , actual, 0.001);
	}

	@Test
	public void testGetImgPathRelative() {

		String expected = "/data/ItemImages/" + data.getName().replace(" ", "") + ".png";
		String actual = data.getImgPathRelative();

		Assert.assertEquals(expected , actual);
	}
}
