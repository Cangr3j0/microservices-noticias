package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Likes;

@Repository
public interface RepositorioLikes extends CrudRepository<Likes, Long>{

}
