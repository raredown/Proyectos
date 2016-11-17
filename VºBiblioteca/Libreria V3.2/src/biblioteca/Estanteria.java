package biblioteca;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Estanteria {
	private int reg;
	private  static final int MAXREG = 100;
	private Libro[] libro;
	private int posicion;
	File archivo;
	private FileReader flujo = null;
	private FileWriter flujoEscribe = null;
	private BufferedReader bufer= null;
	File paraReemplazar = new File("destino");
	FileWriter flujoR = null;
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
				flujo=new FileReader(archivo);
				flujoEscribe = new FileWriter(archivo,true);
				bufer = new BufferedReader(flujo);
				flujoR = new FileWriter(paraReemplazar,true);

			} catch (Exception e) {
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

	//	private void ultimaPosicion(){
	//		try {
	//			pos = flujo.length();
	//			flujo.seek(pos);
	//		} catch (IOException e) {
	//			// TODO Bloque catch generado automáticamente
	//			e.printStackTrace();
	//		}
	//
	//	}//	Pone el fichero en la ultima posicion.
	public String leerLinea(){
		String cadena="";

		try {
			cadena= bufer.readLine();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		return cadena;
	}//Q te de cada linea
	//	public void comenzarLectura(){
	//		try {
	//			if(flujo.length()>pos)
	//				pos=flujo.length();
	//		} catch (IOException e1) {
	//			// TODO Bloque catch generado automáticamente
	//			e1.printStackTrace();
	//		}
	//		try {
	//			flujo.seek(0);
	//		} catch (IOException e) {
	//			// TODO Bloque catch generado automáticamente
	//			e.printStackTrace();
	//		}
	//
	//	}//Ponemos el puntero al principio

	public Estanteria() {
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
				//ultimaPosicion();
				flujoEscribe.write(cadena+System.getProperty("line.separator"));
				flujoEscribe.close();
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
		if(abrirArchivo()){
			String leyendoLinea;
			do{
				leyendoLinea =leerLinea();
				if(leyendoLinea!=null)
					bandera = compararTitulo(leyendoLinea, titulo);
				if(bandera)
					cadenaEncontrada = leyendoLinea;
			}while(leyendoLinea!=null && !bandera);
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

	public boolean modificarOborrar(String titulo){
		boolean bandera = false;
		try{

			if(abrirArchivo()){
				String leyendoLinea;
				do{
					leyendoLinea =leerLinea();
					if(leyendoLinea!=null){
						bandera = compararTitulo(leyendoLinea, titulo);
						if(bandera)
							cadenaEncontrada = leyendoLinea;
						else
							flujoR.write(leyendoLinea+System.getProperty("line.separator"));
					}
				}while(leyendoLinea!=null /*&& !bandera*/);

				bandera = true;
			}else
				bandera = false;
		}catch(Exception e){bandera = false;}


		return bandera;
	}
	public void borrar(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					Thread.sleep(1000);
					flujoEscribe.close();
					flujo.close();
					bufer.close();
					flujoR.close();
					System.gc();
					Thread.sleep(500);
					if(archivo.delete())System.out.println("Se ha borrado.."); else System.out.println("No se ha borrado");
					if(paraReemplazar.renameTo(archivo))System.out.println("Se ha cambiado.."); else System.out.println("No se ha cambiado");
				}catch (Exception e){};
				
			}
		}).start();
		



	}

	public String rellenarLista(){
		String cadena = "";
		int contador = 0;
		if(abrirArchivo()){
			String leyendoLinea;
			do{
				leyendoLinea =leerLinea();
				if(leyendoLinea!=null){
					if(contador == 0)
						cadena+= leyendoLinea.split("@@##@@")[0];
					else
						cadena+= " "+leyendoLinea.split("@@##@@")[0];
				}
				contador++;
			}while(leyendoLinea!=null);
			return cadena;
		}else
			return null;
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

