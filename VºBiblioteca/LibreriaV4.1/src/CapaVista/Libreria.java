package CapaVista;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

import CapaNegocio.Estanteria;




public class Libreria extends JFrame {


	protected static final long serialVersionUID = 1L;
	protected JPanel fondo;
	protected JTextField txtTitulo;
	protected JTextField txtAutor;
	protected JTextField txtNumPaginas;
	protected DefaultListModel<String> modelo = new DefaultListModel<>();
	protected JList<String> lstDisponible;
	protected ButtonGroup grupo;
	protected JRadioButton optNovedad;
	protected JRadioButton optReedicion;
	protected static JLabel lblAnomalias;
	//lo pongo estatico para no tener que crear un objeto y hacemos un getter de lblanomalias para poner el error que esta dando que
	//viene de la clase control de errores
	public static JLabel getLblAnomalias() {
		return lblAnomalias;
	}


	protected JComboBox<String> cmbTema;
	protected JCheckBox chkCartone;
	protected JCheckBox chkRustica;
	protected JCheckBox chkTapaDura;
	protected Estanteria gestion;
	protected String[] elementosLista = {"", "Accion", "Aventuras", "Biografia", "Comedia", "Terror", "Ciencia-Ficcion", "Comics"};
	protected JButton btnBajas;
	protected JButton btnAltas;
	protected JButton btnNuevo;
	protected JButton btnSalir;
	protected JButton btnModificar;


	public static void main(String[] args) {
		Estanteria gestion = new Estanteria("root","");
		ParcialLibreria parcial = new ParcialLibreria(gestion);
		parcial.iniciar();
		parcial.setVisible(true);			
	}


	public Libreria() {
		setResizable(false);
		setTitle("Bibliotea V-1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 486);
		fondo = new JPanel();
		fondo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(fondo);
		fondo.setLayout(null);

		JLabel lblLibreria = new JLabel("Libreria");
		lblLibreria.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 35));
		lblLibreria.setBounds(289, 24, 138, 32);
		fondo.add(lblLibreria);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 16));
		lblTitulo.setBounds(26, 93, 61, 14);
		fondo.add(lblTitulo);

		JLabel lblNewLabel = new JLabel("Autor");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 16));
		lblNewLabel.setBounds(26, 143, 61, 14);
		fondo.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tema");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 16));
		lblNewLabel_1.setBounds(26, 193, 61, 14);
		fondo.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Num Paginas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 16));
		lblNewLabel_2.setBounds(26, 243, 115, 14);
		fondo.add(lblNewLabel_2);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(191, 92, 236, 20);
		fondo.add(txtTitulo);
		txtTitulo.setColumns(10);

		txtAutor = new JTextField();
		txtAutor.setBounds(191, 142, 236, 20);
		fondo.add(txtAutor);
		txtAutor.setColumns(10);

		txtNumPaginas = new JTextField();
		txtNumPaginas.setBounds(191, 242, 86, 20);
		fondo.add(txtNumPaginas);
		txtNumPaginas.setColumns(10);

		cmbTema = new JComboBox<>();
		cmbTema.setModel(new DefaultComboBoxModel<>(elementosLista));
		cmbTema.setBounds(191, 192, 86, 20);
		fondo.add(cmbTema);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(10, 321, 89, 48);
		fondo.add(btnNuevo);

		btnBajas = new JButton("Bajas");
		btnBajas.setEnabled(false);
		btnBajas.setBounds(248, 321, 89, 48);
		fondo.add(btnBajas);

		btnAltas = new JButton("Altas");
		btnAltas.setBounds(10, 389, 89, 48);
		fondo.add(btnAltas);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(129, 389, 89, 48);
		fondo.add(btnSalir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 281, 258, 156);
		fondo.add(scrollPane);


		lstDisponible = new JList<>(modelo);
		scrollPane.setViewportView(lstDisponible);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		verticalBox.setFont(new Font("ROMAN_BASELINE", Font.BOLD | Font.ROMAN_BASELINE, 12));
		verticalBox.setBounds(524, 89, 124, 79);
		fondo.add(verticalBox);

		chkCartone = new JCheckBox("Cartone");
		verticalBox.add(chkCartone);

		chkRustica = new JCheckBox("Rustica");
		verticalBox.add(chkRustica);

		chkTapaDura = new JCheckBox("Tapa Dura");
		verticalBox.add(chkTapaDura);

		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Sylfaen", Font.BOLD | Font.ROMAN_BASELINE, 14));
		lblFormato.setBounds(524, 77, 89, 14);
		fondo.add(lblFormato);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		verticalBox_1.setBounds(525, 187, 124, 55);
		fondo.add(verticalBox_1);

		optNovedad = new JRadioButton("Novedad");
		optNovedad.setSelected(true);
		verticalBox_1.add(optNovedad);

		optReedicion = new JRadioButton("Reedicion");
		verticalBox_1.add(optReedicion);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Sylfaen", Font.BOLD | Font.ROMAN_BASELINE, 14));
		lblEstado.setBounds(525, 173, 46, 14);
		fondo.add(lblEstado);

		lblAnomalias = new JLabel("");
		lblAnomalias.setForeground(Color.RED);
		lblAnomalias.setFont(new Font("Tahoma", Font.BOLD | Font.ROMAN_BASELINE, 11));
		lblAnomalias.setBounds(10, 11, 648, 14);
		fondo.add(lblAnomalias);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setBounds(129, 321, 89, 48);
		fondo.add(btnModificar);



	}
}
