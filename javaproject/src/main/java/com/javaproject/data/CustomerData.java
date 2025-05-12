package com.javaproject.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.interfaces.ObjectData;

public class CustomerData implements ObjectData{
	private final String name;
	private final String occupation;
	private final String imgPathRelative;

	@JsonCreator
	public CustomerData(
		@JsonProperty("name") String _name, 
		@JsonProperty("Occupation") String _occupation) {
		name = _name;
		occupation = _occupation;
		imgPathRelative = "/data/CustomerImages/" + name.replace(" ", "") + ".png";
	}

	@Override
	public String getName() {
		return name;
	}
	public String getOccupation() {
		return occupation;
	}

	@Override
	public String getImgPathRelative() {
		return imgPathRelative;
	}
}
