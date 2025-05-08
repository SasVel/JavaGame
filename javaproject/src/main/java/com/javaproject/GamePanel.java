package com.javaproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.javaproject.UI.TypePanel;
import com.javaproject.items.ItemsManager;
import com.javaproject.sound.SoundManager;

public final class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	//120 for 1920x1080
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

	InputManager inputManager = new InputManager();
	SoundManager soundManager = new SoundManager();
	ItemsManager itemsManager = new ItemsManager();
	
	TypePanel typePanel = new TypePanel(inputManager, soundManager);


	GameController gameController = new GameController(typePanel, itemsManager);
	private Graphics2D graphics;

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
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(inputManager);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		onStart();

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			//Actual game loop
			if (delta >= 1) {

				update();
				repaint();

				delta--;
				toolkit.sync();
			}
		}
	}

	public void onStart() {
		this.add(typePanel, BorderLayout.EAST);
	}

	public void update() {
		typePanel.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = (Graphics2D)g;
		
		gameController.draw(graphics);

		graphics.dispose();
	}
}
