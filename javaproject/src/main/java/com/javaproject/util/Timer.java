package com.javaproject.util;

import java.util.ArrayList;
import java.util.List;

import com.javaproject.interfaces.ITimerListener;
import com.javaproject.interfaces.IUpdateable;


public class Timer implements IUpdateable {

	private final double time;
	private double elapsedTime = 0;
	private boolean isOn = false;
	public boolean isRepeating;

	private final List<ITimerListener> listeners = new ArrayList<>();

	public Timer(double _time) {
		time = _time;
	}

	public double getTime() {
		return time;
	}

	public boolean isOn() {
		return isOn;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void start() {
		isOn = true;
	}

	public void stop() {
		isOn = false;
		elapsedTime = 0;
	}

	public void reset() {
		elapsedTime = 0;
	}

	public float donePercent() {
		return (float)(elapsedTime / time);
	}

	private void timeout() {
		for (ITimerListener listener : listeners) {
			listener.onTimeout();
		}
		if (!isRepeating) stop();
		else reset();
	}


	public void addListener(ITimerListener listener) {
		listeners.add(listener);
	}

	@Override
	public void update(double delta) {
		if(!isOn) return;

		if (delta >= 0) {
			elapsedTime += delta / 100;
		}

		if (elapsedTime >= time) {
			timeout();
		}
	}

}
