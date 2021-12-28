package br.com.springbootrest.estudos.core.calculo;

import org.apache.logging.log4j.util.Strings;

public class DecimalFormat {

	private DecimalFormat() {
		super();
	}

	public static boolean isNumeric(String number) {
		if (Strings.isEmpty(number)) {
			return false;
		}
		number = number.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static double format(String numberString) {

		return Double.parseDouble(numberString);
	}
}
