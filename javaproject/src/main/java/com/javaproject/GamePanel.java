package com.javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.javaproject.UI.TextLabel;
import com.javaproject.sound.SoundManager;
import com.javaproject.sound.SoundManager.SoundTypes;

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
	TextLabel label = new TextLabel();

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

	}

	public void update() {
		if (inputManager.currentState == InputManager.state.Pressed) {
			label.text += (char)inputManager.currKeyCode;
			soundManager.playSound(SoundTypes.TypeKey);
			inputManager.reset();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D)g;
		
		label.draw(graphics);

		graphics.dispose();
	}
}
