package com.javaproject.UI;

import java.awt.Color;
import java.util.List;

import com.javaproject.items.Item;

public class TypeDisplayLabel extends TextLabel {

	public TypeDisplayLabel(Color color) {
		super(color);
	}

	public void LoadItems(List<Item> items) {
		textList.clear();
		for (Item item : items) {
			textList.add(String.format("%s - %s", item.getName(), String.format("%.2f", item.getPrice())));
		}
	}
}
