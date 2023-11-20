package com.weather.unitTests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import com.weather.exception.CoordinateException;
import com.weather.exception.ResourceNotFoundException;
import com.weather.model.Coordinate;
import com.weather.model.Weather;
import com.weather.model.adapter.WeatherSerializer;
import com.weather.repository.WeatherRepository;
import com.weather.service.HttpWeatherService;
import com.weather.service.WeatherService;

class WeatherServiceTest {
	
	private static final String ERROR_MESSAGE_COORDINATE =  "Error en el envío de coordenadas. Verificar Latitud y longitud.";
	private static final String ERROR_MESSAGE_NOT_FOUND = "Error al consumir api de terceros.";
	
	private static HttpWeatherService httpService;
	private static WeatherRepository weatherRepository;
	private static Environment environment;
	private static WeatherSerializer serializer;
	private static WeatherService service;
	
	@BeforeAll
	static void initData() {
		httpService = mock(HttpWeatherService.class);
		weatherRepository = mock(WeatherRepository.class);
		environment = mock(Environment.class);
		serializer = mock(WeatherSerializer.class);
		service = WeatherService.builder()
					.httpService(httpService)
					.weatherRepository(weatherRepository)
					.environment(environment)
					.serializer(serializer)
					.build();
		
		when(environment.getProperty("exception-message.coordinate")).thenReturn(ERROR_MESSAGE_COORDINATE);
		when(environment.getProperty("exception-message.not-found")).thenReturn(ERROR_MESSAGE_NOT_FOUND);
	}
	
	@Test
	@DisplayName("Cuando consulto el servicio del clima obtengo una estructura de datos que representa el clima, enviando unicamente las coordenadas")
	void getActualTest() {
		Coordinate coordinate = Coordinate.builder()
								.latitude("123")
								.length("456")
								.build();
		
		when(httpService.getWeather(any(Coordinate.class))).thenReturn(new String());
		when(serializer.fromJson(anyString())).thenReturn(new Weather());
		
		Weather weather = service.getActual(coordinate); 
		
		assertNotNull(weather);
	}
	
	@Test
	@DisplayName("Cuando consulto el servicio del clima con latitud vacía obtengo un error")
	void getActualEmptyLatitudeTest() {
		Coordinate coordinate = Coordinate.builder()
					.latitude("")
					.length("123")
					.build();
		
		when(httpService.getWeather(any(Coordinate.class))).thenReturn(new String());
		when(serializer.fromJson(anyString())).thenReturn(new Weather());
		
		CoordinateException ex = assertThrows(CoordinateException.class, () -> service.getActual(coordinate));
		assertEquals(ERROR_MESSAGE_COORDINATE, ex.getMessage());
	}
	
	@Test
	@DisplayName("Cuando consulto el servicio del clima con latitud nula obtengo un error")
	void getActualNullLatitudeTest() {
		Coordinate coordinate = Coordinate.builder()
					.latitude(null)
					.length("123")
					.build();
		
		when(httpService.getWeather(any(Coordinate.class))).thenReturn(new String());
		when(serializer.fromJson(anyString())).thenReturn(new Weather());
		
		CoordinateException ex = assertThrows(CoordinateException.class, () -> service.getActual(coordinate));
		assertEquals(ERROR_MESSAGE_COORDINATE, ex.getMessage());
	}
	
	@Test
	@DisplayName("Cuando consulto el servicio del clima con longitud vacía obtengo un error")
	void getActualEmptyLengthTest() {
		Coordinate coordinate = Coordinate.builder()
					.latitude("123")
					.length("")
					.build();
		
		when(httpService.getWeather(any(Coordinate.class))).thenReturn(new String());
		when(serializer.fromJson(anyString())).thenReturn(new Weather());
		
		CoordinateException ex = assertThrows(CoordinateException.class, () -> service.getActual(coordinate));
		assertEquals(ERROR_MESSAGE_COORDINATE, ex.getMessage());
	}
	
	@Test
	@DisplayName("Cuando consulto el servicio del clima con longitud nula obtengo un error")
	void getActualNullLengthTest() {
		Coordinate coordinate = Coordinate.builder()
					.latitude("123")
					.length(null)
					.build();
		
		when(httpService.getWeather(any(Coordinate.class))).thenReturn(new String());
		when(serializer.fromJson(anyString())).thenReturn(new Weather());
		
		CoordinateException ex = assertThrows(CoordinateException.class, () -> service.getActual(coordinate));
		assertEquals(ERROR_MESSAGE_COORDINATE, ex.getMessage());
	}
	
	@Test
	@DisplayName("Cuando busco un pronostico específico por id, obtengo dicha recurso correspondiente")
	void findByIdTest() {
		when(weatherRepository.findById(any(Long.class))).thenReturn(Optional.of(new Weather()));
		
		Weather weather = service.findById(1234L);
		
		assertNotNull(weather);
	}
	
	@Test
	@DisplayName("Cuando busco un pronostico específico por id y este no es encontrado obtengo un error")
	void findByIdNotFoundTest() {
		when(weatherRepository.findById(any(Long.class))).thenReturn(Optional.empty());
		
		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> service.findById(1234L));
		assertEquals(ERROR_MESSAGE_NOT_FOUND, ex.getMessage());
	}
}
