package com.example.test;


import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entidades.Comentario;
import com.example.entidades.Imagen;
import com.example.entidades.Likes;
import com.example.entidades.Noticia;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioComentario;
import com.example.repositorio.RepositorioImagen;
import com.example.repositorio.RepositorioNoticia;
import com.example.repositorio.RepositorioUsuario;
import com.example.service.UserDetailsImpl;
import com.example.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NoticiaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticiaController.class);
	@Autowired
    private RepositorioNoticia noticiarepository;
	@Autowired
    private RepositorioComentario comentariorepository;
	@Autowired
	 private RepositorioUsuario usuarioRepository;
	@Autowired
	private RepositorioImagen imagenRepository;
	
	
	@GetMapping("/noticias")
	@Transactional
	public List<Noticia> getNoticias(){
//		return (List<Noticia>)noticiarepository.findAll(Sort.by(Direction.DESC, "id"));
		return (List<Noticia>)noticiarepository.findAll(Sort.by(Direction.DESC, "id"));
	}
	@PostMapping("/noticias")
	public void addNoticia(@RequestPart(name = "noticia") String noticiaParam,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		//Falta la parte de reducir el tamaño del file asi no ocupa tanto espacio en la base de datos...
		
		LOGGER.warn("log"+noticiaParam);
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario autorUsuario=usuarioRepository.findByNombre(nombreString);
		 Noticia noticia = new ObjectMapper().readValue(noticiaParam, Noticia.class);
		noticia.setAutor(autorUsuario);
		Imagen imagenSubida= new Imagen();
		try {
			imagenSubida.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagenSubida.setNombre(StringUtils.cleanPath(file.getOriginalFilename()));
		imagenSubida.setTipo(file.getContentType());
		List<Imagen>imagenesNoticia=noticia.getImagenes();
		imagenesNoticia.add(imagenSubida);
		noticiarepository.save(noticia);
		
	}
	@GetMapping("/noticias/{id}")
	public Noticia getNoticiasById(@PathVariable("id") Noticia noticia){
		return noticia;
	}
	@GetMapping("/comentarios/cantidad/{id}")
	public Long getCantidadComentarios(@PathVariable("id") Noticia noticia) {
		return noticia.getCantidadComentarios();
	}

	@GetMapping("/likes/{id}")
	public List<Likes> getLikes(@PathVariable("id")Noticia noticia) {
		return noticia.getLikes();
	}

	  
	  @PutMapping("noticias/update/")
	  public Noticia updateNoticia(@RequestBody Noticia noticia) {
		 return noticiarepository.save(noticia);
	  }
	  @DeleteMapping("/noticias/delete/{id}")
	  public Noticia eliminarNoticia (@PathVariable("id")Noticia noticia) {
		  Noticia noticiaeliminada=noticia;
		   noticiarepository.delete(noticia);
		   return noticiaeliminada;
	  }
	  
	  
	  //En vez de un string debería retornar la Imagen asi en angular
	  //no tengo que andar mapeando a text la respuesta y puedo crear
	  //un objeto de tipo Imagen
	  @GetMapping(value="noticias/imagen/{id}")
	  public String getImagenNoticia(@PathVariable("id") Long id) {
		  List<Imagen> noticiasImagen=null;
		  String encodedImagen=null;
		  if(noticiarepository.existsById(id)) {
			  noticiasImagen=noticiarepository.findById(id).get().getImagenes();
			  for(Imagen img:noticiasImagen) {
				 System.out.println(img.getNombre()); 
			  encodedImagen=Base64.getEncoder().encodeToString(img.getData());
			  }
		  }
		  return encodedImagen;
	  }
}
