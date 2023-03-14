package Pruebas;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.codec.digest.DigestUtils;

import com.Dao.IUsuario;

import Controllers.RolController;

public class Principal {
	
	public static void main(String[] args) {


		System.out.print(validarMail("ceoas--wwdk"));
		System.out.print(validarMail("ceo_v@hotmail.com"));
		System.out.print(validarMail("ceo_v@h"));
		System.out.print(validarMail("@hotmail.com"));
		System.out.print(validarMail("ceo_v@.com"));
		
		
	}
	
	
	public static boolean validarMail(String mail) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mail);

        return mather.find();
	}

}
