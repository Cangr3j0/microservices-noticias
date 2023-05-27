package com.example.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "IMAGEN_SEQ")
	@SequenceGenerator(sequenceName = "imagen_seq", allocationSize = 1, name = "IMAGEN_SEQ")
	private Long id;
	
	@Lob
	private byte[] data;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String uuidString;
	
	private String nombre;
	
	private String tipo;

	public Long getId() {
		return id;
	}

	public byte[] getData() {
		return data;
	}

	public String getUuidString() {
		return uuidString;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setUuidString(String uuidString) {
		this.uuidString = uuidString;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
