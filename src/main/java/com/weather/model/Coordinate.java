package com.weather.model;

import lombok.Data;

@Data
public class Coordinate {
	private String latitude;
	private String length;
	
	@Override
	public String toString() {
		return new StringBuilder(latitude)
				.append(",")
				.append(length)
				.toString();
	}
}
