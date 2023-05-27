package com.example.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entidades.Notificacion;
import com.example.entidades.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	
	Usuario findByNombre(String nombre);
	 @Query("SELECT COUNT(*) FROM Usuario u where u.autoridad='USER'")
	Long cantidadUsuarios();
	 //@Query("SELECT u FROM Usuario u LEFT JOIN u.notificaciones ORDER BY u.fechaCreacion DESC where ROWNUM <= 5")
	List<Usuario> findTop5ByOrderByFechaCreacionDesc();
	
	
	//Esto usarlo cuando se quiere recuperar los datos bidireccionales, en este caso quiero recuperar
	//el usuario junto con sus notificaciones
	//Tambien recordar poner por ejemplo en el usuario si tiene una lista de notificaciones(Onetomany) en el getNotificaciones
	//@JsonManagedReference
	//	public List<Notificacion> getNotificaciones() {
	//		return notificaciones;
	//	}
	//y en las notificaciones en el manytoone con referencia a el usuario 
	//	 @JsonBackReference
	//	public Usuario getUsuarioNotificado() {
	//		return usuarioNotificado;
	//	}
	//Con jsonmanagerdreference y jsonbackreference evitamos que nos retorne un json ciclico provocando stackoverflow en la consola
//	 @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.notificaciones WHERE u.id = (:id)")
//	Usuario findUsuarioByIdYfetch(@Param("id") Long id);
	 
//	 @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.notificaciones")
//	List<Usuario> findAllUsuarios(); 
}
