package com.estudos.bookwave.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudos.bookwave.model.Livro;
import com.estudos.bookwave.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public Page<Livro> findAll(Pageable page) {
		return livroRepository.findAll(page);
	}

	public Optional<Livro> findById(Long id) {
		return livroRepository.findById(id);
	}

	public List<Livro> findAllByTituloContainingIgonreCase(String titulo) {
		return livroRepository.findAllByTituloContainingIgnoreCase(titulo);
	}

	public List<Livro> findAllByAutorContainingIgnoreCase(String autor) {
		return livroRepository.findAllByAutorContainingIgnoreCase(autor);
	}

	public Livro post(Livro livro) {
		return livroRepository.save(livro);
	}

	public Livro put(Long id, Livro livro) {
		Livro obj = livroRepository.getReferenceById(id);
		putUpdate(livro, obj);
		return livroRepository.save(obj);
	}
	
	private void putUpdate(Livro livro, Livro obj) {
		livro.setTitulo(obj.getTitulo());
		livro.setAutor(obj.getAutor());
		livro.setSinopse(obj.getSinopse());
	}

	public void delete(Long id) {
		livroRepository.deleteById(id);
	}
}
