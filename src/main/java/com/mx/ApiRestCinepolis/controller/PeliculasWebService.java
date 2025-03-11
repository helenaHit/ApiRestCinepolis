package com.mx.ApiRestCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	
}
