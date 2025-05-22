package com.javaproject.data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDayData {
	private double elapsedTime;
	private List<CustomerData> customersData;

	@JsonCreator
	public GameDayData(
		@JsonProperty("elapsedTime") double _elapsedTime, 
		@JsonProperty("customersData") ArrayList<CustomerData> _customersData) {
		elapsedTime = _elapsedTime;
		customersData = _customersData;
	}

	public List<CustomerData> getCustomersData() {
		return customersData;
	}

	public void addToCustomersData(CustomerData data) {
		customersData.add(data);
	}

	public void setCustomersData(List<CustomerData> customersData) {
		this.customersData = customersData;
	}

	public void clearCustomersData() {
		customersData.clear();
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
