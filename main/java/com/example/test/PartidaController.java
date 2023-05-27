package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Partida;
import com.example.repositorio.RepositorioPartida;

@RestController
@RequestMapping("/partida")
public class PartidaController {

	@Autowired
	private RepositorioPartida repositorioPartida;
	
	@GetMapping("/crear")
	public void crearPartida() {
		try {
			Partida partida=new Partida();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
