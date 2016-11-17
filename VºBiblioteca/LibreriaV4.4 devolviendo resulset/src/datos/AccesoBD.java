package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import negocio.Mensajes;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;



public class AccesoBD {


	protected Connection con;
	//"precompila" y la guarda en condiciones de ser ejecutada inmediatamente, sin necesidad de analizarla
	protected PreparedStatement pstm;
	
	protected ResultSet rs;



	public void abrirConexion(){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		//nombre base de datos
		dataSource.setDatabaseName("libreria");
		// nombre de servidor
		dataSource.setServerName("localhost");
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			new Mensajes(e);
		}
	}//abrirConexion

	public ResultSet ejecutarConsulta(String sql){
//		abrirConexion();
		try {
			if(con != null){
				pstm =  con.prepareStatement(sql);
				rs = pstm.executeQuery();
			}
		} catch (SQLException e) {new Mensajes(e);}
		
		return rs;
	}//ejecutarConsulta()
	
	public boolean ejecutarUpdate(String sql){
		boolean bandera = false;
		abrirConexion();
		if(con != null){
			try {
				pstm = con.prepareStatement(sql);
				if(pstm.executeUpdate() == 1){
					bandera = true;
				}//if
			} catch (SQLException e) {new Mensajes(e);}
			finally{
				cerrarConexion();
			}
			
		}//if
		return bandera;
	}//ejecutarUpdate()
	
	public void cerrarConexion(){
		try {
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		} catch (SQLException e) {new Mensajes(e);}

	}

}
