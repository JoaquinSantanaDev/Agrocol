package com.FuncionesGenericas;

public abstract class FuncionesGenericas {

	public static boolean documentoPattern_CI(String documento) {
		return documento.matches("[0-9]{0,1}[.][\\d]{3}[.][\\d]{3}[-][0-9]{1}") || documento.matches("[0-9]{7,8}");
	}

	public static boolean verificadorCi(String ci) {
		String documento = ci;
		if (ci.contains(".") || ci.contains("-")) {
			documento = ci.replaceAll("[ .]", "").replaceAll("[ -]", "");
		}
		if (documento.length() != 7 && documento.length() != 8) {
			return false;
		} else {
			try {
				Integer.parseInt(documento);
			} catch (NumberFormatException e) {
				return false;
			}
		}

		int digVerificador = Integer.valueOf(documento.substring(documento.length() - 1));
		int[] factores;
		if (documento.length() == 7) { // cedulas sin mill�n
			factores = new int[] { 9, 8, 7, 6, 3, 4 };
		} else {
			factores = new int[] { 2, 9, 8, 7, 6, 3, 4 };
		}

		int suma = 0;
		for (int i = 0; i < documento.length() - 1; i++) {
			char digitoChar = documento.charAt(i);
			int digito = Integer.valueOf(String.valueOf(digitoChar));
			suma += digito * factores[i];
		}

		int resto = suma % 10;
		int checkdigit = 10 - resto;

		if (checkdigit == 10) {
			return (digVerificador == 0);
		} else {
			return (checkdigit == digVerificador);
		}
	}
	
	

}
