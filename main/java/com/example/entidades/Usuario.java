package com.example.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_SEQ")
	@SequenceGenerator(sequenceName = "usuario_seq", allocationSize = 1, name = "USU_SEQ")
	@Column(name = "ID")
	public Long id;
	
	@NonNull
	@Column(nullable = false,unique=true)
	public String nombre;
	
	@Column(nullable = false)
	public String email;
	
	@NonNull
	public String password;
	
	public char enabled;
	
	@NonNull
	public String autoridad;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	public Date fechaCreacion;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private List<Imagen> imagenes=new ArrayList<Imagen>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
	@JsonBackReference
	public Team miTeam; 
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
		this.autoridad="USER";
		this.enabled='Y';
	}

	public Usuario(String name, String string) {
		this.nombre=name;
		this.email=string;
	}
	
	

	//@JsonProperty(access = Access.WRITE_ONLY)
	public String getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(String autoridad) {
		this.autoridad = autoridad;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

//	public List<Imagen> getImagenes() {
//		return imagenes;
//	}
//
//	public void setImagenes(List<Imagen> imagenes) {
//		this.imagenes = imagenes;
//	}

	public Team getMiTeam() {
		return miTeam;
	}

	public void setMiTeam(Team miTeam) {
		this.miTeam = miTeam;
	}

	
	
	
	
	
	
	

}
