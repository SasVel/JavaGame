package com.javaproject.UI;

import java.awt.Color;
import java.awt.Graphics2D;

import com.javaproject.DrawableObject;
import com.javaproject.data.CurrencyData;

public class MoneyTracker extends DrawableObject {
	private final CurrencyData data;

	private final TextLabel coinLabel = new TextLabel(Color.WHITE, 42);
	public MoneyTracker(int _width, int _height, int _posX, int _posY, CurrencyData _data) {
		super(_width, _height, _posX, _posY, _data);
		data = _data;
		
		configCoinLabel();
	}

	private void configCoinLabel() {
		coinLabel.setX(PosX - 65);
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
		updateLabel(money);
	}
}
