package negocio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class Estanteria{

	//PROPIEDADES
	private File archivoFuente = new File("textoorigen.txt");
	private File archivoDestino = new File("textodestino.txt");
	private BufferedReader bufer = null;
	private FileWriter flujoFuente= null;
	private FileWriter flujoDestino=null;
	private String linea;

	/**
	 * 
	 */
	public Estanteria() {
		// TODO Auto-generated constructor stub
		archivoDestino.delete();
		archivoFuente.delete();
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void abriendoFlujos() throws IOException {

		if (!archivoFuente.exists())
			archivoFuente.createNewFile();

		if (!archivoDestino.exists())
			archivoDestino.createNewFile();

		flujoDestino = new FileWriter(archivoDestino, true);
		flujoFuente = new FileWriter(archivoFuente,true);

		bufer = new BufferedReader(

				new InputStreamReader(

						new FileInputStream(archivoFuente),"UTF8"));


	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void cerrarBuffers() throws IOException {
		bufer.close();
		flujoFuente.close();
		flujoDestino.close();
	}
	
	/**
	 * 
	 * @param nombre
	 * @throws IOException
	 */
	public void borrarLinea(String nombre) throws IOException {
		abriendoFlujos();
		while ((linea=bufer.readLine())!=null) {
			if (!(linea.startsWith(nombre)))
				flujoDestino.append(linea+"\n");
		}
		cerrarBuffers();
		if (archivoFuente.exists() && archivoDestino.exists()){
			archivoFuente.delete();
			archivoDestino.renameTo(archivoFuente);
		}
	}

	/**
	 * 
	 * @param libro
	 * @throws IOException
	 */
	private void escribirAlFinal(Libro libro) throws IOException {
		String contenidosLibro="";
		contenidosLibro += libro.getNombre() +","+ libro.getAutor() +","+ libro.getTema() +","+ libro.getPaginas() +","+ libro.getEstado()
				+","+ libro.getFormatoUno() +","+ libro.getFormatoDos() +","+ libro.getFormatoTres();
		flujoFuente.append(contenidosLibro+"\n");
	}
	//METODOS

	/**
	 * Busca si el libro ya existe, si no, crea un objeto libro(con sobrecarga del constructor) y lo introduce en el array, luego aumenta el indice en 1 para el siguiente libro
	 * 
	 * @param nombre
	 * @param autor
	 * @param tema
	 * @param formato
	 * @param estado
	 * @param paginas
	 * @return 1 si el libro ha sido añadido, -1 si el libro ya estaba añadido, 0 si el array esta lleno
	 * @throws IOException 
	 */
	public int altaLibro(String nombre, String autor, String tema, String paginas, String estado, String formatoUno, String formatoDos, String formatoTres) throws IOException {

		int accion = -1;
		if (buscarLibro(nombre) == null) {
			Libro libro = new Libro(nombre, autor, tema, paginas, estado, formatoUno, formatoDos, formatoTres);
			abriendoFlujos();
			escribirAlFinal(libro);
			cerrarBuffers();
			accion = 1;
		} 

		return accion;

	}

	/**
	 * Recibe el nombre del libro y lo busca en el array
	 * @param nombre
	 * @return la posicion si el libro se encuentra en el array y -1 si no
	 * @throws IOException 
	 */
	public Libro buscarLibro(String nombre) throws IOException {
		abriendoFlujos();
		Libro libro = null;
		String[] contenidos = new String[8];
		while ((linea=bufer.readLine())!=null) {
			if (linea.startsWith(nombre)) {
				contenidos = linea.split(",");
				libro = new Libro(contenidos[0], contenidos[1], contenidos[2], contenidos[3], contenidos[4], contenidos[5], contenidos[6], contenidos[7]);
			} 
		}
		cerrarBuffers();
		return libro;
	}
}
