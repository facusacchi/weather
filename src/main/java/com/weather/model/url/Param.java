package com.weather.model.url;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Param {
	
	private String key;
	private String value;
	
	public boolean isEquals(Param param) {
		return this.key.equals(param.getKey());
	}
	
	@Override
	public String toString() {
		return new StringBuilder(key)
				.append("=")
				.append(value)
				.toString();
	}
}
