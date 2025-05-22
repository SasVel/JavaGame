package com.javaproject.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.interfaces.IObjectData;

public class CurrencyData implements IObjectData{
	private double currency = 0;
	@JsonIgnore private final String imgPathRelative = "/data/Props/coins.png";

	@JsonCreator
	public CurrencyData(
		@JsonProperty("currency") double _currency) {
		currency = _currency;
	}

	public CurrencyData() {
		super();
	}

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	@Override
	public String getName() {
		return Double.toString(currency);
	}

	@Override
	public String getImgPathRelative() {
		return imgPathRelative;
	}
}
