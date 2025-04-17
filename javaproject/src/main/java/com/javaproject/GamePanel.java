package com.javaproject;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel {
	// Screen settings
	final int orginialTileSize = 16; //X and Y
	final int scale = 3;

	final int tileSize = orginialTileSize * scale;
	final int screenCol = 16;
	final int screenRow = 12;
	final int screenWidth = tileSize * screenCol;
	final int screenHeight = tileSize * screenRow;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.MAGENTA);
		this.setDoubleBuffered(true);
	}
}
