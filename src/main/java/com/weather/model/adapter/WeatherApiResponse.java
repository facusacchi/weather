package com.weather.model.adapter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class WeatherApiResponse {
	private Summary Summary;
	private List<Interval> Summaries = new ArrayList<>();
	private String Link;
	private String MobileLink;
}
