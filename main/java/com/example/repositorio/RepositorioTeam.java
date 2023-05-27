package com.example.repositorio;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entidades.Team;
import com.example.entidades.Usuario;

@Repository
public interface RepositorioTeam extends CrudRepository<Team, Long>{

	public Team findByLider(Usuario lider);
}
