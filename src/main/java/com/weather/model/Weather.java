package com.weather.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Weather {
	
	private static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TimeInterval> timeSlots = new ArrayList<>();
	private String link;
	private String mobileLink;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_TIME_PATTERN)
	private LocalDateTime dateTime =  LocalDateTime.now();
}
