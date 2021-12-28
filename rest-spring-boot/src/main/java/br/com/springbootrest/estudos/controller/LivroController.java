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

@RestController
@RequestMapping("api/v1/livro")
public class LivroController {

	@Autowired
	private LivroService service;

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<LivroVO> findAll() {
		var pessoas = service.obterTodos();

		pessoas.forEach(p -> p.add(linkTo(methodOn(LivroController.class).findById(p.getKey())).withSelfRel()));
		return pessoas;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public LivroVO findById(@PathVariable("id") Long id) {
		var pessoa = service.obterPorId(id);

		pessoa.add(linkTo(methodOn(LivroController.class).findById(id)).withSelfRel());
		return pessoa;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public LivroVO create(@RequestBody LivroVO pessoa) {
		var created = service.criar(pessoa);
		created.add(linkTo(methodOn(LivroController.class).findById(created.getKey())).withSelfRel());
		return created;

	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public LivroVO put(@RequestBody LivroVO pessoa) {
		var updated = service.atualizar(pessoa);
		updated.add(linkTo(methodOn(LivroController.class).findById(updated.getKey())).withSelfRel());
		return updated;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
