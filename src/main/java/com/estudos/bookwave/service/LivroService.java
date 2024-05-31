package com.estudos.bookwave.service;

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

}
