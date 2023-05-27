package com.example.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_SEQ")
	@SequenceGenerator(sequenceName = "team_seq", allocationSize = 1, name = "TEAM_SEQ")
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "miTeam")
	@JsonManagedReference
	private List<Usuario>jugadores=new ArrayList<Usuario>();
	
	private String nombreEquipo;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario lider;
	
	@OneToOne
	private Imagen logoTeam;
	
	  @OneToOne(mappedBy = "ganador")
	  private Partida partidaGanada;
	  
	  @OneToOne(mappedBy = "perdedor")
	  private Partida partidaPerdida;

	public Long getId() {
		return id;
	}

	public List<Usuario> getJugadores() {
		return jugadores;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public Imagen getLogoTeam() {
		return logoTeam;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJugadores(List<Usuario> jugadores) {
		this.jugadores = jugadores;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public void setLogoTeam(Imagen logoTeam) {
		this.logoTeam = logoTeam;
	}

	public Partida getPartidaGanada() {
		return partidaGanada;
	}

	public Partida getPartidaPerdida() {
		return partidaPerdida;
	}

	public void setPartidaGanada(Partida partidaGanada) {
		this.partidaGanada = partidaGanada;
	}

	public void setPartidaPerdida(Partida partidaPerdida) {
		this.partidaPerdida = partidaPerdida;
	}

	public Usuario getLider() {
		return lider;
	}

	public void setLider(Usuario lider) {
		this.lider = lider;
	}
	
	
}
