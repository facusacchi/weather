package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.Coordinate;
import com.weather.model.Weather;
import com.weather.service.WeatherService;

@RestController
@RequestMapping("/api/v1/weather")
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
