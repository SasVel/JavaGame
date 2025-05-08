package com.javaproject.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TextLabel {

	public List<String> textList = new ArrayList<>();
	private int x = 200;
	private int y = 200;

	private static Font font;
	private Color color;
	private int fontSize = 40;

	public TextLabel(Color color) {
		try {
			InputStream is = getClass().getResourceAsStream("/font/SpecialElite.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, fontSize);
			this.color = color;
		} 
		catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(color);

		for (int i = 0; i < textList.size(); i++) {
			g.drawString(textList.get(i), x, (y + fontSize) + (fontSize * i));
		}
	}
}
