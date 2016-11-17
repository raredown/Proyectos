package CapaNegocio;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CapaDatos.AccesoBD;


public class Estanteria implements Serializable{


	private static final long serialVersionUID = 1L;
	private Connection con;
	//preparedStatement clase que contiene objetos donde es el tipo de argumento que se esta pasando setString setInt etc
	private PreparedStatement pstm;
	//tiene metodos que permiten retroceder o mover arbitrariamente el puntero hacia cualquier fila
	private ResultSet rs;
	private String tituloAntiguo;
	private String usr;
	private String pwd;
	private AccesoBD conector;
	
	public Estanteria() {
		conector = new AccesoBD("root", "");
	}// Constructor


	private void cerrarConexiones(){
		try {
			if(rs!=null)
				rs.close();
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			new Mensajes(e);
		}

	}

	// Con este metodo le vamos a dar de alta en los ficheros a los nuevos libros
	public boolean altas(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado) {
		boolean bandera=true;
		con = conector.conectar();
		if(con!=null){
			String sql="";
			sql += "INSERT INTO estanteria (strtitulo,strautor,strtema,strformato,strestado,numpaginas) ";
			sql += "VALUES(?,?,?,?,?,?) ";
			try {
					pstm = con.prepareStatement(sql);
					pstm.setString(1,titulo);
					pstm.setString(2, autor);
					pstm.setString(3, tema);
					pstm.setString(4, prepararFormato(formato));
					pstm.setString(5, estado);
					pstm.setInt(6, Integer.parseInt(numPaginas));
				int resul = pstm.executeUpdate();
				if(resul == 1){
					bandera=true;
					new Mensajes(Mensajes.MEN_ALTA_CORRECTA);
				}
				else
				{
					new Mensajes(new SQLException());
				}
			} catch (SQLException e) {
				new Mensajes(e);
				bandera=false;
				
				}
			finally{
				cerrarConexiones();
			}
		}//if
		return bandera;
	}//altas

	//prepara el formato de manera separada con comas(,)
	private String prepararFormato(String[] formato){
		String cadena="";
		for(int i = 0;i<formato.length;i++){
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

	// Con este metodo vamos a buscar el fichero
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
					}//if
				}//if
			} catch (SQLException e) {
				bandera = false;
				new Mensajes(e);
				}

		}//if
		if(bandera)
			return librito;
		else
			return null;
	}//buscarLibro


	// Esto nos devuelve un objeto de libro donde coincida el titulo que nos da por parametro
	public Libro cargarDatos(String titulo){
		Libro librito = buscarLibro(titulo);
		tituloAntiguo = librito.getTitulo();
		return librito;	
	}//cargarDatos

	public boolean borrarLineaRegistro(String titulo){
		boolean bandera = false;
		con = conector.conectar();
		try {
			if(con!=null){
				String sql = "DELETE FROM estanteria WHERE strtitulo = ?";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, titulo);
				int result = pstm.executeUpdate();
				if(result==1){
					bandera = true;
					new Mensajes(Mensajes.MEN_BAJA_CORRECTA);
				}
			}

		} catch (SQLException e) {
			new Mensajes(e);
		}
		finally{
			cerrarConexiones();
		}
		return bandera;
	}

	public void modificar(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado){
		con =conector.conectar();
		if(con!=null){
			try {
				String sql = "UPDATE estanteria SET strtitulo = ?, strautor = ?, strtema = ?, strformato = ?, strestado = ?, numpaginas = ? " 
							+"WHERE strtitulo = '"+tituloAntiguo+"'";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, titulo);
				pstm.setString(2, autor);
				pstm.setString(3, tema);
				pstm.setString(4, prepararFormato(formato));
				pstm.setString(5, estado);
				pstm.setInt(6, Integer.parseInt(numPaginas));
				int resul = pstm.executeUpdate();
				if(resul == 1){
					new Mensajes(Mensajes.MEN_MODIFICAR_CORRECTA);
				}
			} catch (SQLException e) {
				new Mensajes(e);
			}
			finally{cerrarConexiones();}
		}
	}

	public String rellenarLista(){
		String cadena="";
		int contador = 0;
		con =conector.conectar();
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
			}catch(SQLException  e){
				new Mensajes(e);
			}
			finally{cerrarConexiones();}
		}
		return cadena;
	}//rellenarLista



}//Fin de la clase Estanteria




