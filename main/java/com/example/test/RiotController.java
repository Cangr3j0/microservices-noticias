package com.example.test;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/riot")
public class RiotController {
	@Value("${RiotApi}")
	private String riotApi;
	private static final Logger LOGGER=LoggerFactory.getLogger(RiotController.class);
	RestTemplate restTemplate = new RestTemplate();
	public static final String URL_STRING = "https://la2.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

	@GetMapping("/{summonerName}")
	public ResponseEntity getResponse(@PathVariable("summonerName") String summonerName) {
		ResponseEntity<RiotResponse> result = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Header", "value");
		headers.set("Origin", "https://developer.riotgames.com");
		headers.set("X-Riot-Token", riotApi);
		headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		HttpEntity<Void> entity = new HttpEntity(headers);
		try {
			result = restTemplate.exchange(URL_STRING + summonerName, HttpMethod.GET, entity, RiotResponse.class);
			result.status(HttpStatus.OK);

		} catch (HttpClientErrorException e) {
			LOGGER.error("Exception handled: "+e.getMessage());
			if(e.getStatusCode()==HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el invocador");
			}}
		return result;

	}

	@GetMapping("/partidas/{summonerName}")
	public List<String> getPartidas(@PathVariable("summonerName") String summonerName) {
		List<String> listaPartida = new ArrayList<String>();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Header", "value");
		headers.set("Origin", "https://developer.riotgames.com");
		headers.set("X-Riot-Token", riotApi);
		headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		HttpEntity<Void> entity = new HttpEntity(headers);
		ResponseEntity<RiotResponse> result = restTemplate.exchange(URL_STRING + summonerName, HttpMethod.GET, entity,
				RiotResponse.class);
		RiotResponse respuestaResponse = result.getBody();
		String puuidString = respuestaResponse.getPuuid();
		String partidasUrl = "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuidString
				+ "/ids?start=0&count=5";
		ResponseEntity<String[]> resultPartidas = restTemplate.exchange(partidasUrl, HttpMethod.GET, entity,
				String[].class);
		for (String s : resultPartidas.getBody()) {
			listaPartida.add(s);
		}
		return listaPartida;
	}


	@GetMapping("/mapa/{matchid}")
	public List<Participants> getInfoMapa(@PathVariable("matchid")String matchid) {
		String url_matchString="https://americas.api.riotgames.com/lol/match/v5/matches/"+matchid;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Header", "value");
		headers.set("Origin", "https://developer.riotgames.com");
		headers.set("X-Riot-Token", riotApi);
		headers.set("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
		HttpEntity<Void> entity = new HttpEntity(headers);
		ResponseEntity<RiotMapResponse> result = restTemplate.exchange(url_matchString, HttpMethod.GET, entity,
				RiotMapResponse.class);
		return result.getBody().getInfo().getParticipants();
	}
	
	@GetMapping("listapartidas/{summonerName}")
	public List<InfoMatchParticipantes> getPartidasInfo(@PathVariable String summonerName) {
		List<InfoMatchParticipantes> resultado=new ArrayList<InfoMatchParticipantes>();
		List<String> partidas=getPartidas(summonerName);
		for(String s:partidas) {
			InfoMatchParticipantes infoMatchParticipantes=new InfoMatchParticipantes();
			List<Participants> participantes=getInfoMapa(s);
			infoMatchParticipantes.setMatchid(s);
			infoMatchParticipantes.setParticipantes(participantes);
			resultado.add(infoMatchParticipantes);
		}
		return resultado;
	}
	
	@GetMapping("/version")
	public String getVersion() {
		String urlString="https://ddragon.leagueoflegends.com/api/versions.json";
		ResponseEntity<String[]> result = restTemplate.getForEntity(urlString,String[].class);
		return result.getBody()[0];
	}
	@GetMapping("/spells")
	public DataSpells getSpells() {
		String urlString="https://ddragon.leagueoflegends.com/cdn/12.22.1/data/es_AR/summoner.json";
		ResponseEntity<DataSpells> result = restTemplate.getForEntity(urlString,DataSpells.class);
		return result.getBody();
	}
	
	@GetMapping("/spells/{key}")
	public Hechizo getSpellByKey(@PathVariable String key){
		boolean encontre=false;
		Hechizo hechizoReturn=null;
		DataSpells dataSpells=getSpells();
		Map<String, Hechizo> mapeo=dataSpells.getData();
		 Set<Map.Entry<String, Hechizo>> entries=mapeo.entrySet();
		 Iterator<Map.Entry<String, Hechizo>> it=entries.iterator();
	        while (it.hasNext()&&!encontre) {
	            Map.Entry<String, Hechizo> next=it.next();
	            System.out.println("Clave: "+next.getValue().getKey());
	            System.out.println("Clave buscada: "+key);
	            if(key.equals(next.getValue().getKey())) {
	            	encontre=true;
	            	hechizoReturn=next.getValue();
	            }
	            System.out.println(next);
	        }
	    if(hechizoReturn==null) {
	    	throw new NullPointerException("No se encontro el hechizo para esa key");
	    }
		return hechizoReturn;
		
	}
	
	//https://ddragon.leagueoflegends.com/cdn/12.23.1/img/item/1004.png
	@GetMapping("/items")
	public DataItems getItems() {
		String urlString="https://ddragon.leagueoflegends.com/cdn/12.23.1/data/es_AR/item.json";
		ResponseEntity<DataItems> result = restTemplate.getForEntity(urlString,DataItems.class);
		return result.getBody();
	}
	
	@GetMapping("/allitems")
	public List<Item> getAllItems() {
		List<Item> toreturnItems=new ArrayList<Item>();
		DataItems dataItems=getItems();
		Map<String,Item> map=dataItems.getData();
		for (Map.Entry<String, Item> entry : map.entrySet()) {
			toreturnItems.add(entry.getValue());
		}
		if (toreturnItems==null) {
			throw new NullPointerException("No hay items en la lista");
		}
		return toreturnItems;
	}
	
	@GetMapping("/campeones")
	public DataChampions getCampeones() {
		String urlString="https://ddragon.leagueoflegends.com/cdn/12.6.1/data/es_AR/champion.json";
		ResponseEntity<DataChampions> result = restTemplate.getForEntity(urlString,DataChampions.class);
		return result.getBody();
	}
	
	@GetMapping("/allchampions")
	public List<Campeon> getAllChampions() {
		List<Campeon> toreturnChampions=new ArrayList<Campeon>();
		DataChampions dataCampeones=getCampeones();
		Map<String,Campeon> map=dataCampeones.getData();
		for (Map.Entry<String, Campeon> entry : map.entrySet()) {
			toreturnChampions.add(entry.getValue());
		}
		if (toreturnChampions==null) {
			throw new NullPointerException("No hay items en la lista");
		}
		return toreturnChampions;
	}
//	@GetMapping("/download")
//	public void downloadImages() {
//		List<Campeon>campeones=getAllChampions();
//		for(Campeon campeon:campeones) {
//			try{
//				URLConnection urlConnection = new URL("https://cdn.communitydragon.org/12.22.1/champion/"+campeon.getKey()+"/square.png").openConnection();
//				urlConnection.addRequestProperty("User-Agent", 
//						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
//				InputStream in=urlConnection.getInputStream();
//				Files.copy(in, Paths.get("C:/www/lolweb/src/assets/imagenes/campeones/"+campeon.getKey()+".png"));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	}
