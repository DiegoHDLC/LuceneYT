package vista.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
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

import vista.paneles.*;

import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador miCoordinador;
	
	private PanelTexto panel1;
	public static JLabel lblResaltado ;
	public static JTable table = new JTable();
	private JTextField txtRuta;
	private JTextField txtLinkYT;
	
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		initComponents();
	}
	
	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 626);
		panel1 = new PanelTexto();
		//panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		SpringLayout sl_panel1 = new SpringLayout();
		panel1.setLayout(sl_panel1);
		
		final JTextField txtBuscarPalabras = new JTextField();
		txtBuscarPalabras.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		sl_panel1.putConstraint(SpringLayout.NORTH, txtBuscarPalabras, 147, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, txtBuscarPalabras, 160, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtBuscarPalabras, 208, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, txtBuscarPalabras, 602, SpringLayout.WEST, panel1);
		panel1.add(txtBuscarPalabras);
		txtBuscarPalabras.setColumns(10);
		
		txtRuta = new JTextField();
		sl_panel1.putConstraint(SpringLayout.NORTH, txtRuta, 80, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, txtRuta, 0, SpringLayout.WEST, txtBuscarPalabras);
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtRuta, -6, SpringLayout.NORTH, txtBuscarPalabras);
		sl_panel1.putConstraint(SpringLayout.EAST, txtRuta, 0, SpringLayout.EAST, txtBuscarPalabras);
		txtRuta.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		panel1.add(txtRuta);
		txtRuta.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.NORTH, scrollPane, 387, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, scrollPane, 63, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.SOUTH, scrollPane, -72, SpringLayout.SOUTH, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, scrollPane, -75, SpringLayout.EAST, panel1);
		panel1.add(scrollPane);
		
		JButton btnRuta = new JButton("Ruta");
		sl_panel1.putConstraint(SpringLayout.NORTH, btnRuta, 20, SpringLayout.NORTH, txtRuta);
		sl_panel1.putConstraint(SpringLayout.WEST, btnRuta, 6, SpringLayout.EAST, txtRuta);
		sl_panel1.putConstraint(SpringLayout.EAST, btnRuta, 697, SpringLayout.WEST, panel1);
		btnRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//miCoordinador.eliminarIndex();
				miCoordinador.agregarRuta(panel1, txtRuta);
			}
		});
		panel1.add(btnRuta);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_panel1.putConstraint(SpringLayout.NORTH, scrollPane_1, 36, SpringLayout.SOUTH, txtBuscarPalabras);
		sl_panel1.putConstraint(SpringLayout.WEST, scrollPane_1, 0, SpringLayout.WEST, txtBuscarPalabras);
		sl_panel1.putConstraint(SpringLayout.SOUTH, scrollPane_1, -28, SpringLayout.NORTH, scrollPane);
		sl_panel1.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, txtBuscarPalabras);
		panel1.add(scrollPane_1);
		
		final JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		sl_panel1.putConstraint(SpringLayout.NORTH, textPane, 243, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, textPane, 332, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, textPane, -63, SpringLayout.EAST, panel1);
		textPane.setContentType("text/html");
		sl_panel1.putConstraint(SpringLayout.SOUTH, textPane, -16, SpringLayout.NORTH, scrollPane);
		
	
		
		
		
		
		table = new JTable();
		//table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				
				String tiempoString = model.getValueAt(index, 2).toString();
				String [] valores = tiempoString.split(":");
				
				double horas = Double.parseDouble(valores[0]);
	        	double minutos = Double.parseDouble(valores[1]);
	        	NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
	        	try {
					double segundos = nf.parse(valores[2]).doubleValue();
					int segundosInt = (int) segundos;
					System.out.println("Segundos enteros: "+segundosInt);
					
					System.out.println("Horas: "+horas+" minutos: "+minutos+" segundos: "+segundos);
					if(segundos>=10000) {
						segundos = segundos/1000;
					}else {
						segundos = segundos/100;
					}
					double tiempo = (horas*360)+(minutos*60)+(segundos);
					System.out.println("tiempo total: "+tiempo+" segundos");
					double parteDecimal = tiempo % 1;
					double parteEntera = tiempo - parteDecimal;
					int tiempoFinal = (int) parteEntera;
					System.out.println("tiempo a poner en el link: "+tiempoFinal);
					
					String url = txtLinkYT.getText()+"&t="+tiempoFinal+"s";
					miCoordinador.abrirURL(url);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
				System.out.println("Valor del index: "+index);
				//System.out.println(subtitulos.getTiempoHoras().get(subtitulos.getPosicionResaltado().get(0)));
				//String url = miCoordinador.convertirTiempoEnLink(index,subtitulos
					//	,txtLinkYT.getText());
			
				
				
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
		sl_panel1.putConstraint(SpringLayout.NORTH, btnLeerTexto, 185, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, btnLeerTexto, 612, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnLeerTexto, 701, SpringLayout.WEST, panel1);
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
		
		JButton btnNewButton = new JButton("Leer PDF");
		sl_panel1.putConstraint(SpringLayout.NORTH, btnNewButton, 151, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, btnNewButton, 612, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnNewButton, 701, SpringLayout.WEST, panel1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//String texto = miCoordinador.leerPDF(txtBuscarPalabras.getText());
					//textPane.setText(texto);
					//miCoordinador.crearDocumento(texto,"txt");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel1.add(btnNewButton);
		
		
		txtLinkYT = new JTextField();
		sl_panel1.putConstraint(SpringLayout.NORTH, txtLinkYT, 10, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, txtLinkYT, 0, SpringLayout.WEST, txtBuscarPalabras);
		sl_panel1.putConstraint(SpringLayout.SOUTH, txtLinkYT, -9, SpringLayout.NORTH, txtRuta);
		sl_panel1.putConstraint(SpringLayout.EAST, txtLinkYT, 0, SpringLayout.EAST, txtBuscarPalabras);
		txtLinkYT.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		txtLinkYT.setColumns(10);
		panel1.add(txtLinkYT);
		
		JButton btnCargarVideo = new JButton("Cargar Video");
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
		sl_panel1.putConstraint(SpringLayout.NORTH, btnCargarVideo, 28, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, btnCargarVideo, 6, SpringLayout.EAST, txtLinkYT);
		sl_panel1.putConstraint(SpringLayout.EAST, btnCargarVideo, 146, SpringLayout.EAST, txtLinkYT);
		panel1.add(btnCargarVideo);
		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
