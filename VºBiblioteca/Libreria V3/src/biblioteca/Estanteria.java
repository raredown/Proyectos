package biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Estanteria {
	private int reg;
	private  static final int MAXREG = 100;
	private Libro[] libro;
	private int posicion;
	File archivo= new File("estanteria.txt");
	private RandomAccessFile flujo = null;
	private BufferedReader bufer= null;
	private long pos;
	private String cadenaEncontrada;
	public boolean abrirArchivo(){
		archivo = new File("estanteria.txt");
		//Si no existe el fichero lo creamos

		if(!archivo.exists()){
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}//if
		if(archivo.exists()){
			try {
				flujo=new RandomAccessFile(archivo, "rw");
				
			} catch (FileNotFoundException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		
			return true;
			}
		
		else{
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			return false;
		}
		
	}//abrir Archivo Si se abre bien o no. Si se abre creamos el flujo.
	
	private void ultimaPosicion(){
		try {
			pos = flujo.length();
			flujo.seek(pos);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}//	Pone el fichero en la ultima posicion.
	public String leerLinea(){
		String cadena="";

		try {
			cadena=flujo.readLine();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		return cadena;
	}//Q te de cada linea
	public void comenzarLectura(){
		try {
			if(flujo.length()>pos)
				pos=flujo.length();
		} catch (IOException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		try {
			flujo.seek(0);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}//Ponemos el puntero al principio
	
	public Estanteria() {
		abrirArchivo();
		reg = 0;
		libro = new Libro[MAXREG];
	}

	public void altas(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado){
		if(reg<MAXREG){
			libro[reg] = new Libro();
//			String formatito="";
//			if(formato[0] != null)
//				formatito+=formato[0];
//			if(formato[1] != null)
//				if(formatito.compareTo("")==0)
//					formatito+=formato[1];
//				else
//					formatito+="$"+formato[1];
//			if(formato[2] != null)
//				if(formatito.compareTo("")==0)
//					formatito+=formato[2];
//				else
//					formatito+="$"+formato[2];
			String cadena;
			cadena=titulo+"@@##@@"+autor+"@@##@@"+tema+"@@##@@"+numPaginas+"@@##@@"+estado+"@@##@@"+formato[0]+"@@##@@"+formato[1]+"@@##@@"+formato[2];
			
			try {
				abrirArchivo();
				ultimaPosicion();
				flujo.writeBytes(cadena+System.getProperty("line.separator"));
				flujo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reg++;
		}//if
	}//altas()
	private boolean compararTitulo(String linea,String titulo){
			String[] lineacita=linea.split("@@##@@");
		
		if(lineacita[0].compareTo(titulo)==0)
			return true;
		else
			return false;
		
	}//comparar el titulo
	public boolean buscarLibro(String titulo){
		boolean bandera = false;
		if(reg > 0&&abrirArchivo()){
			String leyendoLinea;
			do{
				leyendoLinea =leerLinea();
				if(leyendoLinea!=null)
					bandera = compararTitulo(leyendoLinea, titulo);
				if(bandera)
					cadenaEncontrada = leyendoLinea;
			}while(leyendoLinea!=null && !bandera);
//			for(int i = 0; i<reg;i++){
//				if(titulo.compareTo(libro[i].getTitulo())==0){
//					posicion = i;
//					bandera = true;
//				}//if
//			}//for
		}else
			bandera = false;

		return bandera;
	}//buscaLibros()
	
	public Object[] cargarDatos(String titulo){
		Object[] nuevo = new Object[6];
		if(buscarLibro(titulo)){
			String[] cadenaCortada=cadenaEncontrada.split("@@##@@");
			String [] formatoExtraido={cadenaCortada[5],cadenaCortada[6],cadenaCortada[7]};
			nuevo[0] = cadenaCortada[0];
			nuevo[1] = cadenaCortada[1];
			nuevo[2] = cadenaCortada[2];
			nuevo[3] = cadenaCortada[3];
			
			nuevo[4] = formatoExtraido;
			nuevo[5] = cadenaCortada[4];	
			return nuevo;
		}else
			return null;
	}
	
	public boolean borrarDatos(String titulo){
		if(buscarLibro(titulo)){
			reg--;
			libro[posicion] = libro[reg];
			libro[reg].setTitulo("");
			libro[reg].setAutor("");
			libro[reg].setNumPaginas(0);
			libro[reg].setFormato(null);
			libro[reg].setTema("");
			libro[reg].setEstado("");
			return true;
		}else{
			return false;
		}
	}
//	public String[] llamarFormato(String cadenaCortada[]){
//		if(cadenaCortada.length > 5){
//			
//			if(cadenaCortada.length > 6){
//				
//				if(cadenaCortada.length > 7){
//					
//				}
//			}
//			
//		}
//	}

}
