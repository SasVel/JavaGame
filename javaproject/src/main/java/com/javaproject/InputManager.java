package com.javaproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener{

	public enum state {
		Pressed,
		Released
	}

	public state currentState;
	public int currKeyCode;

	@Override
	public void keyPressed(KeyEvent arg0) {
		char keyChar = arg0.getKeyChar();
		if (Character.isLetterOrDigit(keyChar) 
			|| Character.isSpaceChar(keyChar)
			|| keyChar == KeyEvent.VK_MINUS
			|| keyChar == KeyEvent.VK_COMMA
			|| keyChar == KeyEvent.VK_ENTER) {
			currentState = state.Pressed;
			currKeyCode = arg0.getKeyChar();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		currentState = state.Released;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	public void reset()
	{
		currentState = null;
	}
}
