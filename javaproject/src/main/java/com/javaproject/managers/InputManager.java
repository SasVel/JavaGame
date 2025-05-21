package com.javaproject.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.javaproject.enums.InputState;

public class InputManager implements KeyListener{

	public InputState currentState;
	public int currKeyCode;

	@Override
	public void keyPressed(KeyEvent arg0) {
		char keyChar = arg0.getKeyChar();
		if (Character.isLetterOrDigit(keyChar) 
			|| Character.isSpaceChar(keyChar)
			|| keyChar == KeyEvent.VK_MINUS
			|| keyChar == KeyEvent.VK_COMMA
			|| keyChar == KeyEvent.VK_ENTER) {
			currentState = InputState.Pressed;
			currKeyCode = arg0.getKeyChar();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		currentState = InputState.Released;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	public void reset()
	{
		currentState = null;
	}
}
