package com.example.test;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entidades.Comentario;
import com.example.entidades.Entidadprueba;
import com.example.entidades.Noticia;
import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioMensaje;
import com.example.repositorio.RepositorioNoticia;
import com.example.repositorio.RepositorioUsuario;

@SpringBootTest
class TestApplicationTests {
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    RepositorioMensaje mensajeRepository;
//    
//    @Autowired
//    RepositorioNoticia noticiaRepository;
//
//    @Autowired
//    RepositorioUsuario usuarioRepository;
//
//	@Test
//	void contextLoads() {
//		   Entidadprueba mensaje=new Entidadprueba();
//		   mensaje.setMensajeString("test");
//		   mensajeRepository.save(mensaje);
//		   
//		   List<Entidadprueba> messages = (List<Entidadprueba>)mensajeRepository.findAll();
//		   
//assert(messages.size()==1);
//				   
//	}
//	@Test
//	void testNoticia() {
//		Usuario userUsuario=new Usuario();
//		userUsuario.setNombre("Pedro");
//		userUsuario.setEmail("asd@gmail.com");
//		usuarioRepository.save(userUsuario);
//		Noticia noticia=new Noticia();
//		noticia.setAutor(userUsuario);
//		Comentario comentario1=new Comentario();
//		Comentario comentario2=new Comentario();
//		comentario1.setAutor(userUsuario);
//		comentario2.setAutor(userUsuario);
//		comentario1.setContenido("Test");
//		comentario2.setContenido("Test2");
//		noticia.getComentarios().add(comentario1);
//		noticia.getComentarios().add(comentario2);
//		noticia.setFecha_creacion(new Date());
//		noticiaRepository.save(noticia);
//		
//		
//	}

}
