package com.example.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.interfaces.MalePlayer;


@Entity
@DiscriminatorValue(value = "MALE_PLAYER")
public class MaleTenisPlayer extends Player implements MalePlayer{

	private String name;
	private int habilityLevel;
	private int strength;
	private int velocity;

	
	public MaleTenisPlayer(String name) {
		this.name = name;
	}
	public MaleTenisPlayer() {
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
	public int getStrength() {
		return strength;
	}

	@Override
	public int getVelocity() {
		return velocity;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

}
