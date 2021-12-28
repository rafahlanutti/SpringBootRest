package br.com.springbootrest.estudos.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.springbootrest.estudos.core.data.model.Pessoa;
import br.com.springbootrest.estudos.core.data.vo.PessoaVO;

public class DozeMapperTest {

	MockPessoa inputObject;

	@Before
	public void setUp() {
		inputObject = new MockPessoa();
	}

	@Test
	public void parseEntityToVOTest() {
		PessoaVO output = DozerMapper.parseObject(inputObject.mockEntity(), PessoaVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("Nome0", output.getNome());
		Assert.assertEquals("Sobrenome0", output.getSobrenome());
		Assert.assertEquals("Endereço0", output.getEndereco());
		Assert.assertEquals("Masculino", output.getGenero());
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<PessoaVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PessoaVO.class);
		PessoaVO outputZero = outputList.get(0);

		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("Nome0", outputZero.getNome());
		Assert.assertEquals("Sobrenome0", outputZero.getSobrenome());
		Assert.assertEquals("Endereço0", outputZero.getEndereco());
		Assert.assertEquals("Masculino", outputZero.getGenero());

		PessoaVO outputSeven = outputList.get(7);

		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("Nome7", outputSeven.getNome());
		Assert.assertEquals("Sobrenome7", outputSeven.getSobrenome());
		Assert.assertEquals("Endereço7", outputSeven.getEndereco());
		Assert.assertEquals("Feminino", outputSeven.getGenero());

		PessoaVO outputTwelve = outputList.get(12);

		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("Nome12", outputTwelve.getNome());
		Assert.assertEquals("Sobrenome12", outputTwelve.getSobrenome());
		Assert.assertEquals("Endereço12", outputTwelve.getEndereco());
		Assert.assertEquals("Masculino", outputTwelve.getGenero());
	}

	@Test
	public void parseVOToEntityTest() {
		Pessoa output = DozerMapper.parseObject(inputObject.mockVO(), Pessoa.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("Nome0", output.getNome());
		Assert.assertEquals("Sobrenome0", output.getSobrenome());
		Assert.assertEquals("Endereço0", output.getEndereco());
		Assert.assertEquals("Masculino", output.getGenero());
	}

	@Test
	public void parserVOListToEntityListTest() {
		List<Pessoa> pessoas = DozerMapper.parseListObjects(inputObject.mockVOList(), Pessoa.class);
		Pessoa pessoa = pessoas.get(5);

		Assert.assertEquals(Long.valueOf(5L), pessoa.getId());
		Assert.assertEquals("Nome5", pessoa.getNome());
		Assert.assertEquals("Sobrenome5", pessoa.getSobrenome());
		Assert.assertEquals("Endereço5", pessoa.getEndereco());
		Assert.assertEquals("Feminino", pessoa.getGenero());

		Pessoa outputSeven = pessoas.get(2);

		Assert.assertEquals(Long.valueOf(2L), outputSeven.getId());
		Assert.assertEquals("Nome2", outputSeven.getNome());
		Assert.assertEquals("Sobrenome2", outputSeven.getSobrenome());
		Assert.assertEquals("Endereço2", outputSeven.getEndereco());
		Assert.assertEquals("Masculino", outputSeven.getGenero());

		Pessoa outputTwelve = pessoas.get(12);

		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("Nome12", outputTwelve.getNome());
		Assert.assertEquals("Sobrenome12", outputTwelve.getSobrenome());
		Assert.assertEquals("Endereço12", outputTwelve.getEndereco());
		Assert.assertEquals("Masculino", outputTwelve.getGenero());
	}

}
