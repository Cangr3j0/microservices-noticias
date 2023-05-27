package com.example.test;

import java.util.Map;

public class DataItems {
private String type;
private String version;
private Map<String, Item> data;
public String getType() {
	return type;
}
public String getVersion() {
	return version;
}
public Map<String, Item> getData() {
	return data;
}
public void setType(String type) {
	this.type = type;
}
public void setVersion(String version) {
	this.version = version;
}
public void setData(Map<String, Item> data) {
	this.data = data;
}


}
