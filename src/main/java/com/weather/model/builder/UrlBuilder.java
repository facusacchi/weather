package com.weather.model.builder;

import com.weather.model.url.Param;
import com.weather.model.url.Url;

public class UrlBuilder {
	
	private Url url;
	
	public UrlBuilder() {
		this.url = new Url();
	}
	
	public UrlBuilder withDomain(String domain) {
		this.url.setDomain(domain);
		return this;
	}
	
	public UrlBuilder withPath(String path) {
		this.url.setPath(path);
		return this;
	}
	
	public UrlBuilder withParam(Param param) {
		this.url.addParam(param);
		return this;
	}
	
	public String build() {
		return this.url.toString();
	}
}
