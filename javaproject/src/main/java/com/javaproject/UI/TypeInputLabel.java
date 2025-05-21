package com.javaproject.UI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.javaproject.enums.InputState;
import com.javaproject.enums.SoundTypes;
import com.javaproject.interfaces.IUpdateable;
import com.javaproject.interfaces.ITimerListener;
import com.javaproject.interfaces.ITypingInputListener;
import com.javaproject.managers.InputManager;
import com.javaproject.managers.SoundManager;
import com.javaproject.util.Timer;

public class TypeInputLabel extends TextLabel implements IUpdateable, ITimerListener {

	private final InputManager input;
	private final SoundManager sound;

	public List<String> expectedTextList = new ArrayList<>();
	private int currIdx = 0;
	private int currLine = 0;
	public boolean isTextCompleted = false;

	private ITypingInputListener listener;
	private Color wrongInputColor = Color.RED;
	private final Timer wrongInputTimer = new Timer(0.3);
	private final InputIndicator indicator = new InputIndicator(this);

	public TypeInputLabel(Color color, int _fontSize, InputManager inputManager, SoundManager soundManager) {
		super(color, _fontSize);
		input = inputManager;
		sound = soundManager;

		wrongInputTimer.addListener(this);
	}

	public int getCurrIdx() {
		return currIdx;
	}

	public int getCurrLine() {
		return currLine;
	}

	public boolean isEndOfLine() {
		String currLineStr = expectedTextList.get(currLine);
		return currIdx >= currLineStr.length();
	}

	public void loadTextList(List<String> list) {
		expectedTextList = list;
		textList.clear();
		for (int i = 0; i < list.size() + 2; i++) {
			textList.add("");
		}
	}
	
	@Override
	public void update(double delta) {
		wrongInputTimer.update(delta);
		indicator.update(delta);

		if (input.currentState != InputState.Pressed && !isTextCompleted) return;

		char keyChar = (char)input.currKeyCode;
		String currLineStr = expectedTextList.get(currLine);

		if (isEndOfLine()
			&& keyChar == KeyEvent.VK_ENTER) {
			if (currLine + 1 >= expectedTextList.size()) {
				resetTextBox();
				textCompleted();
			} else {
				setNewLine();
			}

			indicator.updateRelativeToText();
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
			
			indicator.updateRelativeToText();
			input.reset();
		} else {
			onIncorrectInput();
			input.reset();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		indicator.draw(g);
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

	public ITypingInputListener getListener() {
		return listener;
	}

	public void setListener(ITypingInputListener listener) {
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

	public void updateIndicator() {
		indicator.updateRelativeToText();
	}

}
