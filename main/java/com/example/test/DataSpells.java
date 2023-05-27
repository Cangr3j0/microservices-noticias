package com.example.test;

import java.util.Map;

public class DataSpells {
	private String type;
	private String version;
private Map<String,Hechizo> data;



public String getType() {
	return type;
}

public String getVersion() {
	return version;
}

public void setType(String type) {
	this.type = type;
}

public void setVersion(String version) {
	this.version = version;
}

public Map<String, Hechizo> getData() {
	return data;
}

public void setData(Map<String, Hechizo> data) {
	this.data = data;
}

}
