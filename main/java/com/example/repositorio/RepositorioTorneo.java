package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Torneo;

@Repository
public interface RepositorioTorneo extends CrudRepository<Torneo, Long>{

}
