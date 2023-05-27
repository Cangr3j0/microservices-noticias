package com.example.test;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidades.Usuario;
import com.example.repositorio.RepositorioUsuario;
import com.example.service.UserDetailsImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private RepositorioUsuario userRepository;

	  @GetMapping("/user")
	  public UserDetailsImpl user(@AuthenticationPrincipal Principal principalUser) {
		     //Since in AuthFilter You are setting UsernamePasswordAuthenticationToken, we will retrieve and cast
		  UserDetailsImpl userUsuario=(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  return userUsuario;
	  }
	  
	  @GetMapping("/role")
	  public String role() {
		  UserDetails userUsuario=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String nombreString=userUsuario.getUsername();
			Usuario autorUsuario=userRepository.findByNombre(nombreString);
			return autorUsuario.getAutoridad();
	  }
	  @GetMapping("/all")
    public List<Usuario> getUsers() {
        return (List<Usuario>) userRepository.findAll();
    }

    @PostMapping("/create")
    void addUser(@RequestBody Usuario user) {
    	Usuario userUsuario=new Usuario();
		userUsuario.setAutoridad("USER");
		userUsuario.setNombre(user.getNombre());
		userUsuario.setFechaCreacion(new Date());
		userUsuario.setEmail(user.getEmail());
		userUsuario.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(userUsuario);
    }
    @GetMapping("/{id}")
    public ResponseEntity getUsuario(@PathVariable("id")Long idUsuario) {
    	Usuario usuario=userRepository.findById(idUsuario).get();
    	if(usuario!=null) {
    		return ResponseEntity.ok().body(usuario);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario");
    	}
    }
    @GetMapping("/cantidad")
    public Long cantidadUsuarios() {
    	return userRepository.cantidadUsuarios();
    }
    @GetMapping("/lastusers")
    @Transactional
	public List<Usuario> getLastUsers() {
    	return userRepository.findTop5ByOrderByFechaCreacionDesc();
	}

}