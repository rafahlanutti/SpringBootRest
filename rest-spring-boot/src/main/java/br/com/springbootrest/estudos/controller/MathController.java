package br.com.springbootrest.estudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springbootrest.estudos.core.calculo.Calculos;
import br.com.springbootrest.estudos.core.calculo.DecimalFormat;
import br.com.springbootrest.estudos.exception.UnsuportedMathOperationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoint Calculadora")
@RestController
@RequestMapping("api/v1/pessoa")
public class MathController {

	@Autowired
	private Calculos calculadora;

	@Operation(summary = "Soma dois números A + B = resultado")
	@GetMapping(value = "/soma/{a}/{b}")
	public Double soma(@PathVariable("a") String a, @PathVariable("b") String b)
			throws UnsuportedMathOperationException {

		if (isParametrosNumericosValidos(a, b)) {
			return calculadora.somar(DecimalFormat.format(a), DecimalFormat.format(b));
		}
		throw new UnsuportedMathOperationException("Please set a numeric value!");
	}

	@Operation(summary = "Subtrai dois números A - B = resultado")
	@GetMapping(value = "/subtracao/{a}/{b}")
	public Double subtracao(@PathVariable("a") String a, @PathVariable("b") String b)
			throws UnsuportedMathOperationException {

		if (isParametrosNumericosValidos(a, b)) {
			return calculadora.subtrair(DecimalFormat.format(a), DecimalFormat.format(b));
		}
		throw new UnsuportedMathOperationException("Please set a numeric value!");
	}

	@Operation(summary = "Divide dois números dividendo / divisor = resultado")
	@GetMapping(value = "/divisao/{dividendo}/{divisor}")
	public Double divisao(@PathVariable("dividendo") String dividendo, @PathVariable("divisor") String divisor)
			throws UnsuportedMathOperationException {

		if (isParametrosNumericosValidos(dividendo, divisor)) {
			return calculadora.dividir(DecimalFormat.format(dividendo), DecimalFormat.format(divisor));
		}
		throw new UnsuportedMathOperationException("Please set a numeric value!");
	}

	@Operation(summary = "Multiplica dois números multiplicando * multiplicador = resultado")
	@GetMapping(value = "/multiplicacao/{multiplicando}/{multiplicador}")
	public Double multiplicacao(@PathVariable("multiplicando") String multiplicando,
			@PathVariable("multiplicador") String multiplicador) throws UnsuportedMathOperationException {

		if (isParametrosNumericosValidos(multiplicando, multiplicador)) {
			return calculadora.multiplicar(DecimalFormat.format(multiplicando), DecimalFormat.format(multiplicador));
		}
		throw new UnsuportedMathOperationException("Please set a numeric value!");
	}

	private boolean isParametrosNumericosValidos(String a, String b) {
		return DecimalFormat.isNumeric(a) && DecimalFormat.isNumeric(b);
	}

}
