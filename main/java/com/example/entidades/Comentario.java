package com.example.entidades;



import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMENT_SEQ")
	@SequenceGenerator(sequenceName = "comentario_seq", allocationSize = 1, name = "COMENT_SEQ")
	private Long id;
	private String contenido;
	
	@Access(AccessType.PROPERTY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="autorId")
	private Usuario autor;
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion=new Date();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

	
}
