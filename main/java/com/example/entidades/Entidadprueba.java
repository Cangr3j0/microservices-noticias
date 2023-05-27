package com.example.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Entidadprueba {
	
@Id
@GeneratedValue	
public Long id;
public String mensajeString;

public String getMensajeString() {
	return mensajeString;
}
public void setMensajeString(String mensajeString) {
	this.mensajeString = mensajeString;
}

}
