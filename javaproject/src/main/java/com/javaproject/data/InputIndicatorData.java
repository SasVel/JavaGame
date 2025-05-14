package com.javaproject.data;

import com.javaproject.interfaces.ObjectData;

public class InputIndicatorData implements ObjectData {
	private String imgPathRelative = "/data/Props/NewLine.png";

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

	@Override
	public String getImgPathRelative() {
		return imgPathRelative;
	}

}
