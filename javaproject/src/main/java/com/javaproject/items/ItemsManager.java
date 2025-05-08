package com.javaproject.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.Item;

public class ItemsManager {
	
	Random rand = new Random();
	ObjectMapper mapper = new ObjectMapper();
	private List<Item> items;
	
	public ItemsManager() {
		try {
			items = mapper.readValue(getClass().getResource("/data/items.json"), new TypeReference<List<Item>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Item> getAllItems() {
		return items;
	}

	public List<Item> getRandItemsInRange(int lower, int upper) {
		int count = rand.nextInt(lower, upper + 1);

		List<Item> resItems = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resItems.add(items.get(rand.nextInt(items.size() - 1)));
		}
		return resItems;
	}

	public List<Item> getItemsByDifficulty(short difficulty) {
		List<Item> resItems = new ArrayList<>();
		for (Item item : items) {
			if (item.getDifficulty() == difficulty) resItems.add(item);
		}
		return resItems;
	}
}
