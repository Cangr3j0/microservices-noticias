package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Imagen;

@Repository
public interface RepositorioImagen extends CrudRepository<Imagen, Long>{

}
