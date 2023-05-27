package com.example.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entidades.Likes;
import com.example.entidades.Noticia;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioLikes;
import com.example.repositorio.RepositorioNoticia;
import com.example.repositorio.RepositorioUsuario;

@Controller
public class LikeController {

	@Autowired
	private RepositorioLikes repositorioLikes;
	@Autowired
	private RepositorioNoticia repositorioNoticia;
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@PostMapping("/likes/{id}")
	public ResponseEntity addLike(@PathVariable("id")Noticia noticia) {
		ResponseEntity result = null;
		try{
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=repositorioUsuario.findByNombre(nombreString);
		List<Likes>likesNoticia=noticia.getLikes();
		Iterator<Likes>likesIterator=likesNoticia.iterator();
		boolean encontre=false;
		while(likesIterator.hasNext()&&!encontre) {
			Likes ActuaLikes=likesIterator.next();
			System.out.println(ActuaLikes.getAutor().getNombre());
			if(ActuaLikes.getAutor().equals(autorUsuario)) {
				encontre=true;
				likesNoticia.remove(ActuaLikes);
				repositorioNoticia.save(noticia);
			}
		}
		if(!encontre) {
		Likes nuevoLike=new Likes();
		nuevoLike.setAutor(autorUsuario);
		likesNoticia.add(nuevoLike);
		repositorioLikes.save(nuevoLike);
		repositorioNoticia.save(noticia);
		result=new ResponseEntity<List<Likes>>(likesNoticia, HttpStatus.OK);
		}
		else {
			//O talvez quitar el like de la lista
			//Debería removerlo desde el repositorio de likes
		result=new ResponseEntity<List<Likes>>(likesNoticia, HttpStatus.OK);
//		result=new ResponseEntity<String>("Ya le has dado like", HttpStatus.METHOD_NOT_ALLOWED);
		}


		}
		catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("No esta logeado.");
			result=new ResponseEntity<String>("Debes estar logeado para realizar esta acción.", HttpStatus.UNAUTHORIZED);
		}
		return result;
	}
}
