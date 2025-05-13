package com.javaproject.managers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.data.CurrencyData;

public class GameDataManager {
	ObjectMapper mapper = new ObjectMapper();
	public CurrencyData gameData;

	public GameDataManager() {
		try {
			gameData = mapper.readValue(getClass().getResource("/data/gameData.json"), CurrencyData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveData() {
		//mapper.writeValue(getClass().getResource("/data/gameData.json"), gameData);
	}
}
