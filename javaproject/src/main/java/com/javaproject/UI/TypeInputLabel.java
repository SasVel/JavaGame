package com.javaproject.UI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.interfaces.TimerListener;
import com.javaproject.interfaces.TypingInputListener;
import com.javaproject.managers.InputManager;
import com.javaproject.managers.SoundManager;
import com.javaproject.managers.SoundManager.SoundTypes;
import com.javaproject.util.Timer;

public class TypeInputLabel extends TextLabel implements TimerListener {

	private final InputManager input;
	private final SoundManager sound;

	public List<String> expectedTextList = new ArrayList<>();
	private int currIdx = 0;
	private int currLine = 0;
	public boolean isTextCompleted = false;

	private TypingInputListener listener;
	private Color wrongInputColor = Color.RED;
	private Timer wrongInputTimer = new Timer(0.3);

	public TypeInputLabel(Color color, int _fontSize, InputManager inputManager, SoundManager soundManager) {
		super(color, _fontSize);
		input = inputManager;
		sound = soundManager;

		wrongInputTimer.addListener(this);
	}

	public void loadTextList(List<String> list) {
		expectedTextList = list;
		textList.clear();
		for (int i = 0; i < list.size() + 2; i++) {
			textList.add("");
		}
	}
	
	public void update(double delta) {
		wrongInputTimer.update(delta);

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
			input.reset();
		} else if (currLineStr.length() != currIdx
			&& keyChar == currLineStr.charAt(currIdx)) {
		
			textList.set(currLine, textList.get(currLine) + keyChar);
			currIdx++;
			
			if (keyChar == KeyEvent.VK_SPACE) {
				sound.playSound(SoundTypes.SpaceKey);
			} else {
				sound.playSound(SoundTypes.TypeKey);
			}
			
			input.reset();
		} else {
			onIncorrectInput();
			input.reset();
		}
	}

	private void onIncorrectInput() {
		if (wrongInputTimer.isOn()) {
			wrongInputTimer.reset();
			return;
		}

		Color tempColor = color;
		color = wrongInputColor;
		wrongInputColor = tempColor;
		wrongInputTimer.start();
	}

	@Override
	public void onTimeout() {
		Color tempColor = color;
		color = wrongInputColor;
		wrongInputColor = tempColor;
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
