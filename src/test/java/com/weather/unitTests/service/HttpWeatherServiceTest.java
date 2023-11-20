package com.weather.unitTests.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.core.env.Environment;

import com.weather.exception.HttpClientException;
import com.weather.model.Coordinate;
import com.weather.model.connection.Connection;
import com.weather.model.factory.ConnectionFactory;
import com.weather.service.HttpWeatherService;

class HttpWeatherServiceTest {
	
	private static String HTTP_RESPONSE = "{\r\n"
			+ "        \"Phrase\": \"Lluvia que finalizará en 13 min\",\r\n"
			+ "        \"Type\": \"RAIN\",\r\n"
			+ "        \"TypeId\": 1\r\n"
			+ "    }";

	private static Environment environment;
	private static HttpWeatherService service;
	private static Connection connection;
	private static Coordinate coordinate;
	
	@BeforeAll
	static void initData() {
		environment = mock(Environment.class);
		service = new HttpWeatherService(environment);
		connection = mock(Connection.class);
		coordinate = Coordinate.builder()
				.latitude("123")
				.length("-456")
				.build();
		
	}
	
	@BeforeEach
	void doMock() throws IOException {
		doNothing().when(connection).addHeader(anyString(), anyString());
		doNothing().when(connection).execute();
		
		when(connection.getResponse()).thenReturn(HTTP_RESPONSE);
		when(environment.getProperty(anyString())).thenReturn("any string");
	}
	
	@AfterEach
	void tearDown() {
		reset(connection);
	}
	
	@Test
	@DisplayName("test")
	void getWeatherTest() throws IOException {
		try(MockedStatic<ConnectionFactory> connectionFactory = mockStatic(ConnectionFactory.class)) {
			
			connectionFactory.when(() -> ConnectionFactory.instance(anyString(), anyString())).thenReturn(connection);
			when(connection.isSuccess()).thenReturn(true);
			
			String response = service.getWeather(coordinate);
			
			assertEquals(HTTP_RESPONSE, response);
		}
	}
	
	@Test
	@DisplayName("test2")
	void getWeatherErrorTest() throws IOException {
		try(MockedStatic<ConnectionFactory> connectionFactory = mockStatic(ConnectionFactory.class)) {
			
			connectionFactory.when(() -> ConnectionFactory.instance(anyString(), anyString())).thenReturn(connection);
			when(connection.isSuccess()).thenReturn(false);
			
			assertThrows(HttpClientException.class, () -> service.getWeather(coordinate));
		}
	}
}
