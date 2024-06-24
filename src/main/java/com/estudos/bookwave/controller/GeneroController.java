package com.estudos.bookwave.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.estudos.bookwave.model.Genero;
import com.estudos.bookwave.service.GeneroService;

import jakarta.validation.Valid;

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

	@PostMapping
	public ResponseEntity<Genero> post(@Valid @RequestBody Genero genero) {
		genero = generoService.post(genero);
		return ResponseEntity.status(HttpStatus.CREATED).body(genero);
	}

	@PutMapping("{id}")
	public ResponseEntity<Genero> put(@Valid @RequestBody Genero genero, @PathVariable Long id) {
		Genero novoGenero = generoService.put(genero, id);
		if (novoGenero != null) {
			return ResponseEntity.status(HttpStatus.OK).body(novoGenero);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Optional<Genero> genero = generoService.findById(id);
		if (genero.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			generoService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}