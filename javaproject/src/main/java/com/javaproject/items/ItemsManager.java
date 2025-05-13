package com.javaproject.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.CurrencyData;
import com.javaproject.data.Item;
import com.javaproject.data.ItemData;

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

	public List<ItemData> getAllItemsData() {
		return itemsData;
	}

	public List<Item> getRandItemsInRange(int lower, int upper) {
		int count = rand.nextInt(lower, upper + 1);

		List<Item> resItems = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resItems.add(new Item(250, 250, 100, 600, itemsData.get(rand.nextInt(itemsData.size() - 1))));
		}
		return resItems;
	}
}
