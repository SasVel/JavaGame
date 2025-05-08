package com.javaproject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.javaproject.InputManager;
import com.javaproject.data.Item;
import com.javaproject.interfaces.Drawable;
import com.javaproject.sound.SoundManager;

public class TypePanel extends JPanel implements Drawable {

	public TypeDisplayLabel displayLabel;
	public TypeInputLabel inputLabel;

	InputManager inputManager;
	SoundManager soundManager;

	public final int x = 800;
	public final int y = 400;

	//Resources
	BufferedImage typewriterBG;

	public TypePanel(InputManager _inputManager, SoundManager _soundManager) {
		super(new BorderLayout());
		inputManager = _inputManager;
		soundManager = _soundManager;

		initComponenets();

		try {
			typewriterBG = ImageIO.read(getClass().getResource("/data/Props/Typewriter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponenets() {
		displayLabel = new TypeDisplayLabel(Color.gray);
		inputLabel = new TypeInputLabel(Color.black, inputManager, soundManager);

		int textX = x + 210;
		int textY = y + 25;
		displayLabel.setX(textX);
		displayLabel.setY(textY);

		inputLabel.setX(textX);
		inputLabel.setY(textY);
	}
	
	public void update() {
		inputLabel.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(typewriterBG, x, y, null);

		displayLabel.draw(g);
		inputLabel.draw(g);
	}

	public void reloadTextList(List<Item> items) {
		displayLabel.loadItems(items);
		inputLabel.loadTextList(displayLabel.textList);
	}
}
