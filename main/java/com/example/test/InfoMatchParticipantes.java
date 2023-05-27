package com.example.test;

import java.util.List;

public class InfoMatchParticipantes {
	private String matchid;
	private List<Participants> participantes;
	
	
	public InfoMatchParticipantes() {
	}
	
	public String getMatchid() {
		return matchid;
	}
	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}
	public List<Participants> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Participants> participantes) {
		this.participantes = participantes;
	}
	
	
}
