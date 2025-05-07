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
	public int x = 200;
	public int y = 200;

	private Font font;
	private Color color;
	private int fontSize = 45;

	public TextLabel(Color color) {
		try {
			InputStream is = getClass().getResourceAsStream("/font/SpecialElite.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, fontSize);
			this.color = color;
		} 
		catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(color);

		for (int i = 0; i < textList.size(); i++) {
			g.drawString(textList.get(i), x, (y + fontSize) + (fontSize * i));
		}
	}
}
