package com.javaproject.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemsManager {
	
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

	public List<Item> getItemsByDifficulty(short difficulty) {
		List<Item> resItems = new ArrayList<>();
		for (Item item : items) {
			if (item.getDifficulty() == difficulty) resItems.add(item);
		}
		return resItems;
	}
}
