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

import br.com.springbootrest.estudos.core.data.vo.PessoaVO;
import br.com.springbootrest.estudos.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint Pessoas", tags = { "API Pessoa"})
@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@ApiOperation(value = "Obter todas as pessoas")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PessoaVO> findAll() {
		var pessoas = service.obterTodos();

		pessoas.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return pessoas;
	}
	@ApiOperation(value = "Obter Pessoa pelo ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PessoaVO findById(@PathVariable("id") Long id) {
		var pessoa = service.obterPorId(id);

		pessoa.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return pessoa;
	}

	@ApiOperation(value = "Criar nova Pessoa")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PessoaVO create(@RequestBody PessoaVO pessoa) {
		var created = service.criar(pessoa);
		created.add(linkTo(methodOn(PessoaController.class).findById(created.getKey())).withSelfRel());
		return created;

	}
	@ApiOperation(value = "Alterar Pessoa")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PessoaVO put(@RequestBody PessoaVO pessoa) {
		var updated = service.atualizar(pessoa);
		updated.add(linkTo(methodOn(PessoaController.class).findById(updated.getKey())).withSelfRel());
		return updated;

	}
	@ApiOperation(value = "Deletar Pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
