package com.example.entidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NamedEntityGraph(name = "Noticia.comentarios",
attributeNodes = {
		@NamedAttributeNode("comentarios"),
		@NamedAttributeNode("autor")}
)
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOT_SEQ")
	@SequenceGenerator(sequenceName = "noticia_seq", allocationSize = 1, name = "NOT_SEQ")
	private Long id;
	private String titulo;
	
	@Lob
	private String contenido;
	private String contenido_corto;
	
	 @Temporal(TemporalType.DATE)
	private Date fecha_creacion=new Date();
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="autorId")
	private Usuario autor;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "Noticia_id")
	private List<Comentario> comentarios=new ArrayList<Comentario>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "Noticia_id")
	private List<Imagen> imagenes=new ArrayList<Imagen>();
	
	
	private Long cantidadComentarios=(long) comentarios.size();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "noticia_likes_id")
	private List<Likes> likes=new ArrayList<Likes>();
	
	
	public Noticia() {
		this.cantidadComentarios=(long)comentarios.size();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getContenido_corto() {
		return contenido_corto;
	}
	public void setContenido_corto(String contenido_corto) {
		this.contenido_corto = contenido_corto;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public Long getCantidadComentarios() {
		return cantidadComentarios;
	}
	public void setCantidadComentarios(Long cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public List<Imagen> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}	
	
	
}
