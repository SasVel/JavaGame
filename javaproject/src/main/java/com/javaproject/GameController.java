package com.javaproject;

import java.awt.Graphics2D;

import com.javaproject.UI.TypePanel;
import com.javaproject.data.GameDay;
import com.javaproject.interfaces.DayDoneListener;
import com.javaproject.interfaces.Drawable;
import com.javaproject.items.ItemsManager;

public final class GameController implements Drawable, DayDoneListener{

	private TypePanel typePanel;
	private ItemsManager items;
	private GameDay currentDay;
	
	public GameController(TypePanel _typePanel, ItemsManager _itemsManager) {
		typePanel = _typePanel;
		items = _itemsManager;

		startNewDay();
	}

	private GameDay getNewDay() {
		return new GameDay(typePanel, items);
	}

	public GameDay startNewDay() {
		currentDay = getNewDay();
		currentDay.addListener(this);
		return currentDay;
	}

	public GameDay getCurrentDay() {
		return currentDay;
	}

	@Override
	public void dayFinished() {
		startNewDay();
	}

	@Override
	public void draw(Graphics2D g) {
		currentDay.draw(g);
	}
}
