package com.weather.model.url;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Url {
	
	private String domain;
	private String path;
	private List<Param> params = new ArrayList<>();
	
	@Override
	public String toString() {
		return new StringBuilder(domain)
				.append(path)
				.append(getQueryParam())
				.toString();
	}
	
	public void addParam(Param param) {
		params.add(param);
	}
	
	private String getQueryParam() {
		StringBuilder queryParam = new StringBuilder("?");
		
		Param firstParam = params.get(0);
		
		params.forEach(param -> {
			if(!param.isEquals(firstParam)) {
				queryParam.append("&");
			}
			queryParam.append(param.toString());
		});
		
		return queryParam.toString();
	}
}
