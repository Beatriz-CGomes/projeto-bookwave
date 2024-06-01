package com.estudos.bookwave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.estudos.bookwave.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	public List<Livro> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
