package com.javaproject.data;

import com.javaproject.interfaces.ObjectData;

public class CurrencyData implements ObjectData{
	private double currency;

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
		return "/data/Props/coins.png";
	}
}
