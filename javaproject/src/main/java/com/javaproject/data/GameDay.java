package com.javaproject.data;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.javaproject.UI.TypePanel;
import com.javaproject.interfaces.CustomerDoneListener;
import com.javaproject.interfaces.DayDoneListener;
import com.javaproject.interfaces.Drawable;
import com.javaproject.items.ItemsManager;

public class GameDay implements Drawable, CustomerDoneListener{

	private Random rand = new Random();
	private TypePanel typePanel;

	private final long id;
	private List<Customer> customers = new ArrayList<>();
	private int currCustomerIdx = 0;
	private Customer currCustomer;

	private static long passedDays = 0;

	private List<DayDoneListener> listeners = new ArrayList<>();
	
	public GameDay(TypePanel _typePanel, ItemsManager items) {
		passedDays++;
		id = passedDays;
		typePanel = _typePanel;

		for (int i = 0; i < rand.nextInt(3, 6); i++) {
			Customer newCustomer = new Customer("Goshko", typePanel, items, null);
			this.customers.add(newCustomer);
		}
		
		setActiveCustomer(currCustomerIdx);
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
		currCustomer.draw(g);
	}
}
