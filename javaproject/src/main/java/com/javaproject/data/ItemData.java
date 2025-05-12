package com.javaproject.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.interfaces.ObjectData;

public class ItemData implements ObjectData{
	private final String name;
	private final String desc;
	private final double price;
	private final short difficulty;
	private final String imgPathRelative;


	@JsonCreator
	public ItemData(
		@JsonProperty("name") String name, 
		@JsonProperty("desc") String desc, 
		@JsonProperty("price") float price, 
		@JsonProperty("difficulty") short difficulty) {
		
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.difficulty = difficulty;
		this.imgPathRelative = "/data/ItemImages/" + name.replace(" ", "") + ".png";
	}

	@Override
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public double getPrice() {
		return price;
	}

	public short getDifficulty() {
		return difficulty;
	}

	@Override
	public String getImgPathRelative() {
		return imgPathRelative;
	}
}
