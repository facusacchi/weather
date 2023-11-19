package com.weather.model.adapter;

import com.weather.model.TimeInterval;

import lombok.Data;

@Data
public class Interval {
	private Long StartMinute;
	private Long EndMinute;
	private Long CountMinute;
	private String MinuteText;
	private String Type;
	private Long TypeId;
	
	public TimeInterval getTimeInterval() {
		return TimeInterval.builder()
				.startMinute(StartMinute)
				.endMinute(EndMinute)
				.totalMinutes(CountMinute)
				.build();
	}
}
