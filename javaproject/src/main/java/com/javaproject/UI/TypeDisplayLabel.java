package com.javaproject.UI;

import java.awt.Color;
import java.util.List;

import com.javaproject.models.Item;

public class TypeDisplayLabel extends TextLabel {

	public TypeDisplayLabel(Color color, int _fontSize) {
		super(color, _fontSize);
	}

	public void loadText(List<Item> items) {
		textList.clear();
		double itemsPriceTotal = 0;
		for (Item item : items) {
			itemsPriceTotal += item.getData().getPrice();
			textList.add(item.toString());
		}
		textList.add(seperator);
		textList.add("Total - " + String.format("%.2f", itemsPriceTotal));
	}
}
