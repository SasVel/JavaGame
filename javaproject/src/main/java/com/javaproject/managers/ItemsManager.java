package com.javaproject.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.ItemData;
import com.javaproject.models.Item;

public class ItemsManager {
	
	Random rand = new Random();
	ObjectMapper mapper = new ObjectMapper();
	private List<ItemData> itemsData;
	
	public ItemsManager() {
		try {
			itemsData = mapper.readValue(getClass().getResource("/data/items.json"), new TypeReference<List<ItemData>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Item getItem(ItemData data) {
		return new Item(250, 250, 100, 600, data);
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
}
