package com.example.entidades;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Torneo {
//	Torneos lúdicos: Diversión, objetivo recreativo.
//	Torneos competitivos: Objetivo proclamar un campeón, objetivo de ganar. ...
//	Torneos educativos: Objetivo poner en práctica algo aprendido, para mejorar un aprendizaje.
//  Formato de torneo: Eliminacion simple, doble, Liga
//  Fecha del torneo
//	Reglas del torneo
//	Premios
//	Costo de inscripcion podria ser por 2puntos, y que cada dia te de 1 punto
//	Publicidad o promocion del torneo?
//	Autor o creador del torneo
//	Puntos
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TORNEO_SEQ")
	@SequenceGenerator(sequenceName = "torneo_seq", allocationSize = 1, name = "TORNEO_SEQ")
	private Long id;
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="autorId")
	private Usuario autor;
	
	@Column(length=50)
	private String titulo;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion=new Date();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "torneo_id")
	private List<Usuario>participantes=new ArrayList<Usuario>();
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ganadorId")
	private Usuario ganador;
	
	@Column(length=1000)
	private String reglasyotros;
	
	private Long puntosOtorgados;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "torneo_id")
	private List<Partida> partidas=new ArrayList<Partida>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "torneo_id")
	private List<Team> equiposParticipantes;
	
	private boolean brackets_creados;
	
	@OneToOne
	private Partida raiz;
	
	public Long getId() {
		return id;
	}

	public Usuario getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public Usuario getGanador() {
		return ganador;
	}

	public String getReglasyotros() {
		return reglasyotros;
	}

	public Long getPuntosOtorgados() {
		return puntosOtorgados;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}

	public void setGanador(Usuario ganador) {
		this.ganador = ganador;
	}

	public void setReglasyotros(String reglasyotros) {
		this.reglasyotros = reglasyotros;
	}

	public void setPuntosOtorgados(Long puntosOtorgados) {
		this.puntosOtorgados = puntosOtorgados;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public List<Team> getEquiposParticipantes() {
		return equiposParticipantes;
	}

	public void setEquiposParticipantes(List<Team> equiposParticipantes) {
		this.equiposParticipantes = equiposParticipantes;
	}

	public boolean isBrackets_creados() {
		return brackets_creados;
	}

	public void setBrackets_creados(boolean brackets_creados) {
		this.brackets_creados = brackets_creados;
	}

	public Partida getRaiz() {
		return raiz;
	}

	public void setRaiz(Partida raiz) {
		this.raiz = raiz;
	}
	
	

	
}
