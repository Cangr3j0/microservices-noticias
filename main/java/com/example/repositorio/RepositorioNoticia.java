package com.example.repositorio;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Comentario;
import com.example.entidades.Noticia;

@Repository
public interface RepositorioNoticia extends JpaRepository<Noticia, Long>{

	@EntityGraph(value = "Noticia.comentarios")
	List<Noticia> findAll(Sort sort);
}
