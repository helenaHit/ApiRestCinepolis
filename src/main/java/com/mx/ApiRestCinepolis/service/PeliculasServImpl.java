package com.mx.ApiRestCinepolis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestCinepolis.dao.PeliculasDao;
import com.mx.ApiRestCinepolis.model.Peliculas;



@Service
public class PeliculasServImpl {
	
	//inyectamos dependencias
	@Autowired
	PeliculasDao peliculasDao;
	
	//CRUD BASICO
	//CONSULTA
	@Transactional(readOnly= true)
	public List<Peliculas> listaPeliculas(){
		List<Peliculas> listaPeliculas = peliculasDao.findAll();
		return listaPeliculas;
	}
	//GUARDAR
	@Transactional()
	public Boolean guardarPeliculas(Peliculas peliculas){
		boolean bandera = false;
		for(Peliculas p :peliculasDao.findAll() ) {
			if(p.getNombre().equals(peliculas.getNombre())) {
				bandera = true;
				break;
			}
		}
		if(bandera==false)
			peliculasDao.save(peliculas);
		return bandera;
	}
	//BUSCARXID
	@Transactional(readOnly = true)
	public Peliculas buscarXid(int idPeli) {
		Peliculas pelisEncontrar = peliculasDao.findById(idPeli).orElse(null);//buscamos por id, orElse (sino encuentra que regrese un null)
		return pelisEncontrar;
		
	}
	//EDITAR_X_ID
	@Transactional
	public boolean editarXid(Peliculas peliculas) {
		
		boolean bandera = false;
		for(Peliculas p: peliculasDao.findAll()) {
			if(p.getIdPeli().equals(peliculas.getIdPeli())) {
				peliculasDao.save(peliculas);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
	//ELIMINAR_X_ID
	@Transactional()
	public boolean eliminarXid(Integer idPeli) {
		boolean bandera = false;
		Peliculas peliculasEncontradas = peliculasDao.findById(idPeli).orElse(null);
		if(peliculasEncontradas != null) {
			peliculasDao.deleteById(idPeli);
			bandera = true;
		}
		
		return bandera;
	}
	//BUSCAR X NOMBRE
	@Transactional(readOnly = true)
	public Peliculas buscarXnombre(String nombre) {
		return peliculasDao.findByNombre(nombre);
	}
	//BUSCAR X GEERO CON QUERY NATIVA DAO
	@Transactional(readOnly = true)
	public List<Peliculas> buscarXGenero(String genero){
		return peliculasDao.buscarXGenero(genero);
	}
	
	//ELIMINAR X NOMBRE
	@Transactional()
	public boolean eliminarXnombre(String nombreP) {
		boolean bandera = false;
		Peliculas pelis = peliculasDao.findByNombre(nombreP);
		if(pelis !=null) {
		//if(pelis.getNombre().equals(nombreP)) {
			peliculasDao.deleteByNombre(nombreP);
			bandera = true;
		}
		return bandera;
	}
	
}
