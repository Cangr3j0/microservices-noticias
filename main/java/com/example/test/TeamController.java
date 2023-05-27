package com.example.test;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ManagedType;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Team;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioTeam;
import com.example.repositorio.RepositorioUsuario;
import com.example.service.ServicioScheduled;
import com.example.entidades.Imagen;

@RestController
@RequestMapping("/team")
public class TeamController {
	
	 @PersistenceContext
	 private EntityManager entityManager;
	 @Autowired
	 private ScheduledExecutorService scheduler;

	@Autowired
	public RepositorioTeam repositorioTeam;
	@Autowired
	public RepositorioUsuario repositorioUsuario;
	@Autowired
	public ServicioScheduled servicioScheduled;
	
	@GetMapping("/{id}")
	public Team getTeamByid(@PathVariable("id")Team team) {
		return team;	
		
	}
	@PostMapping("/crear")
	public ResponseEntity<?> createTeam(@RequestBody Team team) {
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
		Team teamCreadoTeam=new Team();
		teamCreadoTeam.setLider(autorUsuario);
		teamCreadoTeam.setNombreEquipo(team.getNombreEquipo());
		Imagen logoImagen=new Imagen();
		teamCreadoTeam.setLogoTeam(logoImagen);
		List<Usuario> jugadoresList=teamCreadoTeam.getJugadores();
		jugadoresList.add(autorUsuario);
		for(Usuario jugador:team.getJugadores()) {
			jugadoresList.add(jugador);
		}
		autorUsuario.setMiTeam(teamCreadoTeam);
		repositorioTeam.save(teamCreadoTeam);
		repositorioUsuario.save(autorUsuario);
		return new ResponseEntity(HttpStatus.OK).ok(teamCreadoTeam);
	}
	
	@PostMapping("/agregar/{nombreUsuario}")
	public ResponseEntity<?> agregerUsuarioAlTeam(@PathVariable("nombreUsuario") String idUsuario) {
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario liderUsuario=repositorioUsuario.findByNombre(nombreString);
		Team teamdelLider=repositorioTeam.findByLider(liderUsuario);
		
		if(teamdelLider!=null) {
			Usuario usuarioAgregar=repositorioUsuario.findByNombre(idUsuario);
			if (usuarioAgregar!=null) {
				List<Usuario> jugadoresList=teamdelLider.getJugadores();
				jugadoresList.add(usuarioAgregar);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(teamdelLider);

	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllTeams(){
		return ResponseEntity.ok(repositorioTeam.findAll());
	}
	@GetMapping("/creartest")
	public Team crearTest() {

		Team teamTest=new Team();
		Usuario lider= repositorioUsuario.findById(1L).get();
		Usuario agregarUsuario=repositorioUsuario.findById(2L).get();
		teamTest.setLider(lider);
		teamTest.setNombreEquipo("Nombre de mi primer Team");
		//El lider tambien forma parte de los jugadores
		teamTest.getJugadores().add(lider);
		teamTest.getJugadores().add(agregarUsuario);
		repositorioTeam.save(teamTest);
		
		List<Class<?>> managedEntities = entityManager.getMetamodel().getManagedTypes().stream()
		        .map(ManagedType::getJavaType)
		        .collect(Collectors.toList());
		System.out.println("Entidades administradas por el contexto de persistencia: " + managedEntities);
		entityManager.close();
		return teamTest;
	}
	
	@GetMapping("/correrTask")
	 public void mostrarScheduled() {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, +2);
		java.util.Date date=calendar.getTime();
		//Podemos setear un metodo a ser ejecutado en cierto momento
		ScheduledFuture<?> scheduledTask = scheduler.schedule(this::empezar, date.getTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		//Podemos cancelar el task de la siguiente manera
		//scheduledTask.cancel(true);

	}
	
	public void empezar() {
		System.out.println("Testeando otro scheduled.");
	}
	
	@GetMapping("/myteam")
	public ResponseEntity<?> myTeam() {
		try {
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
		Team miteam=autorUsuario.getMiTeam();
		if(miteam==null) {
			//Lanzar error, no tienes equipo, crear uno?
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED).ok().body("No tienes un equipo. Crea uno");
		}
		return new ResponseEntity(HttpStatus.OK).ok().body(miteam);
		}catch (NullPointerException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST).ok().body("Ocurrio un error al intentar obtener tu equipo.");
		}
	}
}
