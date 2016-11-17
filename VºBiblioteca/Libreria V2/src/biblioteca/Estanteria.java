package biblioteca;

import java.util.ArrayList;

public class Estanteria {
	private int reg;
	private  static final int MAXREG = 100;
	private Libro[] libro;
	private int posicion;
	ArrayList<Libro> array = new ArrayList();


	public Estanteria() {
		reg = 0;
		
		libro = new Libro[MAXREG];
	}

	public void altas(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado){
		if(reg<MAXREG){
			Libro objLibro = new Libro(titulo,autor,tema,formato,estado,Integer.parseInt(numPaginas));
			libro[reg] = objLibro;
			array.add(objLibro);
			reg++;
		}//if
	}//altas()

	public boolean buscarLibro(String titulo){
		boolean bandera = false;
		if(reg > 0){
			for(int i = 0; i<reg;i++){
				if(titulo.compareTo(libro[i].getTitulo())==0){
					posicion = i;
					bandera = true;
				}//if
			}//for
		}else
			bandera = false;

		return bandera;
	}//buscaLibros()
	
	public Object[] cargarDatos(String titulo){
		Object[] nuevo = new Object[6];
		if(buscarLibro(titulo)){
			Libro nuevo2= array.get(posicion);
			nuevo[0] = nuevo2.getTitulo();
			nuevo[1] = nuevo2.getAutor();
			nuevo[2] = nuevo2.getTema();
			nuevo[3] = nuevo2.getNumPaginas();
			nuevo[4] = nuevo2.getFormato();
			nuevo[5] = nuevo2.getEstado();	
			return nuevo;
		}else
			return null;
	}
	
	public boolean borrarDatos(String titulo){
		if(buscarLibro(titulo)){
			reg--;
			array.remove(posicion);
			
			return true;
		}else{
			return false;
		}
	}

}
