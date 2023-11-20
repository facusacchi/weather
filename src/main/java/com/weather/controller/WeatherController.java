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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/weather")
@CrossOrigin("*")
@Api(tags = "Api del pronóstico del Clima")
public class WeatherController {

	private WeatherService weatherService;
	
    @Autowired
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
    
    @ApiOperation(value = "Obtener clima actual", notes = "Obtiene el clima actual para una determinada coordenada introducida por query param")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "latitude", value = "Latitud de la coordenada", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "length", value = "Longitud de la coordenada", dataType = "String", paramType = "query")
    })
    @GetMapping("/actual")
    public ResponseEntity<Weather> getActual(Coordinate coordinate) {
    	Weather actual = weatherService.getActual(coordinate);
    	
		return ResponseEntity.ok(actual);
	}
    
    @ApiOperation(value = "Obtener clima por id", notes = "Obtiene un pronóstico del clima que ya ha sido registrado en el sistema")
    @GetMapping("/{id}")
    public ResponseEntity<Weather> findById(
    		@PathVariable
    		@ApiParam(value = "Id del pronostico a buscar", required = true)
    		Long id) {
    	Weather weather = this.weatherService.findById(id);
    	
    	return ResponseEntity.ok(weather);
    }
    
    @ApiOperation(value = "Obtener últimos pronósticos", notes = "Obtiene los últimos pronósticos registrados en el sistema. Máximo permitido: 100")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "limit", value = "Cantidad de pronosticos a obtener", dataType = "int", paramType = "query"),
    })
    @GetMapping("/lastForecasts")
    public ResponseEntity<List<Weather>> getLastForecasts(int limit) {
    	
    	List<Weather> lastForecasts = weatherService.getLastForecasts(limit);
    	
    	return ResponseEntity.ok(lastForecasts);
    }
    
    @ApiOperation(value = "Eliminar clima por id", notes = "Elimina un pronóstico del clima registrado en el sistema")
    @DeleteMapping("/{id}")
	public ResponseEntity<String> delete(
			@PathVariable
			@ApiParam(value = "Id del pronostico a eliminar", required = true)
			Long id) {
		this.weatherService.deleteById(id);

		return ResponseEntity.ok("OK");
	}
}
