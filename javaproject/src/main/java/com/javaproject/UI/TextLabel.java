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

	private Font font;
	protected Color color;
	private float fontSize = 40;
	private int lineSeperation = 5;

	protected String seperator = "----------------";
	public boolean isAutowrap = false;
	public int autowrapCharNum = 25;

	public TextLabel(Color color, float _fontSize) {
		try {
			InputStream is = getClass().getResourceAsStream("/font/SpecialElite.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, _fontSize);
			this.color = color;
			fontSize = _fontSize;
		} 
		catch (FontFormatException | IOException e) { e.printStackTrace(); }
	}

	public List<String> getTextList() {
		return textList;
	}

	public void setTextList(List<String> textList) {
		this.textList = textList;
	}

	public void addToText(String text) {
		if (isAutowrap && text.length() > autowrapCharNum) {
			while (text != "") {
				String croppedText = text.substring(0, Math.min(autowrapCharNum, text.length()));
				textList.add(croppedText);
				text = text.replace(croppedText, "");
			}
		} else {
			textList.add(text);
		}
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

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		font = font.deriveFont(Font.PLAIN, fontSize);
	}

	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(color);

		for (int i = 0; i < textList.size(); i++) {
			g.drawString(textList.get(i), x, (y + fontSize + lineSeperation) + (fontSize * i));
		}
	}
}
