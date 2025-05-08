package com.javaproject.data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.javaproject.UI.TextLabel;
import com.javaproject.UI.TypePanel;
import com.javaproject.customers.CustomersManager;
import com.javaproject.interfaces.CustomerDoneListener;
import com.javaproject.interfaces.DayDoneListener;
import com.javaproject.interfaces.Drawable;
import com.javaproject.items.ItemsManager;

public class GameDay implements Drawable, CustomerDoneListener{
	private final long id;
	private List<Customer> customers = new ArrayList<>();
	private int currCustomerIdx = 0;
	private Customer currCustomer;
	
	private static long passedDays = 0;
	
	private TypePanel typePanel;
	private Random rand = new Random();
	private List<DayDoneListener> listeners = new ArrayList<>();

	private TextLabel dayLabel;
	
	public GameDay(TypePanel _typePanel, ItemsManager _items, CustomersManager customersManager) {
		passedDays++;
		id = passedDays;
		typePanel = _typePanel;

		this.customers.addAll(customersManager.getRandCustomersInRange(2, 6));
		
		setActiveCustomer(currCustomerIdx);
		configDayLabel();
	}

	private void configDayLabel() {
		dayLabel = new TextLabel(Color.white, 65);
		dayLabel.setX(750);
		dayLabel.setY(-10);
		dayLabel.textList.add("Day " + id);
	}

	public long getId() {
		return id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Customer getCurrCustomer() {
		return currCustomer;
	}

	public int getCurrCustomerIdx() {
		return currCustomerIdx;
	}
	
	public void setCurrCustomerIdx(int currCustomerIdx) {
		this.currCustomerIdx = currCustomerIdx;
	}

	private void setActiveCustomer(int idx) {
		currCustomer = customers.get(idx);
		currCustomer.activate();
		currCustomer.addListener(this);
	}

	public static long getPassedDays() {
		return passedDays;
	}

	public void addListener(DayDoneListener listener) {
		listeners.add(listener);
	}

	private void dayFinished() {
		for (DayDoneListener listener : listeners) {
			listener.dayFinished();
		}
	}

	@Override
	public void customerDone() {
		currCustomerIdx++;
		if (currCustomerIdx >= customers.size() - 1) {
			dayFinished();
			return;
		}

		setActiveCustomer(currCustomerIdx);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(36, 26, 1, 100));
		g.fillRect(0, 0, 1600, 100);
		dayLabel.draw(g);
		currCustomer.draw(g);
	}
}
