package com.javaproject.exceptions;

import java.io.IOException;

public class ResourceNotLoadedException extends IOException{
	public ResourceNotLoadedException(String resPath) {
		super("Resource not loaded at path" + resPath);
	}
}
