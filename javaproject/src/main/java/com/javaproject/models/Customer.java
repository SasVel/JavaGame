package com.javaproject.models;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.UI.TypePanel;
import com.javaproject.data.CustomerData;
import com.javaproject.interfaces.ICustomerDoneListener;
import com.javaproject.interfaces.ITypingInputListener;
import com.javaproject.managers.ItemsManager;

public class Customer extends DrawableObject implements ITypingInputListener {
	private final long id;
	protected CustomerData data;
	private final TypePanel typePanel;
	private final List<Item> items;
	private int currItemIdx = 0;
	private Item currItem;

	private final List<ICustomerDoneListener> listeners = new ArrayList<>();


	public Customer(int _width, int _height, int _posX, int _posY, long _id, CustomerData _data, TypePanel _typePanel, ItemsManager _itemsManager) {
		super(_width, _height, _posX, _posY, _data);
		id = _id;

		this.data = _data;
		this.typePanel = _typePanel;
		this.items = _itemsManager.getRandItemsInRange(1, 4);
		this.currItemIdx = 0;
		this.currItem = items.get(currItemIdx);
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

	public boolean setNextItem() {
		if (currItemIdx + 1 > items.size() - 1) return false;
		currItemIdx++;
		this.currItem = items.get(currItemIdx);
		return true;
	}

	public String getName() {
		return data.getName();
	}

	public List<Item> getItems() {
		return items;
	}

	public String getImgRelativePath() {
		return data.getImgPathRelative();
	}

	public void addListener(ICustomerDoneListener listener) {
		listeners.add(listener);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		currItem.draw(g);
		typePanel.draw(g);
	}

	private void customerDone() {
		for (ICustomerDoneListener listener : listeners) {
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
		boolean res = setNextItem();
		if (!res) {
			typePanel.inputLabel.addSeperator();
		}
	}

	@Override
	public void textCompleted() {
		customerDone();
	}

	public double getPriceCombined() {
		double res = items
			.stream()
			.mapToDouble(i -> i.getData().getPrice())
			.sum();
		
		return res;
	}
}
