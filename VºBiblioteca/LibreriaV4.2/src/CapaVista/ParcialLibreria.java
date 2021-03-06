package CapaVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;

import CapaNegocio.Estanteria;
import CapaNegocio.Libro;
import CapaNegocio.Mensajes;


public class ParcialLibreria extends Libreria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Constructor en el que se inicia todo los listener y la lista en caso de que haya datos
	public ParcialLibreria(Estanteria estanteria) {
		gestion = estanteria;
		
		//Preparacion del codigo para el boton de bajas
		btnBajas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarDatos();
				btnBajas.setEnabled(false);
				btnModificar.setEnabled(false);
			}
		});//Fin del boton de bajas

		//Preparacion del codigo para el boton de altas
		btnAltas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comprobarDatos(true);
			}
		});//fin del boton altas

		//Preparacion del codigo para el boton de nuevo
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});//fin del boton nuevo

		//Preparacion del codigo para el boton salir
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});//fin del boton salir

		//Preparacion de la lista
		lstDisponible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				prepararDatos();
				lblAnomalias.setText("");
			}
		});//fin de la lista

		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comprobarDatos(false);
				btnModificar.setEnabled(false);
				btnBajas.setEnabled(false);
			}
		});
		//Rellenamos la lista de datos
		rellenarLista();

	}//Constructor
	
	public void iniciar(){
		//gestion = new Estanteria();
		grupo = new ButtonGroup();
		grupo.add(optNovedad);
		grupo.add(optReedicion);
	}

	/**
	 * Este metodo va a comprobar que todos los campos esten relleno, para que cuando se llame al metodo de altas no genere errores
	 * accion false modifica y true alta 
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
			if(accion)
			{
				if(gestion.altas(titulo, autor, tema, numeroPagina, formato, estado))
				{
					anadirALaLista(titulo);
				}	
			}
			else
			{
				gestion.modificar(titulo, autor, tema, numeroPagina, formato, estado);
				//se le pasa lo que esta seleccionado para que le cambie el nombre
				modelo.set(lstDisponible.getSelectedIndex(), titulo);
			}
			limpiarDatos();
		}
		else{
			new Mensajes(Mensajes.MEN_COMPROBAR_DATOS);
		}
	}//ComprobarDatos

	/**
	 * Este metodo a�ade el titulo al la lista, lstDisponible
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
	 * Este metodo solo se encarga de mostrar los datos en pantalla
	 * @param titulo Es el titulo del libro
	 * @param autor Es el autor del libro
	 * @param tema Es el tema del libro
	 * @param numPaginas Es el numero de pagina del libro
	 * @param formato Es un array de String con el formato del libro
	 * @param estado Es el estado del libro
	 */
	private void mostrarDatos(String titulo, String autor, String tema,int numPaginas, String[] formato, String estado) {
		limpiarDatos();
		txtTitulo.setText(titulo);
		txtAutor.setText(autor);
		txtNumPaginas.setText(Integer.toString(numPaginas));
		//cmbTema.setSelectedIndex(seleccionarComboBox(tema));
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

	// Este metodo limpia los datos de la pantalla
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
			new Mensajes(Mensajes.MEN_ELIMINAR_DATOS_CORRECTO);
		}else{
			new Mensajes(Mensajes.MEN_ELIMINAR_DATOS);
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
