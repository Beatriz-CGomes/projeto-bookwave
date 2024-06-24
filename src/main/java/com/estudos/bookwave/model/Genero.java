package com.estudos.bookwave.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_generos")
public class Genero implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "esse atributo é obrigatorio")
	@Size(min = 5, max = 100, message = "esse atributo tem como 5 e 100 caracteres")
	private String genero;
	
	
	//associação entre tabelas
	@OneToMany(mappedBy = "genero", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("genero")
	private List<Livro> livro; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	public List<Livro> getLivro() {
		return livro;
	}
	
	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	} 
	
}
