package com.javaproject.models;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import com.javaproject.data.ItemData;
import com.javaproject.managers.ItemsManager;

public class ItemTest {

	ItemData itemData = new ItemData("Ember Root", "TestDesc", (double)5.50d, (short)1);
	ItemsManager itemsManager = new ItemsManager();
	Random rand = new Random();
	Item item = itemsManager.getItem(itemData);

	@Test
	public void testGetId() {

		long expected = item.getId();
		long actual = 1L;

		assertEquals(expected, actual);
	}

	@Test
	public void testToString() {
		//
		String expected = String.format("%s - %s", item.data.getName(), String.format("%.2f", item.data.getPrice()));
		String actual = item.toString();

		assertEquals(expected, actual);
	}
}
