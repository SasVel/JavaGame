package com.javaproject.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	private final long id;
	private final String name;
	private final String desc;
	private final double price;
	private final short difficulty;
	private final String imgPathRelative;

	private static long numOfItems = 0;
	
	@JsonCreator
	public Item(
		@JsonProperty("name") String name, 
		@JsonProperty("desc") String desc, 
		@JsonProperty("price") float price, 
		@JsonProperty("difficulty") short difficulty, 
		@JsonProperty("imgPathRelative") String imgPathRelative) {
		
		numOfItems++;
		this.id = numOfItems;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.difficulty = difficulty;
		this.imgPathRelative = imgPathRelative;
	}

	public long getId() {
		return id;
	}

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
	public String getImgPathRelative() {
		return imgPathRelative;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", this.getName(), String.format("%.2f", this.getPrice()));
	}

	
}
