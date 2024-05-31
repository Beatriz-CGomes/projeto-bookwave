package com.estudos.bookwave.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "esse atributo é obrigatorio")
	private String nome;

	@NotNull(message = "esse atributo é obrigatorio")
	@Email(message = "O atributo usuario deve ter um email valido")
	private String usuario;

	@NotBlank(message = "esse atributo é obrigatorio")
	@Size(min = 8, message = "o minimo de caractere para senha é 8")
	private String senha;

	// associação entre tabelas
	@OneToMany
	private List<Livro> livro;

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Livro> getLivro() {
		return livro;
	}
	
	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}
}
