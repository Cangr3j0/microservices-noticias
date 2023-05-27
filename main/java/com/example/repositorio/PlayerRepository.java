package com.example.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.entidades.Player;

@NoRepositoryBean
public interface PlayerRepository extends CrudRepository<Player,Long>{

}
