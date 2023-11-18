package com.weather.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.Coordinate;
import com.weather.service.WeatherService;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

	private WeatherService weatherService;
	
    @Autowired
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
    
    @GetMapping()
    public ResponseEntity<String> getCoordinate(Coordinate coordinate) {
    	
    	String weatherText = new StringBuilder("Latitud: ")
    								.append(coordinate.getLatitude())
    								.append(" | ")
    								.append("Longitud: ")
    								.append(coordinate.getLength())
    								.toString();

		return ResponseEntity.ok(weatherText);
	}
    
    @GetMapping("/status")
    public ResponseEntity<String> getWeather(Coordinate coordinate) throws IOException {
    	
    	String weather = weatherService.getWeather(coordinate);
    	
		return ResponseEntity.ok(weather);
	}
    
    
//    @GetMapping
//	public ResponseEntity<List> getAll() {
//		List list = this.service.findAll();
//
//		return ResponseEntity.ok(list);
//	}
//    
//    @GetMapping("/{id}")
//    public ResponseEntity findById(@PathVariable Long id) {
//		T entity = this.service.findById(id);
//		
//		return ResponseEntity.ok(entity);
//	}
//    
//    @PostMapping
//    public ResponseEntity<String> create(@RequestBody T entity) {
//    	this.service.save(entity);
//        
//        return ResponseEntity.ok("Created");
//    }
//
//    @PutMapping
//    public ResponseEntity<String> update(@RequestBody T entity) {
//    	this.service.update(entity);
//
//		return ResponseEntity.ok("Updated");
//    }
//
//    @DeleteMapping("/{id}")
//	public ResponseEntity<String> delete(@PathVariable Long id) {
//		this.service.deleteById(id);
//
//		return ResponseEntity.ok("Deleted");
//	}
}
