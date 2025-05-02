package com.javaproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener{

	public enum state {
		Pressed,
		Released
	}

	public state currentState;
	public char keyChar;

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (Character.isAlphabetic(arg0.getKeyChar()) || arg0.getKeyChar() == KeyEvent.VK_SPACE) {
			currentState = state.Pressed;
			keyChar = arg0.getKeyChar();
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
