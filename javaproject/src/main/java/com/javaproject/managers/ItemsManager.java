package com.javaproject.managers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.ItemData;
import com.javaproject.enums.TextDifficulty;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.models.Item;

public class ItemsManager {
	
	Random rand = new Random();
	ObjectMapper mapper = new ObjectMapper();
	private HashSet<ItemData> itemsData;
	private Iterator<ItemData> itemsIterator;

	private static long numOfItems = 0;
	
	public ItemsManager() throws ResourceNotLoadedException {
		URL dataPath = getClass().getResource("/data/items.json");
		try {
			itemsData = mapper.readValue(dataPath, new TypeReference<HashSet<ItemData>>() {});
			itemsIterator = itemsData.iterator();
		} catch (IOException e) {
			throw new ResourceNotLoadedException(dataPath.getPath());
		}
	}

	public static long getNumOfItems() {
		return numOfItems;
	}

	private ItemData getRandItemData() {
		itemsIterator = itemsData.iterator();
		ItemData res = itemsIterator.next();
		int randIdx = rand.nextInt(itemsData.size());
		int currIdx = 0;

		while (itemsIterator.hasNext()) {
			if (currIdx == randIdx) break;
			res = itemsIterator.next();
			currIdx++;
		}
		return res;
	}

	public Item getItem(ItemData data) throws ResourceNotLoadedException {
		numOfItems++;
		return new Item(250, 250, 100, 600, numOfItems, data);
	}

	public HashSet<ItemData> getAllItemsData() {
		return itemsData;
	}

	public List<Item> getRandItemsInRange(int lower, int upper) throws ResourceNotLoadedException {
		int count = rand.nextInt(lower, upper + 1);

		List<Item> resItems = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			resItems.add(getItem(getRandItemData()));
		}
		return resItems;
	}

	public List<ItemData> getItemDataByDifficulty(TextDifficulty diff) {
		return itemsData
			.stream()
			.filter(i -> i.getDifficulty() == diff)
			.collect(Collectors.toList());
	}
}
