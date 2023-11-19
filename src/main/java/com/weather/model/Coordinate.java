package com.weather.model;

import java.util.Arrays;

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

	public boolean isValid() {
		return Arrays.asList(latitude, length).stream().allMatch(field -> !isNullOrEmpty(field));
	}
	
	private boolean isNullOrEmpty(String value) {
		return value == null || value.isBlank();
	}
}
