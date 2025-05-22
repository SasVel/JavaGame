package com.javaproject.exceptions;

public class CorruptGameDataException extends Exception {
	public CorruptGameDataException() {
		super("Corrupt game data!");
	}
}
