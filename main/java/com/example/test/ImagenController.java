package com.example.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.entidades.Imagen;
import com.example.repositorio.RepositorioImagen;

@Controller
public class ImagenController {
	
	@Autowired
	private RepositorioImagen repositorioImagen;
	@PostMapping("/upload")
	public ResponseEntity subirImagen(@PathVariable MultipartFile file) {
		//Es la entidad Imagen, no la clase utilizada para RiotGames json
		Imagen imagenSubida= new Imagen();
		try {
		imagenSubida.setData(file.getBytes());
		imagenSubida.setNombre(StringUtils.cleanPath(file.getOriginalFilename()));
		imagenSubida.setTipo(file.getContentType());
		repositorioImagen.save(imagenSubida);
		return ResponseEntity.status(HttpStatus.OK).body("Se subió el archivo exitosamente.");
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Ocurrió un error al subir el archivo.");
		}

	}
	
	@GetMapping(value = "/imagen/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImagen(@PathVariable("id") Long id) {
		byte[] toreturn=null;
		if(repositorioImagen.existsById(id)) {
		toreturn=repositorioImagen.findById(id).get().getData();
		}
		if (toreturn==null) {
			ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error");
		}
		return toreturn;
	}
}
