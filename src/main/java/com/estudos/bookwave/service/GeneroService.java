package com.estudos.bookwave.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudos.bookwave.model.Genero;
import com.estudos.bookwave.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	// METADO PARA PEGAR TODOS OS GENEROS
	public Page<Genero> findAll(Pageable page) {
		return generoRepository.findAll(page);
	}

	// METADO PARA PEGAR GENERO POR ID
	public Optional<Genero> findById(Long id) {
		return generoRepository.findById(id);
	}
	
	//METADO PARA BUSCAR POR GENERO
	public List<Genero> findAllByGeneroContainingIgnoreCase(String genero){
		return generoRepository.findAllByGeneroContainingIgnoreCase(genero);
	}
	
	
	//METADO PARA POSTAR GENERO
	
	
	
	//METADO PARA ATUALIZAR GENERO
	
	
	
	//METADO PARA DELETAR GENERO

}
