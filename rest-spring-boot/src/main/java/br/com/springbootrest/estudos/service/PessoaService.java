package br.com.springbootrest.estudos.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.springbootrest.estudos.model.Pessoa;

@Service
public class PessoaService {

	private final AtomicLong counter = new AtomicLong();

	public Pessoa obterPorId(String id) {
		return criarPessoaMock();
	}

	private Pessoa criarPessoaMock() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(counter.getAndIncrement());
		pessoa.setEndereco("Heitor de Moura Estev�o - Teres�polis - RJ");
		pessoa.setGenero("Masculino");
		pessoa.setNome("Rafael");
		pessoa.setSobrenome("Lanutti");
		return pessoa;
	}

	public List<Pessoa> obterTodos() {

		return Arrays.asList(criarPessoaMock(), criarPessoaMock(), criarPessoaMock());
	}

	public Pessoa criar(Pessoa pessoa) {
		/**
		 * Logica de programação para criar
		 */
		return pessoa;
	}

	public Pessoa atualizar(Pessoa pessoa) {
		/**
		 * Logica de programação para atualizar
		 */
		return pessoa;
	}
	
	public void deletar(String id) {
		/**
		 * Logica de programação para deletar
		 */
	}
	

}
