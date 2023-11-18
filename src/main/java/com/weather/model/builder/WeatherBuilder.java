package com.weather.model.builder;

import java.util.List;

import com.weather.model.TimeInterval;
import com.weather.model.Weather;

public class WeatherBuilder {
	
	private Weather weather;
	
	public WeatherBuilder() {
		this.weather = new Weather();
	}
	
	public WeatherBuilder withDescription(String description) {
		this.weather.setDescription(description);
		return this;
	}
	
	public WeatherBuilder withTimeSlots(List<TimeInterval> timeSlots) {
		this.weather.setTimeSlots(timeSlots);
		return this;
	}
	
	public WeatherBuilder withLink(String link) {
		this.weather.setLink(link);;
		return this;
	}
	
	public WeatherBuilder withMobileLink(String link) {
		this.weather.setMobileLink(link);;
		return this;
	}
	
	public Weather build() {
		return this.weather;
	}
	
}
