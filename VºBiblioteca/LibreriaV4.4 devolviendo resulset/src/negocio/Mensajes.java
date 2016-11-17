package negocio;



import java.awt.Color;
import java.sql.SQLException;

import vista.ParcialLibreria;

	public class Mensajes {
		public static final int MEN_ALTA_CORRECTA=1;
		public static final int MEN_BAJA_CORRECTA=2;
		public static final int MEN_MODIFICAR_CORRECTA=3;
		public static final int MEN_COMPROBAR_DATOS=4;
		public static final int MEN_ELIMINAR_DATOS=5;
		public static final int MEN_ELIMINAR_DATOS_CORRECTO=6;
		
		//mensajes correctos
		public Mensajes(int accion) {
			ParcialLibreria.getLblAnomalias().setForeground(new Color(12, 94, 47));
			switch (accion) {
			case 1:
				ParcialLibreria.getLblAnomalias().setText("Se ha insertado una fila");
				break;
			case 2:
				ParcialLibreria.getLblAnomalias().setText("Se ha borrado una fila");
				break;
			case 3:
				ParcialLibreria.getLblAnomalias().setText("Modificado correctamente");
				break;
			case 4:
				ParcialLibreria.getLblAnomalias().setText("Faltan datos por rellenar");
				break;
			case 5:
				ParcialLibreria.getLblAnomalias().setText("No hemos podido borrar los datos, hay algun problema en la clase Estanteria");
				break;
			case 6:
				ParcialLibreria.getLblAnomalias().setText("Borrado Correctamente");
				break;
			}	
			
		}
		//viene de java.sql.SQLException.class
		//constructor que recoge los errores de SQL
		public void sql(SQLException e) {
			ParcialLibreria.getLblAnomalias().setForeground(Color.RED);
			if(e.getSQLState().equalsIgnoreCase("23000")){
				ParcialLibreria.getLblAnomalias().setText("El libro ya está insertado");
			}
			if(e.getSQLState().equalsIgnoreCase("S0022")){
				ParcialLibreria.getLblAnomalias().setText("Problemas con columnas");
			}
		}
		
		//este recoge los errores normales
		public Mensajes(Exception e) {
			if(e.getClass().equals(java.sql.SQLException.class)){
				System.out.println("coreecto");
			}
			if(e.getClass().equals(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException.class)){
				ParcialLibreria.getLblAnomalias().setText("Problemas en la conexión de la base de datos");
			}
		}
	}
	
	