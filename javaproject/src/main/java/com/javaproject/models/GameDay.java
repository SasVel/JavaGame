package com.javaproject.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.UI.MoneyTracker;
import com.javaproject.UI.TextLabel;
import com.javaproject.interfaces.ICustomerDoneListener;
import com.javaproject.interfaces.IDayDoneListener;
import com.javaproject.interfaces.IDrawable;
import com.javaproject.interfaces.ITimerListener;
import com.javaproject.managers.CustomersManager;
import com.javaproject.managers.ItemsManager;
import com.javaproject.util.Timer;

public class GameDay implements IDrawable, ICustomerDoneListener, ITimerListener{
	private final long id;
	
	private final List<Customer> customers = new ArrayList<>();
	private int currCustomerIdx = 0;
	private Customer currCustomer;
	
	private static long passedDays = 0;
	
	private final List<IDayDoneListener> listeners = new ArrayList<>();

	private TextLabel dayLabel;
	private final MoneyTracker moneyTracker;
	private final Timer dayTimer = new Timer(60 * 5);
	
	public GameDay(ItemsManager _items, CustomersManager customersManager, MoneyTracker _moneyTracker) {
		passedDays++;
		id = passedDays;
		moneyTracker = _moneyTracker;

		this.customers.addAll(customersManager.getRandCustomersInRange(6, 8));
		
		setActiveCustomer(currCustomerIdx);
		configDayLabel();

		dayTimer.addListener(this);
		dayTimer.start();
	}

	private void configDayLabel() {
		dayLabel = new TextLabel(Color.white, 65);
		dayLabel.setX(750);
		dayLabel.setY(0);
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

	public void addListener(IDayDoneListener listener) {
		listeners.add(listener);
	}

	private void dayFinished() {
		for (IDayDoneListener listener : listeners) {
			listener.dayFinished();
		}
	}

	@Override
	public void customerDone() {
		addMoney(customers.get(currCustomerIdx).getPriceCombined() * 0.2d);
		currCustomerIdx++;
		if (currCustomerIdx >= customers.size() - 1) {
			dayFinished();
			return;
		}

		setActiveCustomer(currCustomerIdx);
	}

	@Override
	public void onTimeout() {
		dayFinished();
	}

	private void addMoney(double money) {
		moneyTracker.addToAmmount(money);
	}

	public void update(double delta) {
		dayTimer.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(36, 26, 1, 100));
		g.fillRect(0, 0, 1600, 100);
		dayLabel.draw(g);
		drawDayTimer(g, dayLabel.getX() - 80, dayLabel.getY() + 50);
		currCustomer.draw(g);
	}

	private void drawDayTimer(Graphics2D g, int posX, int posY) {
		float timerDonePercent = dayTimer.donePercent();

		fillCircle(g, Color.BLUE, posX, posY, (int)(timerDonePercent * 40));
		drawCircle(g, Color.WHITE, posX, posY, 40);
	}

	public static void drawCircle(Graphics2D g, Color color, int x, int y, int radius) {
		int diameter = radius * 2;
		g.setColor(color);
		g.drawOval(x - radius, y - radius, diameter, diameter); 
	}

	public static void fillCircle(Graphics2D g, Color color, int x, int y, int radius) {
		int diameter = radius * 2;
		g.setColor(color);
		g.fillOval(x - radius, y - radius, diameter, diameter); 
	}
}
