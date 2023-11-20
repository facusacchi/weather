package com.weather.model.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import lombok.Data;

@Data
public class Connection {
	private HttpURLConnection httpURLConnection;
	private int responseCode;

	public void addHeader(String key, String value) {
		httpURLConnection.setRequestProperty(key, value);
	}
	
	public void execute() throws IOException {
		responseCode = httpURLConnection.getResponseCode();
	}
	
	public boolean isSuccess() {
		return responseCode == HttpURLConnection.HTTP_OK;
	}
	
	public String getResponse() throws IOException {
		StringBuilder response = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		String inputLine;
		
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		
		return response.toString();
	}
}
