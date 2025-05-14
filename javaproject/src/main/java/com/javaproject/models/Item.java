package com.javaproject.models;

import java.awt.Color;
import java.awt.Graphics2D;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.javaproject.UI.TextLabel;
import com.javaproject.data.ItemData;

public class Item extends DrawableObject{
	private final long id;
	private ItemData data;

	private final TextLabel titleLabel = new TextLabel(Color.WHITE, 34);
	private final TextLabel descLabel = new TextLabel(Color.WHITE, 25);

	public enum Difficulty {
		EASY,
		MEDIUM,
		HARD
	}

	@JsonCreator
	public Item(int _width, int _height, int _posX, int _posY, long _numOfItems, ItemData _data) {
		super(_width, _height, _posX, _posY, _data);
		
		this.data = _data;
		this.id = _numOfItems;

		configTitleLabel();
		configDescLabel();
	}

	private void configTitleLabel() {
		titleLabel.setX(PosX + Width + 20);
		titleLabel.setY(PosY);
		titleLabel.addToText(data.getName());
	}

	private void configDescLabel() {
		descLabel.setX(PosX + Width + 20);
		descLabel.setY(PosY + 50);
		descLabel.isAutowrap = true;
		descLabel.addToText(data.getDesc());
	}

	public long getId() {
		return id;
	}

	public ItemData getData() {
		return data;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", this.data.getName(), String.format("%.2f", this.data.getPrice()));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, PosY - 30, Width * 3, Height + 50);
		super.draw(g);
		titleLabel.draw(g);
		descLabel.draw(g);
	}
}
