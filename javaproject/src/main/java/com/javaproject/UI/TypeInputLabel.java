package com.javaproject.UI;

import java.awt.Color;
import java.awt.event.KeyEvent;
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
	public boolean isTextCompleted = false;

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
		if (input.currentState != InputManager.state.Pressed && !isTextCompleted) return;

		char keyChar = (char)input.currKeyCode;
		String currLineStr = expectedTextList.get(currLine);

		if (currIdx >= currLineStr.length()
			&& keyChar == KeyEvent.VK_ENTER) {
			if (currLine + 1 >= expectedTextList.size()) {
				ResetTextBox();
				isTextCompleted = true;
			} else {
				SetNewLine();
			}

			sound.playSound(SoundTypes.EnterKey);
		} else if (currLineStr.length() != currIdx
			&& keyChar == currLineStr.charAt(currIdx)) {
		
			textList.set(currLine, textList.get(currLine) + keyChar);
			currIdx++;
			input.reset();

			if (keyChar == KeyEvent.VK_SPACE) {
				sound.playSound(SoundTypes.SpaceKey);
			} else {
				sound.playSound(SoundTypes.TypeKey);
			}

		}
	}

	private void SetNewLine() {
		currIdx = 0;
		currLine++;
	}

	private void ResetTextBox() {
		currIdx = 0;
		currLine = 0;
	}
}
