package com.mx.ApiRestCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestCinepolis.model.Peliculas;
import com.mx.ApiRestCinepolis.service.PeliculasServImpl;

@RestController
@RequestMapping(path ="PeliculasWebService")
public class PeliculasWebService {
	//inyectamos dependencias
	@Autowired
	PeliculasServImpl peliculasServImpl;
	
	//http://localhost:9000/PeliculasWebService/mostrar
	@GetMapping(path = "mostrar")
	public List<Peliculas> mostrar(){
		return peliculasServImpl.listaPeliculas();
	}
	
	//http://localhost:9000/PeliculasWebService/guardarPeliculas
	@PostMapping(path = "guardarPeliculas")
	public ResponseEntity<?>guardarPeliculas(@RequestBody Peliculas peliculas){
		boolean response = peliculasServImpl.guardarPeliculas(peliculas);
		if (response == false )
			return new ResponseEntity<>(peliculas,HttpStatus.CREATED);
		else
			return new ResponseEntity<>("La pelicula ya existe en la Base de datos", HttpStatus.OK);
	}
	//http://localhost:9000/PeliculasWebService/buscarXid
	@PostMapping(path = "buscarXid")
	public Peliculas buscarXid(@RequestBody Peliculas peliculas) {
		return peliculasServImpl.buscarXid(peliculas.getIdPeli());
	}
	
	//http://localhost:9000/PeliculasWebService/editarXid
	@PostMapping(path ="editarXid")
	public ResponseEntity<?> editarXid(@RequestBody Peliculas peliculas){
		boolean response = peliculasServImpl.editarXid(peliculas);
		
		if(response == false)
			return new ResponseEntity<>("El registro no existe para editar", HttpStatus.OK);
		else 
			return new ResponseEntity<>(peliculas,HttpStatus.CREATED);
	}
	//http://localhost:9000/PeliculasWebService/eliminarXid
	@PostMapping(path ="eliminarXid")
	public ResponseEntity<?> eliminarXid (@RequestBody Peliculas peliculas){
		boolean response = peliculasServImpl.eliminarXid(peliculas.getIdPeli());
		
		if(response == true) {
			return new ResponseEntity<String>("Se elimino el registro", HttpStatus.OK);
		}else
			return new ResponseEntity<String>("No se encontro el registro", HttpStatus.OK);
		
	}
	//http://localhost:9000/PeliculasWebService/consultarxNombre/{nombre}
	//http://localhost:9000/PeliculasWebService/consultarxNombre/SHERK
	@GetMapping(path = "consultarxNombre/{nombre}")
	public Peliculas buscarXnombre(@PathVariable ("nombre") String nombre) {
		return peliculasServImpl.buscarXnombre(nombre);
	}
	//http://localhost:9000/PeliculasWebService/{genero}
	//http://localhost:9000/PeliculasWebService/FANTASIA
	@GetMapping(path = "/{genero}")
	public List<Peliculas> buscarXgenero(@PathVariable ("genero") String genero) {
		return peliculasServImpl.buscarXGenero(genero);
	}
	//http://localhost:9000/PeliculasWebService/eliminar/{nombreP}
	//http://localhost:9000/PeliculasWebService/eliminar/SHERK
	@GetMapping("/eliminar/{nombreP}")
	public ResponseEntity<?> eliminarXnombre(@PathVariable ("nombreP") String nombreP){
		boolean response = peliculasServImpl.eliminarXnombre(nombreP);
		if(response == true) {
			return new ResponseEntity<String>("Se elimino el registro por nombre", HttpStatus.OK);
		}else
			return new ResponseEntity<String>("No se encontro el registro", HttpStatus.OK);
		
	}
	

}
