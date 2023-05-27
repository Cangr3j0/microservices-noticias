package com.example.test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import com.example.entidades.Comentario;
import com.example.entidades.Entidadprueba;
import com.example.entidades.FemaleTenisPlayer;
import com.example.entidades.MaleTenisPlayer;
import com.example.entidades.Noticia;
import com.example.entidades.Player;
import com.example.entidades.Team;
import com.example.entidades.Torneo;
import com.example.entidades.Usuario;
import com.example.repositorio.FemalePlayerRepository;
import com.example.repositorio.MalePlayerRepository;
import com.example.repositorio.PlayerRepository;
import com.example.repositorio.RepositorioComentario;
import com.example.repositorio.RepositorioMensaje;
import com.example.repositorio.RepositorioNoticia;
import com.example.repositorio.RepositorioTeam;
import com.example.repositorio.RepositorioTorneo;
import com.example.repositorio.RepositorioUsuario;

@SpringBootApplication(scanBasePackages={
"com.example.entidades", "com.example.repositorio.RepositorioMensaje","com.example.repositorio","com.example.demo.configuracion","com.example.service"})
@ComponentScan({"com.example.entidades", "com.example.repositorio.RepositorioMensaje","com.example.repositorio","com.example.service"})
@EntityScan("com.example.entidades")
@EnableJpaRepositories("com.example.repositorio")
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = NoticiaController.class)
public class TestApplication {

    @Autowired
    DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RepositorioMensaje mensajeRepository;
    @Autowired
    RepositorioComentario comentariosRepository;
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioTorneo repositorioTorneo;
    @Autowired
    RepositorioTeam repositorioTeam;
    @Value("${asd}")
    private String myFirstName;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(RepositorioUsuario userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel", "Penelope").forEach(name -> {
                Usuario user = new Usuario(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }
    @Bean
    CommandLineRunner init(RepositorioNoticia noticiaRepository, RepositorioUsuario usuarioRepository) {
    	return args ->{
    		
    		//Descomentar todo esto solo en test cuando el propertie
    		//"hibernate.hbm2ddl.auto" este seteado en "create"
    		//sino, en producción setear a validate y comentar esto, sino modifica la 
    		//base de datos y se crea todo dos veces
//    		Usuario userUsuario=new Usuario();
//    		userUsuario.setAutoridad("ADMIN");
//    		userUsuario.setNombre("Pedro");
//    		userUsuario.setFechaCreacion(new Date());
//    		userUsuario.setEmail("asd@gmail.com");
//    		userUsuario.setPassword(passwordEncoder.encode("TEST"));
//    		usuarioRepository.save(userUsuario);
//    		Usuario userUsuario2=new Usuario();
//    		userUsuario2.setAutoridad("ADMIN");
//    		userUsuario2.setNombre("Lucho");
//    		userUsuario2.setFechaCreacion(new Date());
//    		userUsuario2.setEmail("lucho@gmail.com");
//    		userUsuario2.setPassword(passwordEncoder.encode("lucho"));
//    		usuarioRepository.save(userUsuario2);
//    		Noticia noticia=new Noticia();
//    		noticia.setTitulo("testeo");
//    		noticia.setContenido("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consequat, magna hendrerit ultricies egestas, nulla odio vestibulum justo, non aliquam neque lacus malesuada urna. Vestibulum facilisis fermentum cursus. Integer egestas dignissim scelerisque. Quisque lacinia tincidunt orci, eget tincidunt ante vulputate id. Quisque tincidunt lacinia ante in porta. Suspendisse blandit diam felis, sit amet feugiat neque laoreet non. Vestibulum et libero mattis, pretium diam dapibus, sollicitudin tortor. Vestibulum blandit ut libero a vehicula. Sed magna enim, ultrices quis pellentesque at, sagittis vitae mauris. Nulla facilisi.\n"
//    				+ "\n"
//    				+ "");
//    		noticia.setAutor(userUsuario);
//    		Comentario comentario1=new Comentario();
//    		Comentario comentario2=new Comentario();
//    		comentario1.setAutor(userUsuario);
//    		comentario2.setAutor(userUsuario);
//    		comentario1.setContenido("Test");
//    		comentario2.setContenido("Test2");
//    		noticia.getComentarios().add(comentario1);
//    		noticia.getComentarios().add(comentario2);
//    		noticia.setFecha_creacion(new Date());
//    		noticiaRepository.save(noticia);
    		System.out.println(myFirstName);
    		
//    		List<Comentario>comentarioosComentarios=comentariosRepository.findAllComentariosByIdNoticia(1L);
//    		for(Comentario c:comentarioosComentarios) {
//    			System.out.println(c.getContenido());
//    		}
    		List<Usuario> usuarios=usuarioRepository.findTop5ByOrderByFechaCreacionDesc();
    		for(Usuario usuario:usuarios) {
    			System.out.println(usuario.nombre);
    		}
    		EntityManager entityManager = entityManagerFactory.createEntityManager();
    		List<Class<?>> managedEntities = entityManager.getMetamodel().getManagedTypes().stream()
    		        .map(ManagedType::getJavaType)
    		        .collect(Collectors.toList());
    		System.out.println("Entidades administradas por el contexto de persistencia numero 2: " + managedEntities);
    		entityManager.close();


    		};
    }
	   

}
