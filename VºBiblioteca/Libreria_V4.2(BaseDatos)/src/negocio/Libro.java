package negocio;

import java.io.Serializable;

public class Libro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String autor;
	private String tema;
	private String[] formato;
	private String estado;
	private int numPaginas;
	
	
	public Libro(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado) {
		this.titulo = titulo;
		this.autor = autor;
		this.tema = tema;
		this.numPaginas = Integer.parseInt(numPaginas);
		this.formato = formato;
		this.estado = estado;
	}
	
	
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String[] getFormato() {
		return formato;
	}
	public void setFormato(String[] formato) {
		this.formato = formato;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
}
