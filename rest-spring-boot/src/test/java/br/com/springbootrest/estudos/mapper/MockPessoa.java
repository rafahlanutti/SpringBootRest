package br.com.springbootrest.estudos.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.springbootrest.estudos.data.model.Pessoa;
import br.com.springbootrest.estudos.data.vo.PessoaVO;

public class MockPessoa {

	public Pessoa mockEntity() {
		return mockEntity(0);
	}

	public PessoaVO mockVO() {
		return mockVO(0);
	}

	public List<Pessoa> mockEntityList() {
		List<Pessoa> Pessoas = new ArrayList<Pessoa>();
		for (int i = 0; i < 14; i++) {
			Pessoas.add(mockEntity(i));
		}
		return Pessoas;
	}

	public List<PessoaVO> mockVOList() {
		List<PessoaVO> Pessoas = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			Pessoas.add(mockVO(i));
		}
		return Pessoas;
	}

	private Pessoa mockEntity(Integer number) {
		Pessoa Pessoa = new Pessoa();
		Pessoa.setEndereco("Endereço" + number);
		Pessoa.setNome("Nome" + number);
		Pessoa.setGenero(((number % 2) == 0) ? "Masculino" : "Feminino");
		Pessoa.setId(number.longValue());
		Pessoa.setSobrenome("Sobrenome" + number);
		return Pessoa;
	}

	private PessoaVO mockVO(Integer number) {
		PessoaVO Pessoa = new PessoaVO();
		Pessoa.setEndereco("Endereço" + number);
		Pessoa.setNome("Nome" + number);
		Pessoa.setGenero(((number % 2) == 0) ? "Masculino" : "Feminino");
		Pessoa.setId(number.longValue());
		Pessoa.setSobrenome("Sobrenome" + number);
		return Pessoa;
	}

}