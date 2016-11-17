package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;

import negocio.Estanteria;
import negocio.Libro;

public class ParcialLibreria extends Libreria{



	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	//Construtor que que carga  los datos  y carga todos los botones 
	public ParcialLibreria(Estanteria estanteria) {
		gestion = estanteria;
		
		//Boton baja
		btnBajas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lamamos a las fucion
				eliminarDatos();
				// empezamos a false poruque tiene que cargar datos primero para ponero a true
				btnBajas.setEnabled(false);
				btnModificar.setEnabled(false);
			}
		});//Fin del boton de bajas

		//llamamos botn alta
		btnAltas.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				//pasamos a comprobar datos true para que lo compruebes
				comprobarDatos(true);
			}
		});//boton altas

		//Preparacion del codigo para el boton de nuevo
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// cuando se nuevo limpiamos datos de la pantalla solo quitamos los datos de la pantalla
				limpiarDatos();
			}
		});//fin del boton nuevo

		//boton salir
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// solo hacemos que el sistemas cierra el programa
				System.exit(0);
			}
		});//boton salir

		//Preparacion de la lista
		lstDisponible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//cargamos los datos  en pantalla 
				prepararDatos();
			}
		});//lista

		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//pasamos a comprobar datos false 
				comprobarDatos(false);
				//no se puede modificar 
				btnModificar.setEnabled(false);
				btnBajas.setEnabled(false);
			}
		});
		//Rellenamos la lista de datos
		rellenarLista();

	}//Constructor
	
	public void iniciar(){
		// se crea el nuevo botonero
		grupo = new ButtonGroup();
		grupo.add(optNovedad);
		grupo.add(optReedicion);
	}

	/**
	 * se le pasa un nuevo false modifica or true da de alta
	 */
	private void comprobarDatos(boolean accion){

		String titulo = txtTitulo.getText();
		String autor = txtAutor.getText();
		String numeroPagina = txtNumPaginas.getText();
		String tema = cmbTema.getSelectedItem().toString();
		String []formato = new String [3];
		String estado;

		if(autor.compareTo("")!=0 && titulo.compareTo("")!=0 && numeroPagina.compareTo("")!=0 && tema.compareTo("")!=0){
			if (chkCartone.isSelected())
				formato[0] = "cartone";
			if (chkRustica.isSelected())
				formato[1] = "rustica";
			if (chkTapaDura.isSelected())
				formato[2] = "tapaDura";
			if(optNovedad.isSelected())
				estado = "novedad";
			else
				estado = "reedicion";

			if(gestion == null)
				gestion = new Estanteria();
			if(accion){
				gestion.altas(titulo, autor, tema, numeroPagina, formato, estado);
				anadirALaLista(titulo);
			}
			else{
				gestion.modificar(titulo, autor, tema, numeroPagina, formato, estado);
				// le pasa lo que tiene selecionado q le cambie el nombre
				modelo.set(lstDisponible.getSelectedIndex(), titulo);
			}
			limpiarDatos();

		}
		else{
			lblAnomalias.setText("Faltan Datos");
		}
	}//ComprobarDatos

	/**
	 * Añade a la lista el nuevo titulo
	 * @param titulo
	 */
	private void anadirALaLista(String titulo) {
		modelo.addElement(titulo);

	}//anadirALaLista

	/**
	 * Este metodo prepara todo los datos para despues mostrarlo, recoge toda la iformacion de cargarDatos() de la clase Estanteria
	 * y despues de recoger todo los datos llama a mostrarDatos()
	 */
	public void prepararDatos(){
		if(gestion == null)
			gestion = new Estanteria();

		Libro libro = gestion.cargarDatos(lstDisponible.getSelectedValue());
		if(libro != null){
			String titulo = libro.getTitulo();
			String autor = libro.getAutor();
			String tema = libro.getTema();
			int numPaginas =  libro.getNumPaginas();
			String []formato = libro.getFormato();
			String estado = libro.getEstado();
			mostrarDatos(titulo, autor,tema,numPaginas,formato,estado);
			btnModificar.setEnabled(true);
			btnBajas.setEnabled(true);

		}
	}//prepararDatos

	/**
	 * Muestra los dato en pantallla
	 * @param titulo-titulo del libro
	 * @param autor-autor del libro
	 * @param tema-tema del libro
	 * @param numPaginas-numero de pagina del libro
	 * @param formato-array de String con el formato del libro
	 * @param estado-estado del libro
	 */
	private void mostrarDatos(String titulo, String autor, String tema,int numPaginas, String[] formato, String estado) {
		limpiarDatos();
		txtTitulo.setText(titulo);
		txtAutor.setText(autor);
		txtNumPaginas.setText(Integer.toString(numPaginas));
		cmbTema.setSelectedItem(tema);

		if(!formato[0].equals("null"))
			chkCartone.setSelected(true);
		if(!formato[1].equals("null"))
			chkRustica.setSelected(true);
		if(!formato[2].equals("null"))
			chkTapaDura.setSelected(true);
		if(estado.compareTo("novedad")==0){
			optNovedad.setSelected(true);
		}else{
			optReedicion.setSelected(true);
		}

	}//mostrarDatos()

	public void limpiarDatos(){
		txtTitulo.setText("");
		txtAutor.setText("");
		cmbTema.setSelectedItem("");
		txtNumPaginas.setText("");
		chkCartone.setSelected(false);
		chkRustica.setSelected(false);
		chkTapaDura.setSelected(false);
		optNovedad.setSelected(true);

	}//limpiarDatos

	//Con este metodo eliminamos los datos seleccionado
	public void eliminarDatos(){
		if(gestion.borrarLineaRegistro(txtTitulo.getText())){
			modelo.removeElement(txtTitulo.getText());
			limpiarDatos();
		}else{
			lblAnomalias.setText("No emos podido borrar los datos, ay algun problema en la clase Estanteria");
		}
	}//eliminarDatos

	private void rellenarLista(){
		System.out.println(gestion);
		String cadenita = gestion.rellenarLista();
		String[] cadena;
		if(cadenita!=null){
			cadena = cadenita.split(" ");
			for(int i=0; i<cadena.length;i++)
				modelo.addElement(cadena[i]);
		}
	}

}
