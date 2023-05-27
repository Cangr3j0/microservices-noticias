package com.example.test;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Noticia;
import com.example.entidades.Partida;
import com.example.entidades.Team;
import com.example.entidades.Torneo;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioPartida;
import com.example.repositorio.RepositorioTeam;
import com.example.repositorio.RepositorioTorneo;
import com.example.repositorio.RepositorioUsuario;

@RestController
@RequestMapping("/torneo")
public class TorneoController {
	@Autowired
	RepositorioUsuario repositorioUsuario;
	@Autowired
	RepositorioTorneo repositorioTorneo;
	@Autowired
	RepositorioTeam repositorioTeam;
	@Autowired 
	RepositorioPartida repositorioPartida;

	@GetMapping("/all")
	public List<Torneo> getTorneos(){
		return (List<Torneo>)repositorioTorneo.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>etTorneoById(@PathVariable("id")Long id) {
		if(repositorioTorneo.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(repositorioTorneo.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error");
	}
//	@PostMapping("/torneo")
//	public void setTorneo() {
//		Torneo torneo=new Torneo();
//		List<Usuario>participantesList=torneo.getParticipantes();
//		torneo.setAutor(repositorioUsuario.findById(2L).get());
//		participantesList.add(repositorioUsuario.findById(2L).get());
//		torneo.setTitulo("Primer torneo");
//		torneo.setReglasyotros("No hay reglas");
//		repositorioTorneo.save(torneo);
//	}
	
	  @PostMapping("/torneo/add")
	  public ResponseEntity agregarTorneo(@RequestBody Torneo torneo) {
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
		torneo.setAutor(autorUsuario);
		repositorioTorneo.save(torneo);
		 return ResponseEntity.status(HttpStatus.OK).body("Se agregó el torneo "+torneo.getTitulo());
	  }
	  
	@PostMapping("/addUsers")
	public ResponseEntity agregarUsuarios(@RequestBody Torneo torneo) {
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
		List<Usuario> participantes=torneo.getParticipantes();
		participantes.add(autorUsuario);
		repositorioTorneo.save(torneo);
		return ResponseEntity.status(HttpStatus.OK).body("Te has unido al torneo "+torneo.getTitulo());
	}
	
	
	  @PutMapping("/update/")
	  public Torneo updateTorneo(@RequestBody Torneo torneo) {
		 return repositorioTorneo.save(torneo);
	  }
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity eliminarTorneo (@PathVariable("id")Long torneoId) {
		  Torneo torneoEliminar=null;
		  if(repositorioTorneo.existsById(torneoId)) {
			  torneoEliminar=repositorioTorneo.findById(torneoId).get();
			  repositorioTorneo.delete(torneoEliminar);
		  }else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Ocurrio un error al intentar borrar el torneo");
		  }
		  return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el torneo.");
	  }
	  
	  @GetMapping("/participantes/{id}")
	  public ResponseEntity getParticipantes(@PathVariable("id")Long torneoId){
		  List<Usuario> toreturn=null;
		  if(repositorioTorneo.existsById(torneoId)) {
			  Torneo torneo=repositorioTorneo.findById(torneoId).get();
			  toreturn=torneo.getParticipantes();
		  }
		  if(toreturn==null) {
			  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Ocurrió un error al intentar obtener los participantes");
		  }
		  return ResponseEntity.status(HttpStatus.OK).body(toreturn);
	  }
	  
	  
	  @GetMapping("/crearBracket/{id}")
	  public Torneo crearBracket(@PathVariable("id") Torneo torneo) {
		  
		  //Creo un equipo la primera vez, despues lo comento.
//		Team t1=new Team();
//		t1.setNombreEquipo("Equipo 2");
//		t1.setLider(repositorioUsuario.findByNombre("Cangr3j0"));
//		List<Usuario> jugadorest1=t1.getJugadores();
//		jugadorest1.add(repositorioUsuario.findByNombre("Cangr3j0"));
//		repositorioTeam.save(t1);
		//Si estan guardaos ya los puedo obtener del repositorio
//		torneo.getEquiposParticipantes().add(repositorioTeam.findByLider(repositorioUsuario.findByNombre("Cangr3j0")));
//		torneo.getEquiposParticipantes().add(repositorioTeam.findByLider(repositorioUsuario.findByNombre("Pedro")));
		
		List<Team> equiposList=torneo.getEquiposParticipantes();
		//Para no hacer operaciones sobre la misma instancia del objeto de la base de datos, copiamos la lista
		List<Team> equiposListcopy = new ArrayList<Team>(torneo.getEquiposParticipantes());

		int cantParticipantes=torneo.getEquiposParticipantes().size();
//		int ronda=1;
//		if(cantParticipantes>0) {
//			crearPartidasPrimerRonda(equiposListcopy, ronda,repositorioPartida);
//			ronda=ronda+1;
//			crearPartidas(cantParticipantes/2,ronda,repositorioPartida);
//		for(Partida p:repositorioPartida.findAll()) {
//			torneo.getPartidas().add(p);
//			}
//	
//		}
		int cantparticipanteslocal=cantParticipantes;
		while (cantparticipanteslocal%2!=0) {
			cantparticipanteslocal++;
		}
		int cantpartidas=obtenerCantidadPartidas(cantparticipanteslocal);
		crearBracket(cantpartidas, 0, repositorioPartida);
	    
		for(Partida p:repositorioPartida.findAll()) {
			if(p.getPartida1()==null&&p.getPartida2()==null) {
				//Estoy en primera ronda
				asignarEquiposPrimeraRonda(p,equiposListcopy,repositorioPartida);
				
			}
		}
	    
		for(Partida p:repositorioPartida.findAll()) {
		torneo.getPartidas().add(p);
		}
		
		repositorioTorneo.save(torneo);
		return torneo;
		  

	}
	  
	  private int obtenerCantidadPartidas(int cantparticipanteslocal) {
		    int cantpartidas = 0;
		    while(cantparticipanteslocal > 0) {
		    cantpartidas = cantpartidas + cantparticipanteslocal/2;
		    cantparticipanteslocal = cantparticipanteslocal / 2;
		    }
		    return cantpartidas;
	}
	//Creara un arbol de partidas perfecto desde la raiz
	  //There are 2h + 1 – 1 Nodes
	  //Si hay 4 equipos crearBracket(7)
		private Partida crearBracket(int cantPartidas, int index,RepositorioPartida repositorioPartida){
	        if (index >= cantPartidas) {
	            return null;
	        }
	        
	       Partida partida=new Partida();
	        partida.setRonda((long) index);
	        partida.setPartida1(crearBracket(cantPartidas, 2 * index + 1,repositorioPartida));
	        partida.setPartida2(crearBracket(cantPartidas, 2 * index + 2,repositorioPartida));
	        repositorioPartida.save(partida);
	        return partida;
	    }
	  
	  //Si son 3 equipos mando 3/2=1
	  //Si son 4 equipos mando 4/2=2 cantpartidas =2 
	  //2/2=1
//	  private void crearPartidas(int cantpartidas, int ronda, RepositorioPartida repositorioPartida2) {
//		 if(cantpartidas>=1) {
//		  Partida partida=new Partida();
//		  partida.setEquipo1(null);
//		  partida.setEquipo2(null);
//		  partida.setRonda((long) ronda);
//		  repositorioPartida2.save(partida);
//		 }else {
//			 for(int i=0;i<cantpartidas;i++) {
//				  Partida partida=new Partida();
//				  partida.setEquipo1(null);
//				  partida.setEquipo2(null);
//				  partida.setRonda((long) ronda);
//				  repositorioPartida2.save(partida);
//			 }
//			 ronda++;
//			 crearPartidas(cantpartidas/2,ronda,repositorioPartida2);
//		 }
//	}

	  
	  private void crearPartidasPrimerRonda(List<Team>equiposList,int ronda, RepositorioPartida repositorioPartida2) {
		  //Caso base, si la cantidad de partidas es < a 1 freno?
		 
		  if(equiposList.size()==1) {
			  Team t1=equiposList.remove(0);
			  Partida partida=new Partida();
			  partida.setEquipo1(t1);
			  partida.setEquipo2(null);
			  partida.setRonda((long) ronda);
			  repositorioPartida2.save(partida);
		  }
		  else {
			  Team t1=equiposList.remove(equiposList.size()-1);
			  Team t2=equiposList.remove(equiposList.size()-1);
				  Partida partida=new Partida();
				  partida.setEquipo1(t1);
				  partida.setEquipo2(t2);
				  partida.setRonda((long) ronda);
				  repositorioPartida2.save(partida);
				  crearPartidasPrimerRonda(equiposList, ronda,repositorioPartida2);
		  }
		  //Caso recursivo, caso contrario
		//Creo Partidas igual a la cantidad de participantes dividido 2
		//llamo de nuevo a crearPartidas con cantParticipantes dividido 2
	  }
	  
	  private void asignarEquiposPrimeraRonda(Partida partida,List<Team>equiposList,RepositorioPartida repositorioPartida) {
		  if(equiposList.size()==1) {
			  Team t1=equiposList.remove(0);
			  partida.setEquipo1(t1);
			  partida.setEquipo2(null);
			  repositorioPartida.save(partida);
		  }else if(equiposList.size()<1){
			  
		  }
		  else {
			  Team t1=equiposList.remove(equiposList.size()-1);
			  Team t2=equiposList.remove(equiposList.size()-1);
			  partida.setEquipo1(t1);
			  partida.setEquipo2(t2);
			  repositorioPartida.save(partida);
		  }
	  }
	  
	  @DeleteMapping("/removerpartidas/{id}")
	  public ResponseEntity<?> deletePartidas(@PathVariable("id")Long idTorneo){
			Torneo torneo=repositorioTorneo.findById(idTorneo).get();
			List<Partida>partidas=torneo.getPartidas();
			Iterator<Partida>partidasIterator=partidas.iterator();
			while(partidasIterator.hasNext()) {
				Partida partida=partidasIterator.next();
				partidas.remove(partida);
				repositorioTorneo.save(torneo);
			}

			
			return new ResponseEntity(HttpStatus.OK).ok().body("Se removieron las partidas");
	  }
	  
	  @GetMapping("/{id}/addTeam")
	  public ResponseEntity<?> agregarEquipo(@PathVariable("id")Long idTorneo) {
			UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String nombreString=userUsuario.getUsername();
			Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
			//Voy a agregar el equipo del usuario al torneo, pero antes tengo que comprobar si el usuario tiene un equipo.
			Team usuarioTeam=repositorioTeam.findByLider(autorUsuario);
			if(usuarioTeam==null) {
				//Lanzar error, debes crear un equipo para poder unirte al torneo.
			}else {
				Torneo torneo=repositorioTorneo.findById(idTorneo).get();
				if(torneo==null) {
					//Lanzar error, el torneo no existe.
				}else {
					
				}
			}
		  return null;
	  }
	  
	  @GetMapping("/raiz/{torneoid}")
	  public ResponseEntity<?> getRaizTorneo(@PathVariable("torneoid") Long id){
		  Torneo torneo=repositorioTorneo.findById(id).get();
		  Partida partida=torneo.getRaiz();
		return ResponseEntity.ok().body(partida);
	  }
	  
	  @GetMapping("/posicionar/{torneoid}")
	  public ResponseEntity<?>posicionarXeY(@PathVariable("torneoid") Long id){
		  Torneo torneo=repositorioTorneo.findById(id).get();
		  Partida raizPartida=torneo.getRaiz();
		  
		  organizar(raizPartida,660L,0L,"");
		  return ResponseEntity.ok().body("Se establecieron las posiciones.");
	  }
	  private void organizar(Partida partida,Long x,Long y,String path) {
		  if(partida==null) {
			 
		  }else {
		  partida.setPosx(x);
		  partida.setPosy(y);
		  partida.setPath(path);
		  organizar(partida.getPartida1(), x-162, y-10,"M 41.159506,85.312431 H 205.04918 l 0,-28.811653");
		  organizar(partida.getPartida2(), x-162, y+10,"M 41.159506,85.312431 H 205.04918 l 0,30.308359");
		  repositorioPartida.save(partida);
		  }
	  }
	  @GetMapping("/actualizarGanador")
	  public void actualizarGanador()
	  {
		  
	  }
}
