package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Entidadprueba;

@Repository
public interface RepositorioMensaje extends CrudRepository<Entidadprueba, Long>{

}
