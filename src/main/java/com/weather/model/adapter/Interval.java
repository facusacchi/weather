package com.weather.model.adapter;

import lombok.Data;

@Data
public class Interval {
	private Long StartMinute;
	private Long EndMinute;
	private Long CountMinute;
	private String MinuteText;
	private String Type;
	private Long TypeId;
}
