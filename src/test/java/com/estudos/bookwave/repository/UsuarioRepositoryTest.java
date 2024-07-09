package com.estudos.bookwave.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.estudos.bookwave.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository ur;

	@BeforeAll
	void start() {
		ur.deleteAll();
		ur.save(new Usuario(0L, "João da Silva", "joão@gmail.com", "123456789"));
		ur.save(new Usuario(0L, "Maria Helena Hoffman", "mariaHelen@gmail.com", "123456789"));
		ur.save(new Usuario(0L, "Carlos Albuquerque Silva", "carlos@gmail.com", "123456789"));
		ur.save(new Usuario(0L, "Joana Silva Oliveira", "jooh@gmail.com", "123456789"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = ur.findByUsuario("joão@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("joão@gmail.com"));
	}

	@Test
	@DisplayName("retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaUsuario = ur.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaUsuario.size());
		assertTrue(listaUsuario.get(0).getNome().equals("João da Silva"));
		assertTrue(listaUsuario.get(0).getNome().equals("Carlos Albuquerque Silva"));
		assertTrue(listaUsuario.get(0).getNome().equals("Joana Silva Oliveira"));
	}

	@AfterAll
	public void end() {
		ur.deleteAll();

	}

}
