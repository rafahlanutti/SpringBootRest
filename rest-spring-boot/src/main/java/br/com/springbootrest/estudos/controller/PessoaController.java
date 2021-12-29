package br.com.springbootrest.estudos.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.core.data.vo.PessoaVO;
import br.com.springbootrest.estudos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint Pessoas")
@RestController
@RequestMapping("api/v1/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@Operation(summary = "Obter todas as pessoas")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<CollectionModel<PessoaVO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sort = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		var pageble = PageRequest.of(page, limit, Sort.by(sort, "nome"));
		Page<PessoaVO> pessoas = service.obterPorPaginacao(pageble);

		pessoas.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(pessoas));
	}

	@Operation(summary = "Obter pessoa pelo primeiro nome")
	@GetMapping(value = "/findByName/{firstName}", produces = { "application/json", "application/xml",
			"application/x-yaml" })
	public ResponseEntity<CollectionModel<PessoaVO>> findByName(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@PathVariable("firstName") String firstName) {

		var sort = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		var pageble = PageRequest.of(page, limit, Sort.by(sort, "nome"));
		Page<PessoaVO> pessoas = service.obterPeloNome(firstName, pageble);

		pessoas.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(pessoas));
	}

	@Operation(summary = "Obter Pessoa pelo ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PessoaVO findById(@PathVariable("id") Long id) {
		var pessoa = service.obterPorId(id);

		pessoa.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return pessoa;
	}

	@Operation(summary = "Criar nova Pessoa")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PessoaVO create(@RequestBody PessoaVO pessoa) {
		var created = service.criar(pessoa);
		created.add(linkTo(methodOn(PessoaController.class).findById(created.getKey())).withSelfRel());
		return created;

	}

	@Operation(summary = "Alterar Pessoa")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PessoaVO put(@RequestBody PessoaVO pessoa) {
		var updated = service.atualizar(pessoa);
		updated.add(linkTo(methodOn(PessoaController.class).findById(updated.getKey())).withSelfRel());
		return updated;

	}

	@Operation(summary = "Deletar Pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.ok().build();
	}

}
