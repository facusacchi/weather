package com.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.model.Coordinate;
import com.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	private HttpService httpService;
	private WeatherRepository weatherRepository;
	
	@Autowired
	public WeatherService(HttpService httpService, WeatherRepository weatherRepository) {
		this.httpService = httpService;
		this.weatherRepository = weatherRepository;
	}
	
	public String getWeather(Coordinate coordinate) throws IOException {
		return httpService.getWeather(coordinate);
	}
}
