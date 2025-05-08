package com.javaproject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

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

	Color bg = Color.MAGENTA;

	public TypePanel(InputManager _inputManager, SoundManager _soundManager) {
		super(new BorderLayout());
		inputManager = _inputManager;
		soundManager = _soundManager;

		initComponenets();
	}

	private void initComponenets() {
		displayLabel = new TypeDisplayLabel(Color.gray);
		inputLabel = new TypeInputLabel(Color.white, inputManager, soundManager);
	}
	
	public void update() {
		inputLabel.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(bg);
		g.fillRect(0, 0, 320, 320);

		displayLabel.draw(g);
		inputLabel.draw(g);
	}

	public void reloadTextList(List<Item> items) {
		displayLabel.loadItems(items);
		inputLabel.loadTextList(displayLabel.textList);
	}
}
