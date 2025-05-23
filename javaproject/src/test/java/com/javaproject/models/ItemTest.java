package com.javaproject.models;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.javaproject.data.ItemData;
import com.javaproject.enums.TextDifficulty;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.managers.ItemsManager;

public class ItemTest {

	ItemData data = new ItemData("Ember Root", "TestDesc", (double)5.50d, TextDifficulty.MEDIUM);
	ItemsManager itemsManager;
	Item item;

	@Before
	public void configure() throws ResourceNotLoadedException {
		itemsManager = new ItemsManager();
		item = itemsManager.getItem(data);
	}

	@Test
	public void testGetId() {
		long expected = item.getId();
		long actual = ItemsManager.getNumOfItems();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetItemDataByDifficulty() {
		List<ItemData> filteredData = itemsManager.getItemDataByDifficulty(TextDifficulty.MEDIUM);
		
		Assert.assertTrue(filteredData.stream().allMatch(d -> d.getDifficulty() == TextDifficulty.MEDIUM));
	}

	@Test
	public void testToString() {
		String expected = String.format("%s - %s", item.getData().getName(), String.format("%.2f", item.getData().getPrice()));
		String actual = item.toString();

		Assert.assertEquals(expected, actual);
	}
}
