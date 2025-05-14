package com.javaproject.models;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.javaproject.data.ItemData;
import com.javaproject.managers.ItemsManager;
import com.javaproject.models.Item.Difficulty;

public class ItemTest {

	ItemData itemData = new ItemData("Ember Root", "TestDesc", (double)5.50d, Difficulty.MEDIUM);
	ItemsManager itemsManager = new ItemsManager();
	Random rand = new Random();
	Item item = itemsManager.getItem(itemData);

	@Test
	public void testGetId() {
		long expected = item.getId();
		long actual = ItemsManager.getNumOfItems();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetItemDataByDifficulty() {
		List<ItemData> filteredData = itemsManager.getItemDataByDifficulty(Difficulty.MEDIUM);
		
		Assert.assertTrue(filteredData.stream().allMatch(d -> d.getDifficulty() == Difficulty.MEDIUM));
	}

	@Test
	public void testToString() {
		//
		String expected = String.format("%s - %s", item.data.getName(), String.format("%.2f", item.data.getPrice()));
		String actual = item.toString();

		Assert.assertEquals(expected, actual);
	}
}
