package biblioteca;

public class Estanteria {
	private int reg;
	private  static final int MAXREG = 100;
	private Libro[] libro;
	private int posicion;


	public Estanteria() {
		reg = 0;
		libro = new Libro[MAXREG];
	}

	public void altas(String titulo, String autor, String tema, String numPaginas, String[] formato,String estado){
		if(reg<MAXREG){
			libro[reg] = new Libro();
			libro[reg].setTitulo(titulo);
			libro[reg].setAutor(autor);
			libro[reg].setTema(tema);
			libro[reg].setNumPaginas(Integer.parseInt(numPaginas));
			libro[reg].setFormato(formato);
			libro[reg].setEstado(estado);
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
			nuevo[0] = libro[posicion].getTitulo();
			nuevo[1] = libro[posicion].getAutor();
			nuevo[2] = libro[posicion].getTema();
			nuevo[3] = libro[posicion].getNumPaginas();
			nuevo[4] = libro[posicion].getFormato();
			nuevo[5] = libro[posicion].getEstado();	
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

}
