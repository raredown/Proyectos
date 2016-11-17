package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Conector {


	public Conector() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String usr = "root";
		String pwd = "";
		//String url = "jdbc:mysql://localhost/phpmyadmin/libreria";
		//String driver= "com.mysql.jdbc.Driver";

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(usr);
		dataSource.setPassword(pwd);
		dataSource.setDatabaseName("libreria");
		dataSource.setServerName("localhost");
		
		Connection conexion = dataSource.getConnection();
		

		try {
			/*
			Class.forName(driver);
			con = DriverManager.getConnection(usr, pwd, url);*/
			
			String sql = "SELECT * FROM libro l WHERE l.titulo = \"Otro Titulo\"";

			pstm = conexion.prepareStatement(sql);

			rs = pstm.executeQuery();

			while (rs.next()) {
				
				System.out.println(rs.getString("titulo"));
				System.out.println(rs.getString("autor"));
				System.out.println(rs.getString("tema"));
				System.out.println(rs.getString("paginas"));
				if (rs.getInt("cartone")==1) System.out.println("Cartone");
				else System.out.println(".");
				if (rs.getInt("rustica")==1) System.out.println("Rustica");
				else System.out.println(".");
				if (rs.getInt("tdura")==1) System.out.println("Tapa Dura");
				else System.out.println(".");
				System.out.println(rs.getString("formato"));
			}
		/*	
			sql= "";
			sql+= "INSERT INTO libro (titulo, autor, tema, paginas, cartone, rustica, tdura, formato)";
			sql+= "VALUES (?,?,?,?,?,?,?,?) ";
			pstm = conexion.prepareStatement(sql);
			pstm.setString(1, "Otrdddh");
			pstm.setString(2, "Otro Autoraa");
			pstm.setString(3, "Otro TemaAA");
			pstm.setString(4, "100");
			pstm.setInt(5, 0);
			pstm.setInt(6, 1);
			pstm.setInt(7, 0);
			pstm.setString(8, "Novedad");
			int rdto = pstm.executeUpdate();
			if (rdto == 1) {
				System.out.println("1 fila insertada correctamente");
			} else {
				throw new RuntimeException("No se puede insertar la fila");
			}
			*/
			
			sql ="";
			sql+= "DELETE FROM libro WHERE titulo = ?";
			pstm = conexion.prepareStatement(sql);
			pstm.setString(1, "El Titulo");
			int x = pstm.executeUpdate();
			if (x==1) System.out.println("Borrado");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			
			try {
				
				if (rs!=null) rs.close();
				if (pstm!=null) pstm.close();
				if (con!=null) con.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
