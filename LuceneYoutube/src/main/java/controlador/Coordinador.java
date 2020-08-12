package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.google.api.services.samples.youtube.cmdline.data.Captions;

import vista.paneles.*;

import jdk.nashorn.internal.runtime.ListAdapter;
import modelo.Contadores;
import modelo.Documento;
import modelo.Logica;
import modelo.LuceneSearchHighlighter;
import modelo.LuceneWriteIndexFromFile;
import modelo.Subtitulos;
//import modelo.PDFBoxReadFromFile;
//import modelo.Traductor;
import vista.ventanas.VentanaPrincipal;
import vista.ventanas.YoutubeViewer;

public class Coordinador {
	
	private PanelTexto panel1;
	//private PDFBoxReadFromFile PDFReader;
	private VentanaPrincipal ventana;
	private LuceneSearchHighlighter destacadorPalabras;
	private String text = null;
	//private Traductor traduct;
	private Contadores contadores;
	private Documento documento;
	private Logica logica;
	private LuceneWriteIndexFromFile escribirIndex;
	private Captions captions;
	private YoutubeViewer ventanaYoutube;
	
	public Coordinador() {
		
	}

	public void setPanel1(PanelTexto panel1) {
		this.panel1 = panel1;
		
	}

	//public void setPDFBoxReadFromFile(PDFBoxReadFromFile PDFReader) {
		//this.PDFReader = PDFReader;
		
//	}

	public void setVentanaPrincipal(VentanaPrincipal ventana) {
		this.ventana = ventana;
		
	}

	public void setLuceneSearchHighlighter(LuceneSearchHighlighter destacadorPalabras) {
		this.destacadorPalabras = destacadorPalabras;
	}

	//public void setTraductor(Traductor traduct) {
		//this.traduct = traduct;
		
	//}
	
	public void setContadores(Contadores contadores) {
		this.contadores = contadores;
		
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public void setLogica(Logica logica) {
		this.logica = logica;
		
	}
	
	/*public Contadores leerTexto(JEditorPane area2) throws FileNotFoundException, IOException {
		Contadores contadores;
		documento.leerHTML(area2);
		//contadores = documento.ContadorPalabrasYNumeros();
		
		return contadores;
	}*/
	
	@SuppressWarnings("static-access")
	public File crearDocumento(String frag, int i, String formato) {
		File file;
		file = documento.crearDocumento(frag,i,formato);
		return file;
	}

	public int sumar(String num1, String num2) {
		return contadores.sumar(num1,num2);
	}

	@SuppressWarnings("static-access")
	public Subtitulos destacarPalabras(String txtBuscarPalabras, Subtitulos subtitulos) throws Exception {
		Documento documento;
		subtitulos = destacadorPalabras.ejecutarHighlighter(txtBuscarPalabras, subtitulos);
		return subtitulos;
		
	}

	//public String leerPDF(String text) throws Exception {
		//String texto = PDFReader.leerArchivoPDF();
		//crearDocumento(text, "txt");
		//return texto;
		
//	}

	public Documento contarPalabras(Documento documento) throws FileNotFoundException, IOException {
		ArrayList<String> listaRuta = documento.getRuta();
		documento = contadores.contarPalabrasArchivo(documento);
		return documento;
	}

	public Subtitulos agregarDatosFila(JTable table, Subtitulos subtitulos) {
		//limpiarTabla(table);
		logica.agregarDatosAFila(table, subtitulos);
		subtitulos.getPosicionResaltado().clear();
		return subtitulos;
	}

	public void leerHTML(JTextPane textPane) {
		documento.leerHTML(textPane);
		
	}

	public void agregarRuta(PanelTexto panel1, JTextField txtRuta) {
		logica.agregarRutaArchivos(panel1, txtRuta);
		
		escribirIndex.escribirIndexDesdeArchivo(txtRuta.getText());
		
	}

	public Documento calcularTiempoLectura(Documento documento) {
		documento = logica.calcularTiempoLectura(documento);
		return documento;
	}

	public void limpiarTabla(JTable table) {
		logica.limpiarTabla(table);
		
	}

	public void setEscribirIndex(LuceneWriteIndexFromFile escribirIndex) {
		this.escribirIndex = escribirIndex;
		
	}

	public void eliminarArchivosCarpeta(String directorioCarpeta) {
		logica.eliminarArchivosCarpeta(directorioCarpeta);
		
	}

	public Subtitulos leerSRT(PanelTexto panel1, Subtitulos subtitulos) throws ParseException {
		Subtitulos sub;
		sub = logica.leerSRT(panel1, subtitulos);
		return sub;
	}

	public void setCaptions(Captions captions) {
		this.captions = captions;
		
	}

	@SuppressWarnings("static-access")
	public void cargarVideo(String linkVideo) throws IOException {
		captions.descargarSubtitulos(captions.getVideoId(linkVideo));
		
	}

	public void escribirIndex() {
		escribirIndex.escribirIndexDesdeArchivo("E:\\Escritorio\\LuceneFinal\\CaptionsTXT");
		
	}

	public void setVentanaYoutube(YoutubeViewer ventanaYoutube) {
		this.ventanaYoutube = ventanaYoutube;
		
	}

	

	

	
	

}
