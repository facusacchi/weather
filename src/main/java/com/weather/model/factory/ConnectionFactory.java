package com.weather.model.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.weather.model.connection.Connection;

public class ConnectionFactory {
	
	private ConnectionFactory() { }
	
	public static Connection instance(String url, String requestMethod) throws IOException {
		URL urlRequest = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlRequest.openConnection();
		
		httpURLConnection.setRequestMethod(requestMethod);
		
		Connection connection = new Connection();
		connection.setHttpURLConnection(httpURLConnection);
		
		return connection;
	}
}
