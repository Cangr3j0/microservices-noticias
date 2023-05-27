package com.example.test;

import java.util.Map;

public class DataChampions {
	private String type;
	private String version;
	private Map<String, Campeon> data;
	public String getType() {
		return type;
	}
	public String getVersion() {
		return version;
	}
	public Map<String, Campeon> getData() {
		return data;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setData(Map<String, Campeon> data) {
		this.data = data;
	}
	
	
}
