package com.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.weather.model.Coordinate;

@Service
public class HttpService {
	
	private Environment environment;
	
	@Autowired
	public HttpService(Environment environment) {
		this.environment = environment;
	}
	
	public String getWeather(Coordinate coordinate) throws IOException {
		
		StringBuilder response = new StringBuilder();
		String url = this.getUrl(coordinate);
		
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
        
		return response.toString();
	}
	
	private String getUrl(Coordinate coordinate) {
		return new StringBuilder("http://dataservice.accuweather.com/forecasts/v1/minute")
					.append("?apikey=7DsYp2QbxcwAvItRPmbQfqvKWPaifMm0")
					.append("&language=es-ar")
					.append("&q=-34.61315,-58.37723")
					.toString();
	}
}
