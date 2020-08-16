package vista.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import de.tudarmstadt.ukp.wikipedia.api.Wikipedia;
import modelo.Contadores;
import modelo.Documento;
import modelo.LuceneSearchHighlighter;
import modelo.Subtitulos;
import modelo.TextPrompt;
import modelo.LuceneSearchHighlighter;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.fredy.jsrt.api.SRTTimeFormat;

import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.UniqueList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import vista.paneles.*;
import vista.ventanas.StationFinderAutoComplete.Station;
import vista.ventanas.StationFinderAutoComplete.StationTextFilterator;

import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador miCoordinador;
	static JComboBox comboBox;
	static Object[] elements;
	
	
	List<Object> listaTotalPalabras = new ArrayList<Object>();
	
	
	private PanelTexto panel1;
	public static JLabel lblResaltado ;
	public static JTable table = new JTable();
	private JTextField txtLinkYT;
	public static JRadioButton rdbtnEN;
	
	EventList<Object> autocomplete;
	
	ArrayList<Integer> posicion = new ArrayList<Integer>();
	ArrayList<String> listaSubtitulos = new ArrayList<String>();
	ArrayList<Integer> tiempoHoras = new ArrayList<Integer>();
	ArrayList<Integer> tiempoMinutos = new ArrayList<Integer>();
	ArrayList<Double> tiempoSegundos = new ArrayList<Double>();
	ArrayList<Integer> posicionResaltado = new ArrayList<Integer>();
	Subtitulos subtitulos = new Subtitulos(posicion, listaSubtitulos, tiempoHoras, 
			tiempoMinutos, tiempoSegundos, posicionResaltado);

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public VentanaPrincipal() throws InvocationTargetException, InterruptedException, FileNotFoundException, IOException {
		initComponents();
	}
	
	private void initComponents() throws InvocationTargetException, InterruptedException, FileNotFoundException, IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 626);
		panel1 = new PanelTexto();
		
		setContentPane(panel1);
		SpringLayout sl_panel1 = new SpringLayout();
		panel1.setLayout(sl_panel1);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.NORTH, scrollPane, 428, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, scrollPane, 50, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, scrollPane, -39, SpringLayout.SOUTH, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, scrollPane, -50, SpringLayout.EAST, panel1);
		panel1.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.NORTH, scrollPane_1, 207, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, scrollPane_1, -34, SpringLayout.NORTH, scrollPane);
		sl_panel1.putConstraint(SpringLayout.WEST, scrollPane_1, 50, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, scrollPane_1, -50, SpringLayout.EAST, panel1);
		panel1.add(scrollPane_1);
		
		final JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		sl_panel1.putConstraint(SpringLayout.NORTH, textPane, 243, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, textPane, 332, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, textPane, -63, SpringLayout.EAST, panel1);
		textPane.setContentType("text/html");
		sl_panel1.putConstraint(SpringLayout.SOUTH, textPane, -16, SpringLayout.NORTH, scrollPane);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		sl_panel1.putConstraint(SpringLayout.WEST, comboBox, 200, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, comboBox, -24, SpringLayout.NORTH, scrollPane_1);
	
		panel1.add(comboBox);

		table = new JTable();
		//table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String url;
				try {
					url = miCoordinador.convertirTiempoEnLink(table, txtLinkYT);
					miCoordinador.abrirURL(url);
				} catch (ParseException | URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		table.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Subt\u00EDtulo", "Tiempo"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(246);
		table.getColumnModel().getColumn(2).setPreferredWidth(118);
		//table.getColumnModel().getColumn(2).setPreferredWidth(132);
		scrollPane.setViewportView(table);
		
		/*JRadioButton rdbtnES = new JRadioButton("ES");
		sl_panel1.putConstraint(SpringLayout.EAST, rdbtnES, 0, SpringLayout.WEST, comboBox);
		rdbtnES.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		sl_panel1.putConstraint(SpringLayout.NORTH, rdbtnES, 163, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, rdbtnES, 155, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, rdbtnES, -24, SpringLayout.NORTH, scrollPane_1);
		panel1.add(rdbtnES);
		*/
		/*rdbtnEN = new JRadioButton("EN");
		rdbtnEN.setSelected(true);
		rdbtnEN.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		sl_panel1.putConstraint(SpringLayout.NORTH, rdbtnEN, 142, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, rdbtnEN, 155, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, rdbtnEN, -50, SpringLayout.NORTH, scrollPane_1);
		sl_panel1.putConstraint(SpringLayout.EAST, rdbtnEN, 0, SpringLayout.WEST, comboBox);
		panel1.add(rdbtnEN);
		*/
		/*ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnES);
		bg.add(rdbtnEN);
		*/
		JButton btnLeerTexto = new JButton("Leer Texto");
		btnLeerTexto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		sl_panel1.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.WEST, btnLeerTexto);
		sl_panel1.putConstraint(SpringLayout.SOUTH, btnLeerTexto, 0, SpringLayout.SOUTH, comboBox);
		sl_panel1.putConstraint(SpringLayout.NORTH, btnLeerTexto, 0, SpringLayout.NORTH, comboBox);
		sl_panel1.putConstraint(SpringLayout.WEST, btnLeerTexto, -294, SpringLayout.EAST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnLeerTexto, -144, SpringLayout.EAST, panel1);
		btnLeerTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Documento documento;
					try {
						textPane.setText("");
						miCoordinador.eliminarArchivosCarpeta("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados");
				
						//Ejecuta el highlither entregando la ruta donde se encuentran las palabras buscadas.
						subtitulos = miCoordinador.destacarPalabras(comboBox.getSelectedItem(), subtitulos, rdbtnEN);
						//Agrega al documento la cantidad de palabras que tienen los archivos donde se encuentran las palabras.
						//documento = miCoordinador.contarPalabras(documento);
						miCoordinador.limpiarTabla(table);
						//documento = miCoordinador.calcularTiempoLectura(documento);
						subtitulos = miCoordinador.agregarDatosFila(table, subtitulos);
						
						miCoordinador.leerHTML(textPane);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
		panel1.add(btnLeerTexto);
		
	
		txtLinkYT = new JTextField();
		
		TextPrompt placeholder = new TextPrompt("Copie y pegue el enlace aquí", txtLinkYT);
		placeholder.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
		//txtLinkYT.setToolTipText("Copie y pegue en enlace aquí");
		sl_panel1.putConstraint(SpringLayout.NORTH, comboBox, 39, SpringLayout.SOUTH, txtLinkYT);
		sl_panel1.putConstraint(SpringLayout.NORTH, txtLinkYT, 62, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, txtLinkYT, 200, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, txtLinkYT, -294, SpringLayout.EAST, panel1);
		txtLinkYT.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		txtLinkYT.setColumns(10);
		panel1.add(txtLinkYT);
		
		JButton btnCargarVideo = new JButton("Cargar Video");
		btnCargarVideo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		sl_panel1.putConstraint(SpringLayout.NORTH, btnCargarVideo, 62, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, btnCargarVideo, 0, SpringLayout.EAST, txtLinkYT);
		sl_panel1.putConstraint(SpringLayout.EAST, btnCargarVideo, -144, SpringLayout.EAST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtLinkYT, 0, SpringLayout.SOUTH, btnCargarVideo);
		sl_panel1.putConstraint(SpringLayout.SOUTH, btnCargarVideo, 103, SpringLayout.NORTH, panel1);
		btnCargarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String link = txtLinkYT.getText();
				try {
					
					//Borra los archivos de textos anteriores
					miCoordinador.eliminarArchivosCarpeta("E:\\Escritorio\\LuceneFinal\\CaptionsTXT");
					//Elimina los archivos indexados anteriores
					miCoordinador.eliminarArchivosCarpeta("E:\\Escritorio\\LuceneFinal\\Index");
					//Carga el video y descarga los archivos SRT
					miCoordinador.cargarVideo(link);
					//convierte el archivo SRT leído en archivos txt donde cada uno es una linea de subtítulo
					subtitulos = miCoordinador.leerSRT(panel1, subtitulos);
					for(int i = 0; i < subtitulos.getListSubtitulos().size(); i++) {
						miCoordinador.crearDocumento(subtitulos.getListSubtitulos().get(i),i, "txt");
					}
					//Indexa los archivos nuevos
					miCoordinador.escribirIndex();
					
					int cantidadDeSubtitulos = subtitulos.getListSubtitulos().size();
					listaTotalPalabras = miCoordinador.obtenerCantidadPalabras(cantidadDeSubtitulos);
					
					listaTotalPalabras = listaTotalPalabras.stream().distinct().collect(Collectors.toList());
					elements = new Object[listaTotalPalabras.size()];
					for(int i = 0; i< listaTotalPalabras.size();i++) {
						elements[i] = listaTotalPalabras.get(i);
					}
			
					Runnable crearSugerencia = new Runnable() {
					     public void run() {
					    	// autocomplete = new UniqueList<Object>(new BasicEventList<Object>());
					    	 AutoCompleteSupport autocomplete = AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(elements));
				        		autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);
					         //System.out.println("Hello World on " + Thread.currentThread());
					     }
					 	
					 };
					 SwingUtilities.invokeLater(crearSugerencia);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel1.add(btnCargarVideo);
		
		JLabel lblNewLabel = new JLabel("Palabras a buscar:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		sl_panel1.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, comboBox);
		sl_panel1.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, comboBox);
		panel1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Youtube Lucene");
		sl_panel1.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 15, SpringLayout.NORTH, panel1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel1.putConstraint(SpringLayout.WEST, lblNewLabel_1, 250, SpringLayout.WEST, scrollPane_1);
		sl_panel1.putConstraint(SpringLayout.EAST, lblNewLabel_1, -250, SpringLayout.EAST, scrollPane_1);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 26));
		panel1.add(lblNewLabel_1);
		
		
	
		
		
		
	}
	
	public  static void main(String[] args) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
