package com.javaproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.javaproject.UI.TypePanel;
import com.javaproject.customers.CustomersManager;
import com.javaproject.data.CurrencyData;
import com.javaproject.items.ItemsManager;
import com.javaproject.sound.SoundManager;

public final class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	//120 for 1920x1080
	//current is 1600x900
	final int tileSize = 100;
	final int screenAspectX = 16;
	final int screenAspectY = 9;
	final int screenWidth = tileSize * screenAspectX;
	final int screenHeight = tileSize * screenAspectY;

	//FPS
	final int FPS = 60;

	//INIT
	Thread gameThread;
	Toolkit toolkit = Toolkit.getDefaultToolkit();


	CurrencyData gameData = new CurrencyData();
	InputManager inputManager = new InputManager();
	SoundManager soundManager = new SoundManager();
	ItemsManager itemsManager = new ItemsManager();
	
	TypePanel typePanel = new TypePanel(inputManager, soundManager);
	CustomersManager customersManager = new CustomersManager(typePanel, itemsManager);


	GameController gameController = new GameController(itemsManager, customersManager, gameData);
	private Graphics2D graphics;

	//Resources
	BufferedImage backgroundImage;

	public GamePanel() {
		super();
		configure();
	}

	public GamePanel(LayoutManager layout) {
		super(layout);
		configure();
	}

	public void configure() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(inputManager);
		this.setFocusable(true);

		try {
			backgroundImage = ImageIO.read(getClass().getResource("/data/Background/BG.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		onStart();

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			//Actual game loop
			if (delta >= 1) {

				update(delta);
				repaint();

				delta--;
				toolkit.sync();
			}
		}
	}

	public void onStart() {
		this.add(typePanel, BorderLayout.EAST);
	}

	public void update(double delta) {
		gameController.update(delta);
		typePanel.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = (Graphics2D)g;
		graphics.drawImage(backgroundImage, 0, 0, null);

		gameController.draw(graphics);
		
		// graphics.setColor(new Color(110, 39, 39));
		// graphics.fillRect(0, (screenHeight / 5) * 4, screenWidth, screenHeight / 5);

		graphics.dispose();
	}
}
