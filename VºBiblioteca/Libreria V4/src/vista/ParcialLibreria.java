package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import negocio.Estanteria;
import negocio.Libro;


public class ParcialLibreria extends Libreria {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * CONSTRUCTOR DE LA CLASE
	 */
	public ParcialLibreria(final Estanteria estante) {
		// TODO Auto-generated constructor stub

		//****************************************************LISTENERS****************************************
		//LISTENER DE LA LISTA
		lstDisponible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Y se manda el nombre del elemento selecionado a cargarDatos que devolvera un objeto tipo libro que a su vez se lo manda a ponerDatos para poner los datos en los campos
				//de la interfaz
				try {
					visualizarLibro(estante.buscarLibro(lstDisponible.getSelectedValue().toString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Una vez hay al menos 1 elemento en la lista, se activa el boton Bajas
				btnBajas.setEnabled(true);

			}
		});

		//LISTENER DEL BOTON BAJAS

		btnBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Se le manda el nombre del libro seleccionado a la lista a borrarLibros para que lo borre del array
				try {
					estante.borrarLinea(lstDisponible.getSelectedValue().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//Si hay algun elemento (que no sea -1)
				if (lstDisponible.getSelectedIndex() != -1) {
					//se borra del model, (de la lista)
					model.remove(lstDisponible.getSelectedIndex());
				}
				//Si model (o la lista) esta vacio, se desactiva el boton bajas, y se borran los campos
				if (model.isEmpty()) {
					btnBajas.setEnabled(false);
					limpiarCampos();
				}
			}
		});

		//LISTENERS DEL BOTON ALTAS

		btnAltas.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				//Estos son ahora locales de este listener, estadoSeleccionado es necesario puesto que para saber cual esta seleccionado es un boolean y necesitas devolver un String
				String numPags;
				String estadoSeleccionado;
				String[] formato = {".", ".", "."};

				//He cambiado el try catch que tenia para si ponian alguna letra en el numero de paginas por un regex que es mas eficiente
				numPags = txtPaginas.getText();
				if (!numPags.matches("[0-9]+"))
					numPags="0";

				if (chckbxCartone.isSelected()) formato[0] = "Cartoné";
				else formato[0] = ".";
				if (chckbxRustica.isSelected()) formato[1] = "Rústico";
				else formato[1] = ".";
				if (chckbxTapaDura.isSelected())formato[2] = "TapaDura";
				else formato[2] = ".";

				if (rdbtnNovedad.isSelected())
					estadoSeleccionado="Novedad";
				else {
					estadoSeleccionado="Reedicion";
				}





				try {
					//He quitado la variable accion y he utilizado un switch para las acciones que debe de hacer
					switch (estante.altaLibro(txtTitulo.getText(), txtAutor.getText(), cboTema.getSelectedItem().toString(), numPags, estadoSeleccionado, formato[0], formato[1], formato[2] )) {
					case 1:
						txtMensajes.setText("Libro Añadido");
						//Se añade el nombre del libro al model, que a su vez se añade a la lista
						model.addElement(txtTitulo.getText());
						break;
					case -1:
						txtMensajes.setText("El libro ya se encuentra introducido");
						break;
					case 0:
						txtMensajes.setText("Maximo de libros alcanzado");
						break;

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});


		//LISTENER DEL BOTON SALIR
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		//LISTENER DEL BOTON NUEVO
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Se borran todos los campos
				limpiarCampos();

			}
		});



		//LISTENER DEL RADIO BUTTON NOVEDAD
		rdbtnNovedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si novedad esta selecionado, deselecciona Reedicion
				if (rdbtnNovedad.isSelected()) {
					rdbtnReedicion.setSelected(false);
				}
			}
		});


		//LISTENER DEL RADIO BUTTON REEDICION
		rdbtnReedicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si reedicion esta seleccionado, deselecciona Novedad
				if (rdbtnReedicion.isSelected()){ 
					rdbtnNovedad.setSelected(false);
				}

			}
		});


	}//Constructor


	//METODOS
	/**
	 * 
	 * @param libro
	 */
	//Este metodo ahora devuelve un String con todas los atributos del libro, ya que no se guardan los libros, seria imposible acceder al primero despues de crear el segundo
	private void visualizarLibro(Libro libro) {

		txtTitulo.setText(libro.getNombre());
		txtPaginas.setText(libro.getPaginas());
		txtAutor.setText(libro.getAutor());
		cboTema.setSelectedItem(libro.getTema());

		//Si la primera posicion del array de formatos es ., no se activa el checkbox, si no lo es, se activa
		if (!libro.getFormatoUno().equals(".")){

			chckbxCartone.setSelected(true);

		} else {

			chckbxCartone.setSelected(false);


		}

		if (!libro.getFormatoDos().equals("."))  {

			chckbxRustica.setSelected(true);

		}
		else { 

			chckbxRustica.setSelected(false);


		}
		if (!libro.getFormatoTres().equals(".")) {
			chckbxTapaDura.setSelected(true);

		}
		else {
			chckbxTapaDura.setSelected(false);

		}


		//Si el estado es diferente a reedicion o novedad no se activa ninguno, si es igual a alguno, se activa ese
		if (libro.getEstado().equals("Novedad") || libro.getEstado().equals("Reedicion")) {
			if (libro.getEstado().equals("Reedicion")) {
				rdbtnReedicion.setSelected(true);
				rdbtnNovedad.setSelected(false);
			}
			else {
				rdbtnNovedad.setSelected(true);
				rdbtnReedicion.setSelected(false);
			}
		}

	}


	protected void limpiarCampos() {

		txtAutor.setText("");
		txtMensajes.setText("");
		txtPaginas.setText("");
		txtTitulo.setText("");
		chckbxCartone.setSelected(false);
		chckbxRustica.setSelected(false);
		chckbxTapaDura.setSelected(false);
		rdbtnNovedad.setSelected(false);
		rdbtnReedicion.setSelected(false);



	}








}
