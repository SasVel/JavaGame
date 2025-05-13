package com.javaproject;

import java.util.ArrayList;
import java.util.List;

import com.javaproject.interfaces.TimerListener;


public class Timer {

	private final double time;
	private double elapsedTime = 0;
	private boolean isOn = false;

	private List<TimerListener> listeners = new ArrayList<>();

	public Timer(double _time) {
		time = _time;
	}

	public double getTime() {
		return time;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void start() {
		isOn = true;
	}

	public void reset() {
		isOn = false;
		elapsedTime = 0;
	}

	public float donePercent() {
		return (float)(elapsedTime / time);
	}

	private void timeout() {
		for (TimerListener listener : listeners) {
			listener.onTimeout();
		}
		reset();
	}


	public void addListener(TimerListener listener) {
		listeners.add(listener);
	}

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
