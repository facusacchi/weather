package com.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weather.exception.exceptions.HttpClientException;
import com.weather.model.Coordinate;
import com.weather.model.builder.UrlBuilder;
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
		StringBuilder response = new StringBuilder();
		String url = this.getUrl(coordinate);
		try {
			URL urlRequest = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlRequest.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			
			int responseCode = connection.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
			
		} catch(IOException ex) {
			String erroMessage = environment.getProperty("exception-message.http-client");
			throw new HttpClientException(erroMessage);
		}
		return response.toString();
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
}
