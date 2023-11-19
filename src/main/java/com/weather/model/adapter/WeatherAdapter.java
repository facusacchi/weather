package com.weather.model.adapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.weather.model.TimeInterval;
import com.weather.model.Weather;

import lombok.Data;

@Data
public class WeatherAdapter {
	private Summary Summary;
	private List<Interval> Summaries = new ArrayList<>();
	private String Link;
	private String MobileLink;
	
	public Weather getWeather() {
		return Weather.builder()
				.description(Summary.getPhrase())
				.timeSlots(this.getTimeSlots())
				.link(Link)
				.mobileLink(MobileLink)
				.dateTime(LocalDateTime.now())
				.build();
	}
	
	private List<TimeInterval> getTimeSlots() {
		return Summaries.stream()
				.map(Interval::getTimeInterval)
				.collect(Collectors.toList());
	}
}
