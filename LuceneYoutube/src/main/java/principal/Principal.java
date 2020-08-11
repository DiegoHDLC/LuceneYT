package principal;
import vista.paneles.PanelTexto;
import vista.ventanas.VentanaPrincipal;

import java.util.ArrayList;

import javax.swing.UIManager;

import com.google.api.services.samples.youtube.cmdline.*;
import com.google.api.services.samples.youtube.cmdline.data.Captions;

import controlador.Coordinador;
import modelo.Contadores;
import modelo.Documento;
import modelo.Logica;
import modelo.LuceneSearchHighlighter;
import modelo.LuceneWriteIndexFromFile;
import modelo.LuceneSearchHighlighter;
//import modelo.PDFBoxReadFromFile;
//import modelo.Traductor;

public class Principal {
	public  static void main(String[] args) throws Exception {
		
		javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ArrayList<String> arrayList = null;
		ArrayList<Integer> arrayListInt = null;
		ArrayList<Double> arrayTMinutos = null;
		
		Coordinador miCoordinador = new Coordinador();
		PanelTexto panel1 = new PanelTexto();
		//PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile();
		VentanaPrincipal ventana = new VentanaPrincipal();
		LuceneSearchHighlighter destacadorPalabras = new LuceneSearchHighlighter();
		//Traductor traduct = new Traductor();
		Contadores contadores = new Contadores(arrayListInt);
		Documento documento = new Documento(arrayList, arrayListInt,arrayTMinutos);
		Logica logica = new Logica();
		LuceneWriteIndexFromFile escribirIndex = new LuceneWriteIndexFromFile();
		Captions captions = new Captions();
		
		panel1.setCoordinador(miCoordinador);
		//PDFReader.setCoordinador(miCoordinador);
		ventana.setCoordinador(miCoordinador);
		destacadorPalabras.setCoordinador(miCoordinador);
		//traduct.setCoordinador(miCoordinador);
		contadores.setCoordinador(miCoordinador);
		documento.setCoordinador(miCoordinador);
		logica.setCoordinador(miCoordinador);
		escribirIndex.setCoordinador(miCoordinador);
		captions.setCoordinador(miCoordinador);
		
		
		
		miCoordinador.setPanel1(panel1);
		//miCoordinador.setPDFBoxReadFromFile(PDFReader);
		miCoordinador.setVentanaPrincipal(ventana);
		miCoordinador.setLuceneSearchHighlighter(destacadorPalabras);
		//miCoordinador.setTraductor(traduct);
		miCoordinador.setContadores(contadores);
		miCoordinador.setDocumento(documento);
		miCoordinador.setLogica(logica);
		miCoordinador.setEscribirIndex(escribirIndex);
		miCoordinador.setCaptions(captions);
		
		ventana.setVisible(true);
	}
}
