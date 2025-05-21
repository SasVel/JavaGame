package com.javaproject.data;

import com.javaproject.interfaces.IObjectData;

public class CurrencyData implements IObjectData{
	private double currency = 0;

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
