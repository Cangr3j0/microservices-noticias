package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entidades.Usuario;
import com.example.interfaces.IServicioUsuario;
import com.example.repositorio.RepositorioUsuario;

@Service
public class ServicioUsuario implements IServicioUsuario{
	
	@Autowired
	RepositorioUsuario repositorioUsuario;

	@Override
	public Usuario obtenerUsuarioLogeado() throws UsernameNotFoundException{
		UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreString=userUsuario.getUsername();
		Usuario usuarioLogeado=repositorioUsuario.findByNombre(nombreString);
		if(usuarioLogeado==null) throw new UsernameNotFoundException("No se encontro el nombre de usuario");
		return usuarioLogeado;
	}
	
}
