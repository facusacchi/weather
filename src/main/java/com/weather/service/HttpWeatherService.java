package com.weather.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weather.exception.HttpClientException;
import com.weather.model.Coordinate;
import com.weather.model.builder.UrlBuilder;
import com.weather.model.connection.Connection;
import com.weather.model.factory.ConnectionFactory;
import com.weather.model.url.Param;

@Service
public class HttpWeatherService {
	
	private static String HTTP_ENV_KEY = "http-weather-client.";
	private Environment environment;
	
	@Autowired
	public HttpWeatherService(Environment environment) {
		this.environment = environment;
	}
	
	public String getWeather(Coordinate coordinate) {
		String response = null;
		Connection connection = null;
		String url = this.getUrl(coordinate);
		
		try {
			connection = ConnectionFactory.instance(url, "GET");
			connection.addHeader("Content-Type", "application/json");
			connection.execute();
			
			response = connection.getResponse();
			
		} catch(IOException ex) {
			doError();
		}
		
		validateConnection(connection);
		
		return response;
	}
	
	private String getUrl(Coordinate coordinate) {
		UrlBuilder urlBuilder = new UrlBuilder()
				.withDomain(environment.getProperty(HTTP_ENV_KEY + "domain"))
				.withPath(environment.getProperty(HTTP_ENV_KEY + "path"));
		
		Arrays.asList(new Param("apikey", environment.getProperty(HTTP_ENV_KEY + "api-key")),
				new Param("language", environment.getProperty(HTTP_ENV_KEY + "lenguague")),
				new Param("q", coordinate.toString())
				).forEach(param -> urlBuilder.withParam(param));
		
		return urlBuilder.build();
	}
	
	private void validateConnection(Connection connection) {
		if(!connection.isSuccess()) {
			doError();
		}
	}
	
	private void doError() {
		String erroMessage = environment.getProperty("exception-message.http-client");
		throw new HttpClientException(erroMessage);
	}
}
