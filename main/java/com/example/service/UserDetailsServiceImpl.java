package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioUsuario;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private RepositorioUsuario usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario=usuarioRepository.findByNombre(username);
		if(usuario==null) throw new UsernameNotFoundException("No se encontro el nombre de usuario");
		return new UserDetailsImpl(usuario);
	}

}
