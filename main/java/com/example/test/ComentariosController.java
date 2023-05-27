package com.example.test;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Comentario;
import com.example.entidades.Noticia;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioComentario;
import com.example.repositorio.RepositorioNoticia;
import com.example.repositorio.RepositorioUsuario;

import ch.qos.logback.classic.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ComentariosController {

	 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ComentariosController.class.getName());
	@Autowired
	 RepositorioComentario repoComentarios;
	@Autowired
    private RepositorioNoticia noticiarepository;
	@Autowired
	private RepositorioUsuario usuarioRepository;
	public Comentario findById(@PathVariable Comentario comentario) {
		return comentario;
	}
	@GetMapping("/comentarios/{id}")
	public List<Comentario> findAllComentariosOrdenDescendente(@PathVariable("id") Noticia noticia){
		return repoComentarios.findAllComentariosByIdNoticia(noticia.getId());
	}
	@PostMapping("/comentarios/{id}")
	public List<Comentario> addComentario(@PathVariable("id") Noticia noticia,@RequestBody String contenidoComentario) {
//		try{
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=usuarioRepository.findByNombre(nombreString);
		Comentario nuevoComentario=new Comentario();
		nuevoComentario.setAutor(autorUsuario);
		nuevoComentario.setContenido(contenidoComentario);
		List<Comentario> listaDeComentarios=noticia.getComentarios();
		listaDeComentarios.add(nuevoComentario);
		repoComentarios.save(nuevoComentario);
		noticiarepository.save(noticia);
	
//		}catch (NullPointerException e) {
//			e.printStackTrace();
//			LOGGER.info("El parametro usuario es null");
//			System.out.println("Error null");
//		}
		return noticia.getComentarios();
	}

	@DeleteMapping("/comentarios/delete/{id}")
	public Comentario eliminarComentario(@PathVariable("id")Comentario comentario) {
		Comentario comentarioeliminado=comentario;
		repoComentarios.delete(comentario);
		return comentarioeliminado;
	}
}
