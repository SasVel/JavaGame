package com.javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.javaproject.UI.TypeDisplayLabel;
import com.javaproject.UI.TypeInputLabel;
import com.javaproject.items.ItemsManager;
import com.javaproject.sound.SoundManager;

public class GamePanel extends JPanel implements Runnable{
	// Screen settings
	final int orginialTileSize = 16; //X and Y
	final int scale = 3;

	final int tileSize = orginialTileSize * scale;
	final int screenCol = 16;
	final int screenRow = 12;
	final int screenWidth = tileSize * screenCol;
	final int screenHeight = tileSize * screenRow;

	Thread gameThread;
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	InputManager inputManager = new InputManager();
	SoundManager soundManager = new SoundManager();
	ItemsManager itemsManager = new ItemsManager();

	//TextLabel label = new TextLabel();
	TypeDisplayLabel typeDisplayLabel = new TypeDisplayLabel(Color.gray);
	TypeInputLabel typeInputLabel = new TypeInputLabel(Color.white, inputManager, soundManager);

	public GamePanel() {
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
		while (gameThread != null) {

			update();
			repaint();

			toolkit.sync();
		}
	}

	public void onStart() {
		typeDisplayLabel.LoadItems(itemsManager.getAllItems());
		typeInputLabel.LoadTextList(typeDisplayLabel.textList);
	}

	public void update() {
		typeInputLabel.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D)g;
		
		typeDisplayLabel.draw(graphics);
		typeInputLabel.draw(graphics);

		graphics.dispose();
	}
}
