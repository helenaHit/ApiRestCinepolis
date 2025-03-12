package com.mx.ApiRestCinepolis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.ApiRestCinepolis.model.Peliculas;

public interface PeliculasDao extends JpaRepository<Peliculas, Integer>{

	
	
	//PARA BUSCAR X NOMBRE USAMOS EL METODO DE RESORTE USANDO FindById
	public Peliculas findByNombre (String nombre);
	public void deleteByNombre (String nombreP);
	
	//buscar por genero usando query nativa
	@Query(value ="SELECT * FROM PELICULAS WHERE GENERO =:GENERO", nativeQuery = true)
	public List<Peliculas> buscarXGenero(@Param ("GENERO")String genero);
	
/*
DELETE FROM PELICULAS WHERE NOMBRE = 'SHERK';*/
}
