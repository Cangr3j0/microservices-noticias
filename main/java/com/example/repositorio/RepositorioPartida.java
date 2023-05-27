package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Partida;

@Repository
public interface RepositorioPartida extends CrudRepository<Partida, Long>{

}
