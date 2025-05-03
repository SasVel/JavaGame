package com.javaproject.UI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.InputManager;
import com.javaproject.sound.SoundManager;
import com.javaproject.sound.SoundManager.SoundTypes;

public class TypeInputLabel extends TextLabel {

	private InputManager input;
	private SoundManager sound;

	public List<String> expectedTextList = new ArrayList<>();
	private int currIdx = 0;
	private int currLine = 0;

	public TypeInputLabel(Color color, InputManager inputManager, SoundManager soundManager) {
		super(color);
		input = inputManager;
		sound = soundManager;
	}

	public void LoadTextList(List<String> list) {
		expectedTextList = list;
		textList.clear();
		for (String str : list) { textList.add(""); }
	}
	
	public void update() {
		if (input.currentState == InputManager.state.Pressed
			&& (char)input.currKeyCode == expectedTextList.get(currLine).charAt(currIdx)) {
			textList.set(currLine, textList.get(currLine) + (char)input.currKeyCode);

			currIdx++;
			sound.playSound(SoundTypes.TypeKey);
			input.reset();

			if (expectedTextList.get(currLine).length() == currIdx) {
				currIdx = 0;
				currLine++;
			}
		}
	}
}
