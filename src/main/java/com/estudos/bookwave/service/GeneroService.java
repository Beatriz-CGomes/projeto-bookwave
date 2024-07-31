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

	// METADO PARA BUSCAR POR GENERO
	public List<Genero> findByGeneroNome(String genero) {
		return generoRepository.findAllByGeneroContainingIgnoreCase(genero);
	}

	// METADO PARA POSTAR GENERO
	public Genero post(Genero genero) {
		return generoRepository.save(genero);
	}

	// METADO PARA ATUALIZAR GENERO
	 public Genero put(Genero genero, Long id) {
	        return generoRepository.findById(id).map(existingGenero -> {
	            updatePut(existingGenero, genero);
	            return generoRepository.save(existingGenero);
	        }).orElse(null);
	    }

	    private void updatePut(Genero existingGenero, Genero newGenero) {
	        existingGenero.setGenero(newGenero.getGenero());
	    }

	// METADO PARA DELETAR GENERO
	public void delete(Long id) {
		generoRepository.deleteById(id);
	}

}
