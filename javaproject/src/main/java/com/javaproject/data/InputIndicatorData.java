package com.javaproject.data;

import com.javaproject.interfaces.IObjectData;

public class InputIndicatorData implements IObjectData {
	private final String imgPathRelative = "/data/Props/NewLine.png";

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

	@Override
	public String getImgPathRelative() {
		return imgPathRelative;
	}

}
