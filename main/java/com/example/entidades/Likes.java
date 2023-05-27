package com.example.entidades;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
	@SequenceGenerator(sequenceName = "like_seq", allocationSize = 1, name = "LIKE_SEQ")
	private Long id;
	
	@Access(AccessType.PROPERTY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="autorId")
	private Usuario autor;
	
	 @Temporal(TemporalType.DATE)
	private Date fecha_creacion=new Date();

	public Long getId() {
		return id;
	}

	public Usuario getAutor() {
		return autor;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	 
	 
	
	
	
}
