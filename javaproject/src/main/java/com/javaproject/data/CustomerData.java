package com.javaproject.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerData {
	private final String name;
	private final String imgRelativePath;

	@JsonCreator
	public CustomerData(
		@JsonProperty("name") String _name, 
		@JsonProperty("imgRelativePath") String _imgRelativePath) {
		name = _name;
		imgRelativePath = _imgRelativePath;
	}

	public String getName() {
		return name;
	}

	public String getImgRelativePath() {
		return imgRelativePath;
	}

}
