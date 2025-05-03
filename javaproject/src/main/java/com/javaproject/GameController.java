package com.javaproject;

import com.javaproject.UI.TypeDisplayLabel;
import com.javaproject.UI.TypeInputLabel;
import com.javaproject.items.ItemsManager;

public class GameController {

	TypeDisplayLabel displayLabel;
	TypeInputLabel inputLabel;
	ItemsManager items;
	
	public GameController(TypeDisplayLabel typeDisplayLabel, TypeInputLabel typeInputLabel, ItemsManager itemsManager) {
		displayLabel = typeDisplayLabel;
		inputLabel = typeInputLabel;
		items = itemsManager;

		ReloadTextList();
	}

	public void update() {
		if (inputLabel.isTextCompleted) {
			ReloadTextList();
			inputLabel.isTextCompleted = false;
		}
	}

	private void ReloadTextList() {
		displayLabel.LoadItems(items.getRandItemsInRange(2, 3));
		inputLabel.LoadTextList(displayLabel.textList);
	}
}
