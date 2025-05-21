package com.javaproject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.javaproject.interfaces.IDrawable;
import com.javaproject.interfaces.IUpdateable;
import com.javaproject.managers.InputManager;
import com.javaproject.managers.SoundManager;
import com.javaproject.models.Item;

public class TypePanel extends JPanel implements IDrawable, IUpdateable {

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
		displayLabel = new TypeDisplayLabel(Color.gray, 32);
		inputLabel = new TypeInputLabel(Color.black, 32, inputManager, soundManager);

		int textX = x + 210;
		int textY = y + 25;
		displayLabel.setX(textX);
		displayLabel.setY(textY);

		inputLabel.setX(textX);
		inputLabel.setY(textY);
		inputLabel.updateIndicator();
	}
	
	@Override
	public void update(double delta) {
		inputLabel.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(typewriterBG, x, y, null);

		displayLabel.draw(g);
		inputLabel.draw(g);
	}

	public void reloadTextList(List<Item> items) {
		displayLabel.loadText(items);
		inputLabel.loadTextList(displayLabel.textList);
	}
}
