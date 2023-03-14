package Genericos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FuncionesGenericas {

	public static boolean documentoPattern_CI(String documento) {
		return documento.matches("[0-9]{0,1}[.][\\d]{3}[.][\\d]{3}[-][0-9]{1}") || documento.matches("[0-9]{7,8}");
	}

	/**
	 * Método para validar documentos de tipo cedula de identidad con digito
	 * verificador
	 *
	 * @param documento
	 * @return
	 */
	public static boolean verificadorCi(String ci) {

		try {
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
			if (documento.length() == 7) { // cedulas sin millón
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
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean validarMail(String mail) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mail);

        return mather.find();
	}
	
	
}
