package br.com.springbootrest.estudos.calculo;

import org.springframework.stereotype.Service;

@Service
public class Math implements Calculos {

	public double somar(double a, double b) {
		return a + b;
	}

	public double subtrair(double a, double b) {
		return a - b;
	}

	@Override
	public Double dividir(double dividendo, double divisor) {
		return dividendo / divisor;
	}

	@Override
	public Double multiplicar(double multiplicando, double multiplicador) {
		return multiplicando * multiplicador;
	}

}
