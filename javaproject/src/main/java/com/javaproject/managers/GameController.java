package com.javaproject.managers;

import java.awt.Graphics2D;

import com.javaproject.UI.MoneyTracker;
import com.javaproject.data.CurrencyData;
import com.javaproject.interfaces.IDayDoneListener;
import com.javaproject.interfaces.IDrawable;
import com.javaproject.models.GameDay;

public final class GameController implements IDrawable, IDayDoneListener{

	private final ItemsManager items;
	private final CustomersManager customersManager;
	private final CurrencyData currencyData;
	private final MoneyTracker moneyTracker;
	private GameDay currentDay;

	public GameController(ItemsManager _itemsManager, CustomersManager _customersManager, CurrencyData _currencyData) {
		items = _itemsManager;
		customersManager = _customersManager;
		currencyData = _currencyData;
		moneyTracker = new MoneyTracker(120, 120, 1450, 60, currencyData);

		startNewDay();
	}

	private GameDay getNewDay() {
		return new GameDay(items, customersManager, moneyTracker);
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

	public void update(double delta) {
		currentDay.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		currentDay.draw(g);
		moneyTracker.draw(g);
	}
}
