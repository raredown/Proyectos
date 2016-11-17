package biblioteca;




import java.awt.EventQueue;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Biblioteca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fondo;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtNumPaginas;
	private DefaultListModel<String> modelo = new DefaultListModel<>();
	private JList<String> lstDisponible;
	private ButtonGroup grupo;
	private JRadioButton optNovedad;
	private JRadioButton optReedicion;
	private JLabel lblAnomalias;
	private JComboBox<String> cmbTema;
	private JCheckBox chkCartone;
	private JCheckBox chkRustica;
	private JCheckBox chkTapaDura;
	private Estanteria gestion;
	private String[] elementosLista = {"", "Accion", "Aventuras", "Biografia", "Comedia", "Terror", "Ciencia-Ficcion", "Comics"};
	private JButton btnBajas;
	
	
	/**
	 * Este metodo prepara todo los datos para despues mostrarlo, recoge toda la iformacion de cargarDatos() de la clase Estanteria
	 * y despues de recoger todo los datos llama a mostrarDatos()
	 */
	public void prepararDatos(){
		Object[] objeto;
		objeto = gestion.cargarDatos(lstDisponible.getSelectedValue());
		if(objeto != null){
			String titulo = (String) objeto[0];
			String autor = (String) objeto[1];
			String tema = (String) objeto[2];
			int numPaginas = (int) objeto[3];
			String []formato = (String[]) objeto[4];
			String estado = (String) objeto[5];
			mostrarDatos(titulo, autor,tema,numPaginas,formato,estado);
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
		
		if(formato[0] != null)
			chkCartone.setSelected(true);
		if(formato[1] != null)
			chkRustica.setSelected(true);
		if(formato[2] != null)
			chkTapaDura.setSelected(true);
		if(estado == "novedad"){
			optNovedad.setSelected(true);
		}else{
			optReedicion.setSelected(true);
		}
		
	}//mostrarDatos()

	/**
	 * Este metodo va a comprobar que todos los campos esten relleno, para que cuando se llame al metodo de altas no genere errores
	 */
	private void comprobarDatos(){
		
		String titulo = txtTitulo.getText();
		String autor = txtAutor.getText();
		String numeroPagina = txtNumPaginas.getText();
		String tema = cmbTema.getSelectedItem().toString();
		String []formato = new String [3];
		String estado;
		
		if(autor.compareTo("")!=0 && titulo.compareTo("")!=0 && numeroPagina.compareTo("")!=0 && tema.compareTo("")!=0){
			anadirALaLista(titulo);
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
			
			gestion.altas(titulo, autor, tema, numeroPagina, formato, estado);
			limpiarDatos();
			
		}
		else{
			lblAnomalias.setText("Faltan Datos");
		}
	}//ComprobarDatos
	
	
	/**
	 * Este metodo añade el titulo al la lista, lstDisponible
	 * @param titulo
	 */
	private void anadirALaLista(String titulo) {
		modelo.addElement(titulo);

	}//anadirALaLista
	
	/**
	 * Este metodo se encarga de inicializar el objeto estanteria y de crear el grupo de opciones, este metodo es llamado desde el main
	 * despues de iniciar la pantalla es decir el frame
	 */
	public void iniciar(){
		gestion = new Estanteria();
		grupo = new ButtonGroup();
		grupo.add(optNovedad);
		grupo.add(optReedicion);
	}
	
	/**
	 * Este metodo limpia los datos de la pantalla
	 */
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
	
	public void eliminarDatos(){
		if(gestion.borrarDatos(txtTitulo.getText())){
			modelo.removeElement(txtTitulo.getText());
			limpiarDatos();
		}else{
			lblAnomalias.setText("No emos podido borrar los datos, ay algun problema en la clase Estanteria");
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Biblioteca frame = new Biblioteca();
					frame.iniciar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Biblioteca() {
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
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarDatos();
			}
		});
		btnNuevo.setBounds(62, 321, 89, 48);
		fondo.add(btnNuevo);
		
		btnBajas = new JButton("Bajas");
		btnBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarDatos();
			}
		});
		btnBajas.setEnabled(false);
		btnBajas.setBounds(197, 321, 89, 48);
		fondo.add(btnBajas);
		
		JButton btnAltas = new JButton("Altas");
		btnAltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprobarDatos();
			}
		});
		btnAltas.setBounds(62, 389, 89, 48);
		fondo.add(btnAltas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(197, 389, 89, 48);
		fondo.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 281, 258, 156);
		fondo.add(scrollPane);
		
		
		lstDisponible = new JList<>(modelo);
		lstDisponible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				prepararDatos();
			}
		});
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
		
		
		
	}
}
