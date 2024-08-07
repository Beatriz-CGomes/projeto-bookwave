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

import com.estudos.bookwave.model.Livro;
import com.estudos.bookwave.repository.GeneroRepository;
import com.estudos.bookwave.repository.LivroRepository;
import com.estudos.bookwave.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private LivroRepository livroRepository;

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
	public ResponseEntity<List<Livro>> findTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(livroService.findAllPorTitulo(titulo));
	}

	@GetMapping("/autor/{autor}")
	public ResponseEntity<List<Livro>> findAutor(@PathVariable String autor) {
		return ResponseEntity.ok(livroService.findAllPorAutor(autor));
	}

	@PostMapping
	public ResponseEntity<Livro> post(@Valid @RequestBody Livro livro) {
		if (generoRepository.existsById(livro.getGenero().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(livroService.post(livro));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> put(@Valid @RequestBody Livro livro, @PathVariable Long id) {
		Livro novoLivro = livroService.put(id, livro);
		if (livroRepository.existsById(livro.getId())) {
			if (generoRepository.existsById(livro.getGenero().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(novoLivro);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Livro> livros = livroService.findById(id);
		if (livros.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
