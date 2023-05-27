package com.example.test;

public class RiotResponse {
	private String id;
	private String accountId;
	private String puuid;
	private String name;
	private Integer profileIconId;
	private Integer summonerLevel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(Integer profileIconId) {
		this.profileIconId = profileIconId;
	}
	public Integer getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(Integer summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	
	
}
