package com.weather.model.builder;

import com.weather.model.Coordinate;

public class CoordinateBuilder {

	private Coordinate coordinate;
	
	public CoordinateBuilder() {
		this.coordinate = new Coordinate();
	}
	
	public CoordinateBuilder withLatitude(String latitude) {
		coordinate.setLatitude(latitude);
		return this;
	}
	
	public CoordinateBuilder withLength(String length) {
		coordinate.setLength(length);
		return this;
	}
	
	public Coordinate build() {
		return this.coordinate;
	}
}
