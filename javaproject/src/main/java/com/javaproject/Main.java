package com.javaproject;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		window.setTitle("Alchemical Wonders");

		GamePanel gamePanel = new GamePanel(new BorderLayout());
		window.add(gamePanel);

		window.pack();

		// Not fatal if the icon is missing.
		try {
			URL url = gamePanel.getClass().getResource("/data/ItemImages/DragonSlices.png");
			BufferedImage icon = ImageIO.read(url);
			window.setIconImage(icon);
		} catch (IOException e) { }

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}