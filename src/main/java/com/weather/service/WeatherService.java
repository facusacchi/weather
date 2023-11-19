package com.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weather.exception.exceptions.CoordinateException;
import com.weather.model.Coordinate;
import com.weather.model.Weather;
import com.weather.model.adapter.WeatherSerializer;
import com.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	private HttpWeatherService httpService;
	private WeatherRepository weatherRepository;
	private Environment environment;
	private WeatherSerializer serializer;
	
	@Autowired
	public WeatherService(HttpWeatherService httpService, WeatherRepository weatherRepository, Environment environment,
			WeatherSerializer serializer) {
		this.httpService = httpService;
		this.weatherRepository = weatherRepository;
		this.environment = environment;
		this.serializer = serializer;
	}
	
	public Weather getActual(Coordinate coordinate) {
		this.validateCoordinate(coordinate);
		
		String response = httpService.getWeather(coordinate);
		
		return serializer.fromJson(response);
	}
	
	private void validateCoordinate(Coordinate coordinate) {
		if(!coordinate.isValid()) {
			String erroMessage = environment.getProperty("exception-message.coorinate");
			throw new CoordinateException(erroMessage);
		}
	}
}
