package com.javaproject.data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.UI.TypePanel;
import com.javaproject.interfaces.CustomerDoneListener;
import com.javaproject.interfaces.Drawable;
import com.javaproject.interfaces.TypingInputListener;
import com.javaproject.items.ItemsManager;

public class Customer implements Drawable, TypingInputListener {
	private final long id;
	private final CustomerData data;
	private final TypePanel typePanel;
	private final List<Item> items;
	private int currItemIdx = 0;
	private Item currItem;

	private List<CustomerDoneListener> listeners = new ArrayList<>();

	private BufferedImage image;

	private static long customersNum = 0;

	public Customer(CustomerData _data, TypePanel _typePanel, ItemsManager _itemsManager) {
		customersNum++;
		id = customersNum;

		this.data = _data;
		this.typePanel = _typePanel;
		this.items = _itemsManager.getRandItemsInRange(1, 2);
		this.currItemIdx = 0;
	}

	public long getId() {
		return id;
	}

	public int getCurrItemIdx() {
		return currItemIdx;
	}

	public Item getCurrItem() {
		return currItem;
	}

	public void setNextItem() {
		currItemIdx++;
		if (currItemIdx > items.size() - 1) {
			customerDone();
			return;
		}
		this.currItem = items.get(currItemIdx);
	}

	public String getName() {
		return data.getName();
	}

	public List<Item> getItems() {
		return items;
	}

	public String getImgRelativePath() {
		return data.getImgRelativePath();
	}

	public void addListener(CustomerDoneListener listener) {
		listeners.add(listener);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect(500, 500, 200, 600);

		currItem.draw(g);
		typePanel.draw(g);
	}

	private void customerDone() {
		for (CustomerDoneListener listener : listeners) {
			listener.customerDone();
		}
	}

	public void activate() {
		this.currItem = items.get(currItemIdx);
		typePanel.reloadTextList(items);
		typePanel.inputLabel.setListener(this);
	}

	@Override
	public void newLineStarted() {
		setNextItem();
	}

	@Override
	public void textCompleted() {
		customerDone();
	}
}
