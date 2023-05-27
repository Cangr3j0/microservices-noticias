package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entidades.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import antlr.collections.List;

public class UserDetailsImpl implements UserDetails{

	private Usuario usuario;
	public UserDetailsImpl(Usuario usuario) {
		this.usuario=usuario;
		}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		ArrayList<SimpleGrantedAuthority> listaAutoridades=new ArrayList<>();
		String autoridad=usuario.getAutoridad();
		if(autoridad=="ADMIN") {
		listaAutoridades.add(new SimpleGrantedAuthority("ROLE_"+autoridad));
		listaAutoridades.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else {
			listaAutoridades.add(new SimpleGrantedAuthority("ROLE_"+autoridad));
		}
		return listaAutoridades;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getNombre();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		boolean isEnabled=false;
		if(usuario.getEnabled()=='Y') {
			isEnabled=true;
		}
		else {
			isEnabled=false;
		}
		return isEnabled;
	}

}
