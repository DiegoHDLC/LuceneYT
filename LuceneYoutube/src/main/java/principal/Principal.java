package principal;
import vista.paneles.PanelTexto;
import vista.ventanas.StationFinderAutoComplete;
import vista.ventanas.VentanaPrincipal;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.UIManager;

import com.google.api.services.samples.youtube.cmdline.data.Captions;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import controlador.Coordinador;
import modelo.Contadores;
import modelo.Documento;
import modelo.Logica;
import modelo.LuceneSearchHighlighter;
import modelo.LuceneWriteIndexFromFile;

public class Principal {
	public  static void main(String[] args) throws Exception {
		
		
			
			
		
           
		javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ArrayList<String> arrayList = null;
		ArrayList<Integer> arrayListInt = null;
		ArrayList<Double> arrayTMinutos = null;
		
		Coordinador miCoordinador = new Coordinador();
		
		
		LuceneSearchHighlighter destacadorPalabras = new LuceneSearchHighlighter();
		Contadores contadores = new Contadores(arrayListInt);
		Documento documento = new Documento(arrayList, arrayListInt,arrayTMinutos);
		Logica logica = new Logica();
		LuceneWriteIndexFromFile escribirIndex = new LuceneWriteIndexFromFile();
		Captions captions = new Captions();
	
		VentanaPrincipal ventana = new VentanaPrincipal();
		
		
		ventana.setCoordinador(miCoordinador);
		destacadorPalabras.setCoordinador(miCoordinador);
		contadores.setCoordinador(miCoordinador);
		documento.setCoordinador(miCoordinador);
		logica.setCoordinador(miCoordinador);
		escribirIndex.setCoordinador(miCoordinador);
		captions.setCoordinador(miCoordinador);
		
		
		
		
		miCoordinador.setVentanaPrincipal(ventana);
		miCoordinador.setLuceneSearchHighlighter(destacadorPalabras);
		miCoordinador.setContadores(contadores);
		miCoordinador.setDocumento(documento);
		miCoordinador.setLogica(logica);
		miCoordinador.setEscribirIndex(escribirIndex);
		miCoordinador.setCaptions(captions);
		
		ventana.setVisible(true);
		
		
	}
}
