package com.weather.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weather.exception.CoordinateException;
import com.weather.exception.ResourceNotFoundException;
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
		
		String httpResponse = httpService.getWeather(coordinate);
		Weather weather = serializer.fromJson(httpResponse);
		
		weatherRepository.save(weather);
		
		return weather;
	}
	
	private void validateCoordinate(Coordinate coordinate) {
		if(!coordinate.isValid()) {
			String erroMessage = environment.getProperty("exception-message.coorinate");
			throw new CoordinateException(erroMessage);
		}
	}

	public Weather findById(Long id) {
		Optional<Weather> weather = weatherRepository.findById(id);
		
		if(!weather.isPresent()) {
			String erroMessage = environment.getProperty("exception-message.not-found");
			throw new ResourceNotFoundException(erroMessage);
		}
		
		return weather.get();
	}
	
	public void deleteById(Long id) {
		weatherRepository.deleteById(id);
	}

	public List<Weather> getLastForecasts(int limit) {
		return weatherRepository.findTopNByOrderByDateTimeDesc(limit);
	}
}
