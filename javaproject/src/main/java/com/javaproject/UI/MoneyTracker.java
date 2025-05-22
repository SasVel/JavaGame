package com.javaproject.UI;

import java.awt.Color;
import java.awt.Graphics2D;

import com.javaproject.data.CurrencyData;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.models.DrawableObject;

public class MoneyTracker extends DrawableObject {
	private final CurrencyData data;


	private final TextLabel coinLabel = new TextLabel(Color.WHITE, 42);
	public MoneyTracker(int _width, int _height, int _posX, int _posY, CurrencyData _currencyData) throws ResourceNotLoadedException {
		super(_width, _height, _posX, _posY, _currencyData);
		data = _currencyData;

		configCoinLabel();
	}

	private void configCoinLabel() {
		coinLabel.setX(PosX - 80);
		coinLabel.setY(PosY + 20);
		coinLabel.textList.add("0");
	}

	private void updateLabel(double val) {
		coinLabel.textList.set(0, String.format("%.2f", val));
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		coinLabel.draw(g);
	}

	public void addToAmmount(double money) {
		data.setCurrency(data.getCurrency() + money);
		updateLabel(data.getCurrency());
	}
}
