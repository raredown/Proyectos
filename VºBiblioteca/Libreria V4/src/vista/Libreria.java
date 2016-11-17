package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import negocio.Estanteria;



public class Libreria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField txtMensajes;
	protected JTextField txtPaginas;
	protected JTextField txtAutor;
	protected JTextField txtTitulo;
	protected JComboBox<Object> cboTema;
	protected JButton btnAltas;
	protected JButton btnBajas;
	protected JButton btnNuevo;
	protected JButton btnSalir;
	protected JCheckBox chckbxCartone;
	protected JCheckBox chckbxRustica;
	protected JCheckBox chckbxTapaDura;
	protected JRadioButton rdbtnNovedad;
	protected JRadioButton rdbtnReedicion;

	//Creacion de un objeto lista, un objeto DefaultListModel para añadir elementos a la lista y un scrollpane para hacer que la lista sea scrollable
	@SuppressWarnings("rawtypes")
	protected JList lstDisponible;
	@SuppressWarnings("rawtypes")
	protected DefaultListModel model = new DefaultListModel();
	protected JScrollPane scrollPane = new JScrollPane();




	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Libreria() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LIBRERIA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 11, 364, 108);
		contentPane.add(lblNewLabel);

		//Se instancia el objeto de la clase JList pasandole como parametro el objeto model creado anteriormente
		lstDisponible = new JList(model);
		//se le añade la lista al objeto scrollPane para hacerla scrollable
		scrollPane.setViewportView(lstDisponible);
		//se le colocan los limites al scrollPane
		scrollPane.setBounds(280, 320, 334, 142);
		//Y se añade al contenPane
		contentPane.add(scrollPane);


		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setBounds(412, 47, 202, 20);
		contentPane.add(lblMensajes);

		txtMensajes = new JTextField();
		txtMensajes.setEditable(false);
		txtMensajes.setBounds(412, 67, 202, 20);
		contentPane.add(txtMensajes);
		txtMensajes.setColumns(10);

		JLabel lblTitulo = new JLabel("TITULO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(20, 130, 91, 20);
		contentPane.add(lblTitulo);

		JLabel lblAutor = new JLabel("AUTOR");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutor.setBounds(20, 192, 91, 20);
		contentPane.add(lblAutor);

		JLabel lblTema = new JLabel("TEMA");
		lblTema.setHorizontalAlignment(SwingConstants.CENTER);
		lblTema.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTema.setBounds(20, 255, 91, 20);
		contentPane.add(lblTema);

		JLabel lblPaginas = new JLabel("N\u00BA DE PAGINAS");
		lblPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPaginas.setBounds(20, 317, 107, 20);
		contentPane.add(lblPaginas);

		txtPaginas = new JTextField();
		txtPaginas.setBounds(137, 318, 101, 20);
		contentPane.add(txtPaginas);
		txtPaginas.setColumns(10);

		txtAutor = new JTextField();
		txtAutor.setBounds(137, 193, 202, 20);
		contentPane.add(txtAutor);
		txtAutor.setColumns(10);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(137, 130, 202, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		//Se le añaden los temas al comboBox a traves de un array de Strings
		cboTema = new JComboBox<Object>();
		cboTema.setModel(new DefaultComboBoxModel(new String[] {"Accion", "Aventuras", "Biografia", "Ciencia", "Ciencia Ficcion", "Cine", "Economia", "Gastronomia", "Historia", "Informatica", "Medicina", "Misterio", "Naturaleza", "Policiaco", "Politica", "Romantica", "Teatro", "Terror\""}));
		cboTema.setBounds(137, 256, 147, 20);
		contentPane.add(cboTema);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(20, 425, 107, 40);
		contentPane.add(btnNuevo);

		btnAltas = new JButton("Altas");

		btnAltas.setBounds(20, 374, 107, 40);
		contentPane.add(btnAltas);

		btnBajas = new JButton("Bajas");
		btnBajas.setEnabled(false);
		btnBajas.setBounds(137, 374, 107, 40);
		contentPane.add(btnBajas);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(137, 425, 107, 40);
		contentPane.add(btnSalir);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setBounds(412, 130, 202, 75);
		contentPane.add(verticalBox);

		chckbxCartone = new JCheckBox("Carton\u00E9");
		verticalBox.add(chckbxCartone);

		chckbxRustica = new JCheckBox("R\u00FAstica");
		verticalBox.add(chckbxRustica);

		chckbxTapaDura = new JCheckBox("Tapa Dura");
		verticalBox.add(chckbxTapaDura);

		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setBounds(412, 115, 202, 14);
		contentPane.add(lblFormato);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox_1.setBounds(412, 236, 202, 51);
		contentPane.add(verticalBox_1);

		rdbtnNovedad = new JRadioButton("Novedad");
		verticalBox_1.add(rdbtnNovedad);

		rdbtnReedicion = new JRadioButton("Reedición");
		verticalBox_1.add(rdbtnReedicion);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(412, 222, 46, 14);
		contentPane.add(lblEstado);

		JLabel lblLibros = new JLabel("Libros");
		lblLibros.setBounds(280, 300, 46, 14);
		contentPane.add(lblLibros);

		setVisible(true);
	}


	public static void main(String[] args) {
		Estanteria estante = new Estanteria(); 
		//No es necesario crear un objeto de esta clase ya que todo lo que se usa de esa clase esta en el constructor, por lo tanto es lo unico a lo que hay que llamar.
		new ParcialLibreria(estante);


	}


}
