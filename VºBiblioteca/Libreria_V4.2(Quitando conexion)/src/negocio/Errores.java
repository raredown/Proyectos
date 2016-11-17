package negocio;

import java.sql.SQLException;

import presentacion.ParcialLibreria;

public class Errores {
	//Errores sql
public Errores(SQLException e) {
	// TODO Auto-generated constructor stub
	System.out.println(e.getSQLState());
	if(e.getSQLState().equalsIgnoreCase("530")){
		ParcialLibreria.getLblAnomalias().setText("problema de insert or update");
	}
	if(e.getSQLState().equalsIgnoreCase("23000")){
		ParcialLibreria.getLblAnomalias().setText("repetido duplicado");
	}
	if(e.getSQLState().equalsIgnoreCase("S0022")){
		ParcialLibreria.getLblAnomalias().setText("problema con programacion");
	}
}
//errores normales
public Errores(Exception e) {
	// TODO Auto-generated constructor stub
	if(e.getClass().equals(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException.class))
		ParcialLibreria.getLblAnomalias().setText("Se produjo un error en la conexion");
}
}
