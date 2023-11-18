package com.weather.model.adapter;

import com.weather.model.Weather;

public class WeatherAdapter {
	
	private Weather weather;
	private WeatherApiResponse weatherApiResponse;
	private String json;
	
	public WeatherAdapter(String json) {
		this.weather = new Weather();
		this.json = json;
	}
	
//	public static Weather getWeather() {
//		
//	}
	
}
