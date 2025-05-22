package com.javaproject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.javaproject.UI.TypePanel;
import com.javaproject.exceptions.CorruptGameDataException;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.managers.CustomersManager;
import com.javaproject.managers.GameController;
import com.javaproject.managers.GameDataManager;
import com.javaproject.managers.InputManager;
import com.javaproject.managers.ItemsManager;
import com.javaproject.managers.SoundManager;

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

	GameDataManager gameDataManager;
	InputManager inputManager;
	SoundManager soundManager;
	ItemsManager itemsManager;
	
	TypePanel typePanel;
	CustomersManager customersManager;


	GameController gameController;
	private Graphics2D graphics;

	//Resources
	BufferedImage backgroundImage;

	public GamePanel() throws ResourceNotLoadedException {
		super();

		try {
			gameDataManager = new GameDataManager();
			inputManager = new InputManager();
			soundManager = new SoundManager();
			itemsManager = new ItemsManager();

			typePanel = new TypePanel(inputManager, soundManager);
			customersManager = new CustomersManager(typePanel, itemsManager);
			gameController = new GameController(itemsManager, customersManager, gameDataManager);
		} catch (CorruptGameDataException | ResourceNotLoadedException e) {
			System.exit(0);
		}
		configure();
	}

	public GamePanel(LayoutManager layout) throws ResourceNotLoadedException {
		super(layout);
		configure();
	}

	public void configure() throws ResourceNotLoadedException {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(inputManager);
		this.setFocusable(true);

		URL resPath = getClass().getResource("/data/Background/BG.jpg");
		try {
			backgroundImage = ImageIO.read(resPath);
		} catch (IOException e) {
			throw new ResourceNotLoadedException(resPath.getPath());
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
	}

	public void update(double delta) {
		gameController.update(delta);
		typePanel.update(delta);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = (Graphics2D)g;
		graphics.drawImage(backgroundImage, 0, 0, null);

		if (gameController != null) { gameController.draw(graphics); }
		
		graphics.dispose();
	}
}
