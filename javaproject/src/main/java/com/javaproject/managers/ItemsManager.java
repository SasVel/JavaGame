package com.javaproject.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.ItemData;
import com.javaproject.models.Item;
import com.javaproject.models.Item.Difficulty;

public class ItemsManager {
	
	Random rand = new Random();
	ObjectMapper mapper = new ObjectMapper();
	private List<ItemData> itemsData;

	private static long numOfItems = 0;
	
	public ItemsManager() {
		try {
			itemsData = mapper.readValue(getClass().getResource("/data/items.json"), new TypeReference<List<ItemData>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long getNumOfItems() {
		return numOfItems;
	}

	public Item getItem(ItemData data) {
		numOfItems++;
		return new Item(250, 250, 100, 600, numOfItems, data);
	}

	public List<ItemData> getAllItemsData() {
		return itemsData;
	}

	public List<Item> getRandItemsInRange(int lower, int upper) {
		int count = rand.nextInt(lower, upper + 1);

		List<Item> resItems = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resItems.add(getItem(itemsData.get(rand.nextInt(itemsData.size() - 1))));
		}
		return resItems;
	}

	public List<ItemData> getItemDataByDifficulty(Difficulty diff) {
		return itemsData
			.stream()
			.filter(i -> i.getDifficulty() == diff)
			.collect(Collectors.toList());
	}
}
