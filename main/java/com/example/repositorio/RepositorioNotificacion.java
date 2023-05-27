package com.example.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Notificacion;
import com.example.entidades.Usuario;

@Repository
public interface RepositorioNotificacion extends JpaRepository<Notificacion, Long>{

	@Query(value="SELECT DISTINCT n FROM Notificacion n LEFT JOIN FETCH n.usuarioNotificado")
	public List<Notificacion> findAllNotificaciones();
	
	public List<Notificacion> findByUsuarioNotificado(Usuario usuario);
	
}
