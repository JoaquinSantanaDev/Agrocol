package Controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.Dao.ICasillaDao;
import com.Dao.IFormularioDao;
import com.Modelo.Casilla;
import com.Modelo.PlantillaCasilla;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;

public class CasillaController {

	public static boolean altaCasilla(Casilla casilla) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.altaCasilla(casilla) > -1 ? true : false;
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static List<UnidadesMedida> listarUnidadesMedidas() {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.listarUnidadesMedidas();
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return new ArrayList<>();
	}

	public static List<TipoInput> listarTiposInput() {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.listarTiposInput();
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return new ArrayList<>();
	}

	public static void altaTipoInput(TipoInput tipoInput) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			casillaDao.altaTipoInput(tipoInput);
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void altaUnidadMedida(UnidadesMedida unidad) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			casillaDao.altaUnidadMedida(unidad);
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static List<Casilla> listarTodas() {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.listarTodos();
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return new ArrayList<>();
	}

	public static Casilla obtenerCasilla(Integer codigo) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.buscarPorId(codigo);
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return new Casilla();
	}

	public static boolean bajaCasilla(int idCasilla) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.bajaCasilla(idCasilla);
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static List<PlantillaCasilla> ObtenerCasillaDePlantilla(Integer xIdPlantilla) {
		try {
			ICasillaDao casillaDao = (ICasillaDao) InitialContext
					.doLookup("AgroColServidor/CasilllaDaoimpl!com.Dao.ICasillaDao");
			return casillaDao.ObtenerCasillaDePlantilla(xIdPlantilla);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar altaCasilla", "Error", JOptionPane.ERROR_MESSAGE);
			return  new ArrayList<>();
		}
	}

}
