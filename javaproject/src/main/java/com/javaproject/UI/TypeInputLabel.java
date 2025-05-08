package com.javaproject.UI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.InputManager;
import com.javaproject.interfaces.TypingInputListener;
import com.javaproject.sound.SoundManager;
import com.javaproject.sound.SoundManager.SoundTypes;

public class TypeInputLabel extends TextLabel {

	private final InputManager input;
	private final SoundManager sound;

	public List<String> expectedTextList = new ArrayList<>();
	private int currIdx = 0;
	private int currLine = 0;
	public boolean isTextCompleted = false;

	private TypingInputListener listener;

	public TypeInputLabel(Color color, int _fontSize, InputManager inputManager, SoundManager soundManager) {
		super(color, _fontSize);
		input = inputManager;
		sound = soundManager;
	}

	public void loadTextList(List<String> list) {
		expectedTextList = list;
		textList.clear();
		for (int i = 0; i < list.size() + 2; i++) {
			textList.add("");
		}
	}
	
	public void update() {
		if (input.currentState != InputManager.state.Pressed && !isTextCompleted) return;

		char keyChar = (char)input.currKeyCode;
		String currLineStr = expectedTextList.get(currLine);

		if (currIdx >= currLineStr.length()
			&& keyChar == KeyEvent.VK_ENTER) {
			if (currLine + 1 >= expectedTextList.size()) {
				resetTextBox();
				textCompleted();
			} else {
				setNewLine();
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

	public TypingInputListener getListener() {
		return listener;
	}

	public void setListener(TypingInputListener listener) {
		this.listener = listener;
	}

	private void newLineStarted() {
		listener.newLineStarted();
	}

	private void textCompleted() {
		listener.textCompleted();
	}

	private void setNewLine() {
		currIdx = 0;
		currLine++;
		newLineStarted();
	}

	private void resetTextBox() {
		currIdx = 0;
		currLine = 0;
	}

	public void addSeperator() {
		textList.set(currLine, seperator);
		currLine++;
	}
}
