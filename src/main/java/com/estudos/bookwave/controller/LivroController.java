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

import com.estudos.bookwave.model.Livro;
import com.estudos.bookwave.service.LivroService;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public ResponseEntity<Page<Livro>> findAll(
			@PageableDefault(size = 10, page = 0, sort = { "titulo" }) Pageable page) {
		Page<Livro> livroPage = livroService.findAll(page);
		return ResponseEntity.ok(livroPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		return livroService.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Livro>> findAllByTituloContainingIgonreCase(@PathVariable String titulo) {
		return ResponseEntity.ok(livroService.findAllByTituloContainingIgonreCase(titulo));
	}
}
