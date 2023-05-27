package com.example.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entidades.Comentario;

@Repository
public interface RepositorioComentario extends CrudRepository<Comentario, Long>{
	@Query(value="SELECT * FROM comentario C JOIN NOTICIA N ON C.NOTICIA_ID=N.ID WHERE noticia_id = :idNoticia ORDER BY C.FECHA_CREACION DESC", nativeQuery = true )
	List<Comentario> findAllComentariosByIdNoticia(@Param("idNoticia") Long id);
}
