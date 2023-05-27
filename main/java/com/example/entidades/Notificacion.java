package com.example.entidades;



import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTI_SEQ")
	@SequenceGenerator(sequenceName = "notificacion_seq", allocationSize = 1, name = "NOTI_SEQ")
	private Long id;
	
	private String mensaje;
	
	 @Temporal(TemporalType.DATE)
	private Date fecha_creado;
	
	private boolean leido;
	
	//Muchas notificaciones pueden pertenecer a un solo usuario
	//Esto es muchas notificaciones pueden ir a un mismo usuario
	@ManyToOne
	@JoinColumn(name="usuarioNotificado")
	private Usuario usuarioNotificado;

	public Long getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Date getFecha_creado() {
		return fecha_creado;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setFecha_creado(Date fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}


	public Usuario getUsuarioNotificado() {
		return usuarioNotificado;
	}

	public void setUsuarioNotificado(Usuario usuarioNotificado) {
		this.usuarioNotificado = usuarioNotificado;
	}
	
	
}
