package com.javaproject.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.CurrencyData;
import com.javaproject.data.GameData;
import com.javaproject.data.GameDayData;
import com.javaproject.exceptions.CorruptGameDataException;
import com.javaproject.interfaces.IGameDataListener;

public final class GameDataManager {
	ObjectMapper mapper;
	private GameData gameData;
	URL dataPath;

	private final List<IGameDataListener> listeners;

	public GameDataManager() throws CorruptGameDataException {
		mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		listeners = new ArrayList<>();
		try {
			dataPath = getClass().getResource("/data/gameData.json");
			gameData = mapper.readValue(dataPath, GameData.class);
			if (gameData.getDayData() == null) {
				createGameData();
				saveData();
			}
		} catch (IOException e) {
			createGameData();
			saveData();
		}
	}

	public CurrencyData getCurrencyData() {
		return gameData.currencyData;
	}

	public GameDayData getDayData() {
		return gameData.dayData;
	}

	public void setDayData(GameDayData data) {
		gameData.dayData = data;
	}

	private void createGameData() {
		gameData = new GameData();
	}

	public void addListener(IGameDataListener listener) {
		listeners.add(listener);
	}

	private void fetchData() {
		listeners.stream().forEach(l -> l.toSaveData());
	}

	public void saveData() throws CorruptGameDataException {
		fetchData();

		try {
			File file = new File(dataPath.toURI());
			String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameData);
			mapper.writeValue(file, jsonStr);
			System.err.println(jsonStr);
		} catch (FileNotFoundException e) {
			throw new CorruptGameDataException();
		} catch (IOException | URISyntaxException e) {
			throw new CorruptGameDataException();
		}
	}
}
