package com.example.entidades;

import java.util.Comparator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.interfaces.FemalePlayer;

@Entity
@DiscriminatorValue(value = "FEMALE_PLAYER")
public class FemaleTenisPlayer extends Player implements FemalePlayer,Comparable<FemaleTenisPlayer> {

	private String name;
	private int habilityLevel;
	private int reactionTime;

	public FemaleTenisPlayer(String name,int habilityLevel,int reactionTime) {
		this.name=name;
		this.habilityLevel=habilityLevel;
		this.reactionTime=reactionTime;
	}
	public FemaleTenisPlayer(String name) {
		this.name=name;
	}
	public FemaleTenisPlayer() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getHabilityLevel() {
		return habilityLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHabilityLevel(int habilityLevel) {
		this.habilityLevel = habilityLevel;
	}

	@Override
	public int getReactionTime() {
		// TODO Auto-generated method stub
		return reactionTime;
	}

	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}

	@Override
	public int compareTo(FemaleTenisPlayer o) {
		return Comparator.comparing(FemaleTenisPlayer::getHabilityLevel)
				.thenComparing(FemaleTenisPlayer::getReactionTime)
				.compare(this, o);
	}

}
