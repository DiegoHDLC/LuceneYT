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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.paneles.*;

import javax.swing.SpringLayout;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinador miCoordinador;
	
	private PanelTexto panel1;
	public static JLabel lblResaltado ;
	private JTable table;
	private JTextField txtRuta;
	private JTextField txtLinkYT;

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
		table.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ruta", "Cantidad de Palabras", "Tiempo Estimado Lectura"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(246);
		table.getColumnModel().getColumn(1).setPreferredWidth(118);
		table.getColumnModel().getColumn(2).setPreferredWidth(132);
		scrollPane.setViewportView(table);
		
		JButton btnLeerTexto = new JButton("Leer Texto");
		sl_panel1.putConstraint(SpringLayout.NORTH, btnLeerTexto, 185, SpringLayout.NORTH, panel1);
		sl_panel1.putConstraint(SpringLayout.WEST, btnLeerTexto, 612, SpringLayout.WEST, panel1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnLeerTexto, 701, SpringLayout.WEST, panel1);
		btnLeerTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Documento documento;
					try {
						miCoordinador.limpiarTabla(table);
						//Ejecuta el highlither entregando la ruta donde se encuentran las palabras buscadas.
						documento = miCoordinador.destacarPalabras(txtBuscarPalabras.getText());
						//Agrega al documento la cantidad de palabras que tienen los archivos donde se encuentran las palabras.
						documento = miCoordinador.contarPalabras(documento);
						
						documento = miCoordinador.calcularTiempoLectura(documento);
						miCoordinador.agregarDatosFila(table, documento);
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
		
		JButton btnLeerSRT = new JButton("Leer SRT");
		btnLeerSRT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> listaSubtitulos = new ArrayList<String>();
				listaSubtitulos = miCoordinador.leerSRT(panel1);
				for(int i = 0; i < listaSubtitulos.size(); i++) {
					
					miCoordinador.crearDocumento(listaSubtitulos.get(i),i, "txt");
				}
				
			}
		});
		sl_panel1.putConstraint(SpringLayout.NORTH, btnLeerSRT, 0, SpringLayout.NORTH, scrollPane_1);
		sl_panel1.putConstraint(SpringLayout.EAST, btnLeerSRT, 0, SpringLayout.EAST, scrollPane);
		panel1.add(btnLeerSRT);
		
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
				ArrayList<String> listaSubtitulos = new ArrayList<String>();
				String link = txtLinkYT.getText();
				try {
					//Carga el video y descarga los archivos SRT
					miCoordinador.cargarVideo(link);
					//convierte el archivo SRT leído en archivos txt donde cada uno es una linea de subtítulo
					listaSubtitulos = miCoordinador.leerSRT(panel1);
					for(int i = 0; i < listaSubtitulos.size(); i++) {
						miCoordinador.crearDocumento(listaSubtitulos.get(i),i, "txt");
					}
					miCoordinador.eliminarIndex();
					miCoordinador.escribirIndex();
				} catch (IOException e) {
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
