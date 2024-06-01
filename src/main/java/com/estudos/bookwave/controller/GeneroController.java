package com.estudos.bookwave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.bookwave.model.Genero;
import com.estudos.bookwave.service.GeneroService;

@RestController
@RequestMapping("/generos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	@GetMapping
	public ResponseEntity<Page<Genero>> findAll(
			@PageableDefault(size = 10, page = 0, sort = { "genero" }) Pageable page) {
		Page<Genero> generoPage = generoService.findAll(page);
		return ResponseEntity.ok(generoPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Genero> findById(@PathVariable Long id) {
		return generoService.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Genero>> findAllByGeneroContainingIgnoreCase(@PathVariable String genero) {
		return ResponseEntity.ok(generoService.findAllByGeneroContainingIgnoreCase(genero));
	}
}