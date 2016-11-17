package negocio;

public class Libro {

	//PROPIEDADES
	private String nombre;
	private String autor;
	private String tema;
	private String paginas;
	private String formatoUno;
	private String formatoDos;
	private String formatoTres;
	//Al ser tres los posibles estados, es necesario un array para tener los tres
	private String estado;


	//Constructor
	/**
	 * 
	 * @param nombre
	 * @param autor
	 * @param tema
	 * @param formato
	 * @param estado
	 * @param paginas
	 */
	public Libro(String nombre, String autor, String tema, String paginas, String estado, String formatoUno, String formatoDos, String formatoTres) {

		this.nombre = nombre;
		this.autor = autor;
		this.tema = tema;
		this.paginas = paginas;
		this.formatoUno = formatoUno;
		this.formatoDos = formatoDos;
		this.formatoTres = formatoTres;
		this.estado = estado;

	}


	//GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getPaginas() {
		return paginas;
	}
	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	public String getFormatoUno() {
		return formatoUno;
	}


	public void setFormatoUno(String formatoUno) {
		this.formatoUno = formatoUno;
	}


	public String getFormatoDos() {
		return formatoDos;
	}


	public void setFormatoDos(String formatoDos) {
		this.formatoDos = formatoDos;
	}


	public String getFormatoTres() {
		return formatoTres;
	}


	public void setFormatoTres(String formatoTres) {
		this.formatoTres = formatoTres;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}


}
