package com.estudos.bookwave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.estudos.bookwave.model.Genero;
import com.estudos.bookwave.repository.GeneroRepository;

@RestController
@RequestMapping("/generos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneroController {

	@Autowired
	private GeneroRepository generoRepository;
	
	
	@GetMapping
	public ResponseEntity<Page<Genero>> findAll(@PageableDefault(size = 10, page = 0, sort = {"titulo"}) Pageable page){
		   Page<Genero> generosPage = generoRepository.findAll(page);
	        return ResponseEntity.ok(generosPage);
	}
}
 