package com.estudos.bookwave.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_livros")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "esse atributo é obrigatorio")
	@Size(min = 5, max = 100, message = "esse atributo tem como 5 e 100 caracteres")
	private String titulo;

	@NotBlank(message = "esse atributo é obrigatorio")
	@Size(min = 5, max = 100, message = "esse atributo tem como 5 e 100 caracteres")
	private String autor;

	@NotBlank(message = "esse atributo é obrigatorio")
	@Size(min = 20, max = 1000, message = "esse atributo tem como 20 e 1000 caracteres")
	private String sinopse;

	@UpdateTimestamp
	private LocalDateTime data;

	@NotNull
	private Integer paginas;

	@NotNull
	private Integer quantidade;

	@Size(min = 20, max = 5000, message = "esse atributo tem como 20 e 5000 caracteres")
	private String foto;

	
	// associação entre tabelas
	@ManyToOne
	@JsonIgnoreProperties("livro")
	private Genero genero; 
	
	@ManyToOne
	@JsonIgnoreProperties("livro")
	private Usuario usuario;

	// getterd and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	} 

}
