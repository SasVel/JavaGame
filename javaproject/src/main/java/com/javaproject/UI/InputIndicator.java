package com.javaproject.UI;

import java.awt.Graphics2D;

import com.javaproject.data.InputIndicatorData;
import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.interfaces.ITimerListener;
import com.javaproject.interfaces.IUpdateable;
import com.javaproject.models.DrawableObject;
import com.javaproject.util.Timer;

public class InputIndicator extends DrawableObject implements IUpdateable, ITimerListener {

	private final TypeInputLabel targetLabel;

	private final Timer blinkTimer = new Timer(0.4);
	private boolean isOn = true;

	private final int LineHeight = 3;
	
	public InputIndicator(TypeInputLabel _targetLabel) throws ResourceNotLoadedException {
		super(_targetLabel.getFontSize(), _targetLabel.getFontSize(), _targetLabel.getX(), _targetLabel.getY(), new InputIndicatorData());

		targetLabel = _targetLabel;

		blinkTimer.isRepeating = true;
		blinkTimer.addListener(this);
		blinkTimer.start();
	}

	public int getPosX() {
		return PosX;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public int getPosY() {
		return PosY;
	}

	public void setPosY(int posY) {
		PosY = posY;
	}

	public void updateRelativeToText() {
		PosX = targetLabel.getX() + ((targetLabel.getFontSize() / 2) * (targetLabel.getCurrIdx() + 1) - 8);
		PosY = targetLabel.getY() + (targetLabel.getFontSize() * (targetLabel.getCurrLine() + 1) - 5);
	}

	@Override
	public void update(double delta) {
		blinkTimer.update(delta);
	}

	@Override
	@SuppressWarnings("UnnecessaryReturnStatement")
	public void draw(Graphics2D g) {
		if (!isOn) return;

		if (targetLabel.isEndOfLine()) {
			super.draw(g);
		} else {
			g.fillRect(PosX - 5, PosY + 12, targetLabel.getFontSize() / 2, LineHeight);
		}
	}

	@Override
	public void onTimeout() {
		isOn = !isOn;
	}
}
