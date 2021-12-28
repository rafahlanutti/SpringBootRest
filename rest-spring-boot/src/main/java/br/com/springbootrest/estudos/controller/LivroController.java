package br.com.springbootrest.estudos.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.core.data.vo.LivroVO;
import br.com.springbootrest.estudos.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint Livros", tags = { "API livros" })
@RestController
@RequestMapping("api/v1/livro")
public class LivroController {

	@Autowired
	private LivroService service;

	@ApiOperation(value = "Obter todos os livros")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<LivroVO> findAll() {
		var livros = service.obterTodos();

		livros.forEach(p -> p.add(linkTo(methodOn(LivroController.class).findById(p.getKey())).withSelfRel()));
		return livros;
	}

	@ApiOperation(value = "Obter livro pelo ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public LivroVO findById(@PathVariable("id") Long id) {
		var livro = service.obterPorId(id);

		livro.add(linkTo(methodOn(LivroController.class).findById(id)).withSelfRel());
		return livro;
	}

	@ApiOperation(value = "Criar nova livros")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public LivroVO create(@RequestBody LivroVO livro) {
		var created = service.criar(livro);
		created.add(linkTo(methodOn(LivroController.class).findById(created.getKey())).withSelfRel());
		return created;

	}

	@ApiOperation(value = "Alterar livro")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public LivroVO put(@RequestBody LivroVO livro) {
		var updated = service.atualizar(livro);
		updated.add(linkTo(methodOn(LivroController.class).findById(updated.getKey())).withSelfRel());
		return updated;

	}

	@ApiOperation(value = "Deletar livro")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
