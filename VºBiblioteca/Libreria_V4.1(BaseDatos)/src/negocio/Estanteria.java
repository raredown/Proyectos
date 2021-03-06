package negocio;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datos.Conector;

public class Estanteria implements Serializable{


	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;
	private Conector conector;
	private String tituloAntiguo;
	public Estanteria() {
		conector = new Conector("root", "");
	}// Constructor

	private void cerrarConexiones(){
		try {
			if(rs!=null)
				rs.close();
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		} catch (SQLException e) {}

	}

	// Con este metodo le vamos a dar de alta en los ficheros a los nuevos libros
	public void altas(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado) {
		con = conector.conectar();
		if(con!=null){
			String sql="";
			sql += "INSERT INTO estanteria (strtitulo,strautor,strtema,strformato,strestado,numpaginas) ";
			sql += "VALUES(?,?,?,?,?,?) ";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, titulo);
				pstm.setString(2, autor);
				pstm.setString(3, tema);
				pstm.setString(4, prepararFormato(formato));
				pstm.setString(5, estado);
				pstm.setInt(6, Integer.parseInt(numPaginas));
				int resul = pstm.executeUpdate();
				if(resul == 1)
					System.out.println("Una insertada");
				else
					System.out.println("no se a insertado");
			} catch (SQLException e) {System.out.println("tenemos problemas con lso insert");}
			finally{
				cerrarConexiones();
			}
		}
	}//altas


	private String prepararFormato(String[] formato){
		String cadena="";
		for(int i =0;i<formato.length;i++){
			if(i==0)
				cadena += formato[i];
			else
				cadena += ","+formato[i];
		}
		return cadena;
	}

	private String[] arrayFormato(String cadena){
		return cadena.split(",");
	}

	// BUscamos el fichero
	public Libro buscarLibro(String titulo) {
		boolean bandera = false;
		Libro librito = null;
		con = conector.conectar();
		if(con!=null){
			String sql = "SELECT * FROM estanteria l WHERE strtitulo = \""+titulo+"\"";
			try {
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();
				if(rs.next()){
					if(rs.getString("strtitulo").equals(titulo)){
						titulo = rs.getString("strtitulo");
						String autor = rs.getString("strautor");
						String tema = rs.getString("strtema");
						String []formato = arrayFormato(rs.getString("strformato"));
						String estado = rs.getString("strestado");
						String numPaginas = Integer.toString(rs.getInt("numpaginas"));
						librito = new Libro(titulo, autor, tema, numPaginas, formato, estado);
						bandera = true;
					}
				}//while
			} catch (SQLException e) {bandera = false;}

		}
		if(bandera)
			return librito;
		else
			return null;
	}//buscarLibro


	// Ddevuelve un objeto libro
	public Libro cargarDatos(String titulo){
		Libro librito = buscarLibro(titulo);
		tituloAntiguo = librito.getTitulo();
		return librito;	
	}//cargarDatos

	// El metodose esr borra un registro
	public boolean borrarLineaRegistro(String titulo){
		boolean bandera = false;
		con = conector.conectar();
		try {
			if(con!=null){
				String sql = "DELETE FROM estanteria WHERE strtitulo = ?";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, titulo);
				int result = pstm.executeUpdate();
				if(result==1)
					bandera = true;
				else
					bandera = false;
			}

		} catch (Exception e) {}
		finally{
			cerrarConexiones();
		}
		return bandera;
	}
	//este metodo actualiza 

	public void modificar(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado){
		con = conector.conectar();
		if(con!=null){
			try {
				String sql = "UPDATE estanteria SET strtitulo = ?, strautor = ?, strtema = ?, strformato = ?, strestado = ?, numpaginas = ? WHERE strtitulo = '"+tituloAntiguo+"'";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, titulo);
				pstm.setString(2, autor);
				pstm.setString(3, tema);
				pstm.setString(4, prepararFormato(formato));
				pstm.setString(5, estado);
				pstm.setInt(6, Integer.parseInt(numPaginas));
				int resul = pstm.executeUpdate();
				if(resul == 1)
					System.out.println("1 fila actualizado");
				else
					System.out.println("no se a actualizado");

			} catch (SQLException e) {}
			finally{cerrarConexiones();}
		}
	}
//rellena el listado
	public String rellenarLista(){
		String cadena="";
		int contador = 0;
		con = conector.conectar();
		if(con!=null){
			try{
				String sql = "SELECT strtitulo FROM estanteria";
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()){
					if(contador == 0)
						cadena += rs.getString("strtitulo");
					else
						cadena += " "+rs.getString("strtitulo");
					contador++;
				}
			}catch(SQLException  e){}
			finally{cerrarConexiones();}
		}
		return cadena;
	}//rellenarLista



}//Estanteria




