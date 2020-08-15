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
import modelo.LuceneSearchHighlighter;

import javax.swing.JTextField;
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
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.fredy.jsrt.api.SRTTimeFormat;

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

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador miCoordinador;
	static JComboBox comboBox;
	static Object[] elements;
	
	
	private PanelTexto panel1;
	public static JLabel lblResaltado ;
	public static JTable table = new JTable();
	private JTextField txtLinkYT;
	
	EventList<Object> autocompletado;
	
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
	 */
	public VentanaPrincipal() throws InvocationTargetException, InterruptedException {
		initComponents();
	}
	
	private void initComponents() throws InvocationTargetException, InterruptedException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 626);
		panel1 = new PanelTexto();
		
		setContentPane(panel1);
		SpringLayout sl_panel1 = new SpringLayout();
		panel1.setLayout(sl_panel1);
		
		final JTextField txtBuscarPalabras = new JTextField();
		sl_panel1.putConstraint(SpringLayout.WEST, txtBuscarPalabras, 200, SpringLayout.WEST, panel1);
		txtBuscarPalabras.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		panel1.add(txtBuscarPalabras);
		txtBuscarPalabras.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.NORTH, scrollPane, 428, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, scrollPane, 50, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, scrollPane, -39, SpringLayout.SOUTH, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, scrollPane, -50, SpringLayout.EAST, panel1);
		panel1.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtBuscarPalabras, -24, SpringLayout.NORTH, scrollPane_1);
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
		
		elements = new Object[] {"Cat", "Dog", "Lion", "Mouse"};
		
		
		Runnable doHelloWorld = new Runnable() {
		     public void run() {
		    	 AutoCompleteSupport autocomplete = AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(elements));
	        		autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);
		         System.out.println("Hello World on " + Thread.currentThread());
		     }
		 	
		 };
		 SwingUtilities.invokeLater(doHelloWorld);
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
		
		JButton btnLeerTexto = new JButton("Leer Texto");
		sl_panel1.putConstraint(SpringLayout.EAST, txtBuscarPalabras, -28, SpringLayout.WEST, btnLeerTexto);
		sl_panel1.putConstraint(SpringLayout.WEST, btnLeerTexto, 662, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, btnLeerTexto, -34, SpringLayout.NORTH, scrollPane_1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnLeerTexto, -144, SpringLayout.EAST, panel1);
		btnLeerTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Documento documento;
					try {
						miCoordinador.eliminarArchivosCarpeta("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados");
				
						//Ejecuta el highlither entregando la ruta donde se encuentran las palabras buscadas.
						subtitulos = miCoordinador.destacarPalabras(txtBuscarPalabras.getText(), subtitulos);
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
		sl_panel1.putConstraint(SpringLayout.NORTH, txtBuscarPalabras, 41, SpringLayout.SOUTH, txtLinkYT);
		sl_panel1.putConstraint(SpringLayout.WEST, txtLinkYT, 200, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, txtLinkYT, -294, SpringLayout.EAST, panel1);
		sl_panel1.putConstraint(SpringLayout.NORTH, txtLinkYT, 62, SpringLayout.NORTH, panel1);
		txtLinkYT.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		txtLinkYT.setColumns(10);
		panel1.add(txtLinkYT);
		
		JButton btnCargarVideo = new JButton("Cargar Video");
		sl_panel1.putConstraint(SpringLayout.NORTH, btnLeerTexto, 49, SpringLayout.SOUTH, btnCargarVideo);
		sl_panel1.putConstraint(SpringLayout.WEST, btnCargarVideo, 28, SpringLayout.EAST, txtLinkYT);
		sl_panel1.putConstraint(SpringLayout.EAST, btnCargarVideo, -144, SpringLayout.EAST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtLinkYT, 16, SpringLayout.SOUTH, btnCargarVideo);
		sl_panel1.putConstraint(SpringLayout.NORTH, btnCargarVideo, 72, SpringLayout.NORTH, panel1);
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
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel1.add(btnCargarVideo);
	
		
		
		
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
