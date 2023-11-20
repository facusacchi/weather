package com.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.Coordinate;
import com.weather.model.Weather;
import com.weather.service.WeatherService;

@RestController
@RequestMapping("/api/v1/weather")
@CrossOrigin("*")
public class WeatherController {

	private WeatherService weatherService;
	
    @Autowired
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
    
    @GetMapping("/actual")
    public ResponseEntity<Weather> getActual(Coordinate coordinate) {
    	Weather actual = weatherService.getActual(coordinate);
    	
		return ResponseEntity.ok(actual);
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<Weather> findById(@PathVariable Long id) {
    	Weather weather = this.weatherService.findById(id);
    	
    	return ResponseEntity.ok(weather);
    }
    
    @GetMapping("/lastForecasts")
    public ResponseEntity<List<Weather>> getLastForecasts(int limit) {
    	List<Weather> lastForecasts = weatherService.getLastForecasts(limit);
    	
    	return ResponseEntity.ok(lastForecasts);
    }
    
    @DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		this.weatherService.deleteById(id);

		return ResponseEntity.ok("OK");
	}
}
