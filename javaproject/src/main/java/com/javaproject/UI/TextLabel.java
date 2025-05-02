package com.javaproject.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

public class TextLabel {

	public String text = "";
	public int x, y = 200;

	private Font font;

	public TextLabel() {
		try {
			InputStream is = getClass().getResourceAsStream("/font/SpecialElite.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 45);
		} 
		catch (FontFormatException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text, x, y);
	}
}
