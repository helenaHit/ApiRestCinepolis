package com.mx.ApiRestCinepolis.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name = "PELICULAS")
@NoArgsConstructor //TIENE EL CONTRUCTOR VACIO
@AllArgsConstructor //CONSTRUCTOR CON PARAMETROS
@Data //TIENE toString , getters, setters
public class Peliculas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//esto solo se usa cuando tiene auto incrementable
	@Column(name = "ID_PELI", columnDefinition = "NUMBER",nullable=false)
	private Integer idPeli;
	
	@Column(name = "NOMBRE", columnDefinition = "VARCHAR2(80)",nullable = false)
	private String nombre;
	;
	
	@Column(name = "GENERO", columnDefinition = "VARCHAR2(80)",nullable = false)
	private String genero;

	@Column(name ="PRECIO", columnDefinition = "NUMBER", nullable=false)
	private Float precio;
	

}
