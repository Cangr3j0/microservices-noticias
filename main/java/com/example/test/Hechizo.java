package com.example.test;

public class Hechizo {
private String id;
private String name;
private String description;
private String key;
private Imagen image;

public Hechizo() {

}
public String getId() {
	return id;
}
public String getName() {
	return name;
}
public String getDescription() {
	return description;
}
public String getKey() {
	return key;
}
public void setId(String id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setDescription(String description) {
	this.description = description;
}
public void setKey(String key) {
	this.key = key;
}
public Imagen getImage() {
	return image;
}
public void setImage(Imagen image) {
	this.image = image;
}



}
