package com.example.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Notificacion;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioNotificacion;
import com.example.repositorio.RepositorioUsuario;
import com.example.service.ServicioUsuario;


@RestController
@RequestMapping("/notificacion")
public class NotificacionController {

	@Autowired
	ServicioUsuario usuarioService;
	
	@Autowired
	RepositorioNotificacion repositorioNotificacion;
	
	@Autowired
	RepositorioUsuario repositorioUsuario;
	

	@GetMapping("/todas")
	@Transactional
	public List<Notificacion> todas(){
		 List<Notificacion> notificaciones =repositorioNotificacion.findAllNotificaciones();
	        return notificaciones;
	}
	
	@GetMapping("/notificaciones")
	public ResponseEntity<?> obtenerNotificacionesPorUsuario(){
		Usuario usuarioLogeado=usuarioService.obtenerUsuarioLogeado();
		List<Notificacion>notificacionesList=repositorioNotificacion.findByUsuarioNotificado(usuarioLogeado);
		if(notificacionesList.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notificaciones");
		}
		return ResponseEntity.status(HttpStatus.OK).body(notificacionesList);
	}
	
	@PostMapping("/enviaratodos")
	public ResponseEntity<Map<String, Object>> enviarNotificacionATodos(@RequestBody Notificacion notificacion){
		Iterable<Usuario>usuarios=repositorioUsuario.findAll();
		Map<String,Object>respuesta=new HashMap<String,Object>();
		if(notificacion.getMensaje()!=null) {
		for(Usuario user:usuarios) {
			notificacion.setUsuarioNotificado(user);
			notificacion.setFecha_creado(new Date());
			repositorioNotificacion.save(notificacion);
		}
		
		respuesta.put("mensaje", "Se envió la notificación '"+notificacion.getMensaje()+"' a todos los usuarios.");
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
		respuesta.put("mensaje","El mensaje no puede ser vacio");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
	}
	
//	public ResponseEntity<<Map<String,Object>> 
}
