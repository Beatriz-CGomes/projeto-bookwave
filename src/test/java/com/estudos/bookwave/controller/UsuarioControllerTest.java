package com.estudos.bookwave.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estudos.bookwave.model.Usuario;
import com.estudos.bookwave.repository.UsuarioRepository;
import com.estudos.bookwave.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void star() {
		usuarioRepository.deleteAll();
		usuarioService.cadastrarUsuario(new Usuario(0L, "root", "root@root.com", "rootroot"));
	}

	@Test
	@DisplayName("Cria um Usuario")
	public void deveCriarUmUsuario() {
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Paulo Antunes", "paulo_antunes@gmail.com", "123456789"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());

	}

	@Test
	@DisplayName("Testando se a aplicação permite usuarios duplicados")
	public void naoDeveDuplicarUsuario() {
		usuarioService.cadastrarUsuario(new Usuario(0L, "Maria da Silva", "maria@gmail.com", "123456789"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Maria da Silva", "maria@gmail.com", "123456789"));
		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);
		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

	}

	@Test
	@DisplayName("Atualizar um Usuario")
	public void atualizarUmUsuario() {
		Optional<Usuario> usuarioCadastrado = usuarioService
				.cadastrarUsuario(new Usuario(0L, "Juliana Andrews", "juliana_andrews@gmail.com", "123456789"));

		Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(), "Juliana Andrews Ramos",
				"juliana_ramos@gmail.com", "123456789");
		HttpEntity<Usuario> corpoRequicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequicao, Usuario.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		assertEquals(corpoRequicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}

	@Test
	@DisplayName("Listando todos os Usuários")
	public void deveMostarTodos() {
		usuarioService.cadastrarUsuario(new Usuario(0L, "Sabrina Sanchez", "sabrina_sanchez@gmail.com", "123456789"));
		usuarioService.cadastrarUsuario(new Usuario(0L, "Ricardo Santos", "ricardo_santos@gmail.com", "123456789"));
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

}
