package com.javaproject.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.javaproject.UI.MoneyTracker;
import com.javaproject.UI.TextLabel;
import com.javaproject.data.CustomerData;
import com.javaproject.data.GameDayData;
import com.javaproject.exceptions.CorruptGameDataException;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.interfaces.ICustomerDoneListener;
import com.javaproject.interfaces.IDayDoneListener;
import com.javaproject.interfaces.IDrawable;
import com.javaproject.interfaces.IGameDataListener;
import com.javaproject.interfaces.ITimerListener;
import com.javaproject.managers.CustomersManager;
import com.javaproject.managers.GameDataManager;
import com.javaproject.managers.ItemsManager;
import com.javaproject.util.Timer;

public class GameDay implements IDrawable, ICustomerDoneListener, ITimerListener, IGameDataListener{
	private final long id;
	
	private final LinkedList<Customer> customers;
	
	private static long passedDays = 0;
	
	private final List<IDayDoneListener> listeners;

	private TextLabel dayLabel;
	private final MoneyTracker moneyTracker;
	private final GameDataManager gameDataManager;
	private final CustomersManager customersManager;
	private GameDayData dayData;
	private final Timer dayTimer;
	
	public GameDay(ItemsManager _items, CustomersManager _customersManager, GameDataManager _gameDataManager, MoneyTracker _moneyTracker) {
		passedDays++;
		id = passedDays;

		dayTimer = new Timer(60 * 5);
		
		listeners = new ArrayList<>();
		customers = new LinkedList<>();
		moneyTracker = _moneyTracker;
		gameDataManager = _gameDataManager;
		customersManager = _customersManager;
		
		if (gameDataManager.getDayData() == null) {
			createData();
		} else { 
			dayData = gameDataManager.getDayData();
			loadData(dayData);
		}

		setActiveCustomer();
		configDayLabel();

		dayTimer.addListener(this);
		gameDataManager.addListener(this);
		dayTimer.start();
	}

	private void createData() {
		ArrayList<CustomerData> customersData = customersManager.getRandCustomerDataInRange(6, 8);
		customersData.stream().forEach(cd -> {
			try {
				customers.add(customersManager.getNewCustomer(cd));
			} catch (ResourceNotLoadedException e) {}
		});
		dayData = new GameDayData(0, customersData);
	}

	private void loadData(GameDayData data) {
		dayTimer.setElapsedTime(data.getElapsedTime());
		data.getCustomersData().stream().forEach(cd -> {
			try {
				customers.add(customersManager.getNewCustomer(cd));
			} catch (ResourceNotLoadedException e) {
				e.printStackTrace();
			}
		});
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

	public LinkedList<Customer> getCustomers() {
		return customers;
	}

	public Customer getCurrCustomer() {
		return customers.peekFirst();
	}

	private void setActiveCustomer() {
		Customer currCustomer = customers.peekFirst();
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
		listeners.stream().forEach(c -> c.dayFinished());
	}

	@Override
	public void customerDone() {
		addMoney(customers.pop().getPriceCombined() * 0.2d);
		if (customers.isEmpty()) {
			dayFinished();
			return;
		}

		setActiveCustomer();
		try {
			gameDataManager.saveData();
		} catch (CorruptGameDataException e) {}
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
		getCurrCustomer().draw(g);
	}

	private void drawDayTimer(Graphics2D g, int posX, int posY) {
		float timerDonePercent = dayTimer.getDonePercent();

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

	@Override
	public void toSaveData() {
		dayData.clearCustomersData();
		customers.stream().forEach(c -> dayData.addToCustomersData(c.getData()));
		dayData.setElapsedTime(dayTimer.getElapsedTime());
		gameDataManager.setDayData(dayData);
	}
}
