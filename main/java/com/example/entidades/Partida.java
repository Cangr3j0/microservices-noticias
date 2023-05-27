package com.example.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;

@Entity
public class Partida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTID_SEQ")
	@SequenceGenerator(sequenceName = "partida_seq", allocationSize = 1, name = "PARTID_SEQ")
	private Long id;
	
//	@Size(min=2,max = 2)
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
//	@JoinColumn(name = "partida_id")
//	private List<Team> equipos=new ArrayList<Team>();

	 @OneToOne(cascade = CascadeType.ALL)
	private Team ganador;
	
	 @OneToOne(cascade = CascadeType.ALL)
	private Team perdedor;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	private Team equipo1;
	
	 @OneToOne(cascade = CascadeType.ALL)
	private Team equipo2;
	 
	 @Temporal(TemporalType.DATE)
	private Date fechaInicio=new Date();
	 
	 @Temporal(TemporalType.DATE)
	private Date fechaFinalizado=new Date(); 
	 
	private String partida_lol_id; 
	
	private Long ronda;
	
	@ManyToOne
	private Partida partida1;
	
	@ManyToOne
	private Partida partida2;
	
	private Long posx;
	
	private Long posy;
	
	private String path;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getGanador() {
		return ganador;
	}

	public Team getPerdedor() {
		return perdedor;
	}

//	public void setEquipos(List<Team> equipos) {
//		this.equipos = equipos;
//	}

	public void setGanador(Team ganador) {
		this.ganador = ganador;
	}

	public void setPerdedor(Team perdedor) {
		this.perdedor = perdedor;
	}

//	public List<Team> getEquipos() {
//		return equipos;
//	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFinalizado() {
		return fechaFinalizado;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFinalizado(Date fechaFinalizado) {
		this.fechaFinalizado = fechaFinalizado;
	}

	public String getPartida_lol_id() {
		return partida_lol_id;
	}

	public void setPartida_lol_id(String partida_lol_id) {
		this.partida_lol_id = partida_lol_id;
	}

	public Long getRonda() {
		return ronda;
	}

	public void setRonda(Long ronda) {
		this.ronda = ronda;
	}

	public Team getEquipo1() {
		return equipo1;
	}

	public Team getEquipo2() {
		return equipo2;
	}

	public void setEquipo1(Team equipo1) {
		this.equipo1 = equipo1;
	}

	public void setEquipo2(Team equipo2) {
		this.equipo2 = equipo2;
	}

	public Partida getPartida1() {
		return partida1;
	}

	public Partida getPartida2() {
		return partida2;
	}

	public void setPartida1(Partida partida1) {
		this.partida1 = partida1;
	}

	public void setPartida2(Partida partida2) {
		this.partida2 = partida2;
	}

	public Long getPosx() {
		return posx;
	}

	public Long getPosy() {
		return posy;
	}

	public void setPosx(Long posx) {
		this.posx = posx;
	}

	public void setPosy(Long posy) {
		this.posy = posy;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
