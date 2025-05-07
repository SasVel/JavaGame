package com.javaproject;

import com.javaproject.UI.TypePanel;
import com.javaproject.items.ItemsManager;

public class GameController {

	TypePanel typePanel;
	ItemsManager items;
	
	public GameController(TypePanel _typePanel, ItemsManager _itemsManager) {
		typePanel = _typePanel;
		items = _itemsManager;

		typePanel.ReloadTextList(items);
	}

	public void update() {
		if (typePanel.inputLabel.isTextCompleted) {
			typePanel.ReloadTextList(items);
			typePanel.inputLabel.isTextCompleted = false;
		}
	}
}
