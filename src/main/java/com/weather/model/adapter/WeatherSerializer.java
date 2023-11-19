package com.weather.model.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.weather.model.Weather;

@Component
public class WeatherSerializer {
	
	private Gson gson;
	
	@Autowired
	public WeatherSerializer(Gson gson) {
		this.gson = gson;
	}
	
	public Weather fromJson(String json) {
		WeatherAdapter weatherApiResponse = gson.fromJson(json, WeatherAdapter.class);
		
		return  weatherApiResponse.getWeather();
	}
}
