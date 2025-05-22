package com.javaproject.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameData {
	
	public CurrencyData currencyData;
	public GameDayData dayData;

	@JsonCreator
	public GameData(
		@JsonProperty("currencyData") CurrencyData _currencyData,
		@JsonProperty("dayData") GameDayData _dayData,
		@JsonProperty("id") String _id) {
		currencyData = _currencyData;
		dayData = _dayData;
	}

	public GameData() {
		currencyData = new CurrencyData();
	}



	@JsonProperty("currencyData")
	public void deserializeCurrencyData(String data) {
		return;
	}

	public CurrencyData getCurrencyData() {
		return currencyData;
	}

	public void setCurrencyData(CurrencyData currencyData) {
		this.currencyData = currencyData;
	}

	public GameDayData getDayData() {
		return dayData;
	}

	public void setDayData(GameDayData dayData) {
		this.dayData = dayData;
	}
}
