package negocio;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


import datos.AccesoBD;

public class AccesoLibro extends AccesoBD implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tituloAntiguo;
	private ResultSet rs;


	// Con este metodo le vamos a dar de alta en los ficheros a los nuevos
	// libros
	public boolean altas(Libro libro) {
		boolean bandera = false;
		String sql = "";
		sql += "INSERT INTO estanteria (strtitulo,strautor,strtema,strformato,strestado,numpaginas) ";
		sql += "VALUES('" + libro.getTitulo() + "','" + libro.getAutor() + "','" + libro.getTema()+ "','"
				+ prepararFormato(libro.getFormato()) + "','" + libro.getEstado() + "','"
				+ Integer.toString(libro.getNumPaginas()) + "')";
		bandera = ejecutarUpdate(sql);
		if(bandera)
			new Mensajes(Mensajes.MEN_ALTA_CORRECTA);
	
		return bandera;
	}// altas

	private String prepararFormato(String[] formato) {
		String cadena = "";
		for (int i = 0; i < formato.length; i++) {
			if (i == 0)
				cadena += formato[i];
			else
				cadena += "," + formato[i];
		}
		return cadena;
	}

	private String[] arrayFormato(String cadena) {
		return cadena.split(",");
	}

	// Con este metodo vamos a buscar el fichero
	public Libro buscarLibro(String titulo) {
		boolean bandera = false;
		Libro librito = null;
		try {
			abrirConexion();
			rs = ejecutarConsulta("SELECT * FROM estanteria l WHERE strtitulo = \""
					+ titulo + "\"");
			if (rs.next()) {
				if (rs.getString("strtitulo").equals(titulo)) {
					titulo = rs.getString("strtitulo");
					String autor =rs.getString("strautor");
					String tema = rs.getString("strtema");
					String[] formato = arrayFormato(rs.getString("strformato"));
					String estado = rs.getString("strestado");
					String numPaginas = Integer.toString(rs.getInt("numpaginas"));
					librito = new Libro(titulo, autor, tema, numPaginas,
							formato, estado);
					bandera = true;
				}
			}// while
		} catch (SQLException e) {
			new Mensajes(e);
			bandera = false;
		} finally {
			cerrarConexion();
		}
		if (bandera)
			return librito;
		else
			return null;
	}// buscarLibro

	// Esto nos devuelve un objeto de libro donde coincida el titulo que nos da
	// por parametro
	public Libro cargarDatos(String titulo) {
		Libro librito = buscarLibro(titulo);
		tituloAntiguo = librito.getTitulo();
		return librito;
	}// cargarDatos

	public boolean borrarLineaRegistro(String titulo) {
		boolean bandera = false;

		if (ejecutarUpdate("DELETE FROM estanteria WHERE strtitulo = '"
				+ titulo + "'")) {
			bandera = true;
			new Mensajes(Mensajes.MEN_BAJA_CORRECTA);
		}

		return bandera;
	}

	public void modificar(Libro libro) {
		
		String sql = "UPDATE estanteria SET strtitulo = '" + libro.getTitulo()
				+ "', strautor = '" +libro.getAutor() + "', strtema = '" + libro.getTema()
				+ "', strformato = '" + prepararFormato(libro.getFormato())
				+ "', strestado = '" + libro.getEstado()+ "', numpaginas = '"
				+ libro.getNumPaginas() + "' WHERE strtitulo = '" + tituloAntiguo + "'";
		ejecutarUpdate(sql);
		
	
	}

	public String rellenarLista() {
		String cadena = "";
		int contador = 0;
		try {
			abrirConexion();
			String sql = "SELECT strtitulo FROM estanteria";
			rs = ejecutarConsulta(sql);
			while (rs.next()) {
				if (contador == 0)
					cadena += rs.getString("strtitulo");
				else
					cadena += "%%" + rs.getString("strtitulo");
				contador++;
			}
		} catch (Exception e) {
			new Mensajes(e);
		} finally {
			cerrarConexion();
		}
		return cadena;
	}// rellenarLista

}// Fin de la clase Estanteria

