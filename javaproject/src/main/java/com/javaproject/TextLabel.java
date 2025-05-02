package com.javaproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextLabel {

	public String text = "";
	public int x, y = 200;

	public void draw(Graphics2D g) {
		g.setFont(new Font("Ariel", Font.BOLD, 28));
		g.setColor(Color.WHITE);
		g.drawString(text, x, y);
	}
}
