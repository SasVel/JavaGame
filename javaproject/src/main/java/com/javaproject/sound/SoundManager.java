package com.javaproject.sound;

import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	Clip clip;
	Random rand = new Random();

	URL typeKeySounds[] = new URL[2];
	URL spaceKeySounds[] = new URL[1];
	URL enterKeySounds[] = new URL[1];

	public enum SoundTypes {
		TypeKey,
		SpaceKey,
		EnterKey
	}

	public SoundManager() {
		typeKeySounds[0] = getClass().getResource("/musicAndSounds/sounds/KeyPress_1.wav");
		typeKeySounds[1] = getClass().getResource("/musicAndSounds/sounds/KeyPress_2.wav");

		spaceKeySounds[0] = getClass().getResource("/musicAndSounds/sounds/SpacePress_1.wav");

		enterKeySounds[0] = getClass().getResource("/musicAndSounds/sounds/EnterPress_1.wav");
	}

	public void playSound(SoundTypes type) {
		switch (type) {
			case TypeKey:
				playRandSound(typeKeySounds);
				break;
			case SpaceKey:
				playRandSound(spaceKeySounds);
				break;
			case EnterKey:
				playRandSound(enterKeySounds);
				break;
		}
	}

	private void playRandSound(URL arr[]) {
		int n = rand.nextInt(arr.length);
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(arr[n]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
