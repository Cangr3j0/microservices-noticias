package com.example.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Player;
import com.example.repositorio.FemalePlayerRepository;



@RestController
@RequestMapping("/player/")
public class PlayerController {
	@Autowired
	private FemalePlayerRepository femalePlayerRepository;
	
	@GetMapping("/femaleplayers")
	public List<Player> getNoticias(){
		return (List<Player>)femalePlayerRepository.findAll();
	}
}
