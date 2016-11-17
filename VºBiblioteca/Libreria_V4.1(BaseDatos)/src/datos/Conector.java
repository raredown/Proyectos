package datos;

import java.sql.Connection;

import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class Conector {
	
	private String usr;
	private String psw;
	private Connection con;

	
	public Conector(String usuario, String pass) {
		usr = usuario;
		psw = pass;
	}//conector
	
	public Connection conectar(){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(usr);
		dataSource.setPassword(psw);
		//nombre base de datos
		dataSource.setDatabaseName("libreria");
		// nombre de servidor
		dataSource.setServerName("localhost");
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			con = null;
		}
		return con;
	}//conectar
	
	
	
}
