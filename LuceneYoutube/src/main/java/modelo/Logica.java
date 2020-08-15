package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Driver;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.fredy.jsrt.api.SRT;
import org.fredy.jsrt.api.SRTInfo;
import org.fredy.jsrt.api.SRTReader;
import org.fredy.jsrt.api.SRTTimeFormat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import vista.paneles.*;
import vista.ventanas.StationFinderAutoComplete;
import controlador.Coordinador;

public class Logica {
	DefaultTableModel modelo = new DefaultTableModel();
	JFileChooser chooser;
	String choosertitle;
	//JTable table = new JTable(modelo);
	
	public Logica() {
		// TODO Auto-generated constructor stub
	}
	
	public void setCoordinador(Coordinador miCoordinador) {
		// TODO Auto-generated method stub
		
	}

	public void agregarDatosAFila(JTable table, Subtitulos subtitulos) {
		
		for(int i = 0; i <subtitulos.posicionResaltado.size();i++) {
			int numCols = table.getModel().getColumnCount();
			Object [] fila = new Object[numCols]; 
	         fila[0] = i+1;
			 fila[1] = subtitulos.getListSubtitulos().get(subtitulos.posicionResaltado.get(i)-1);
			 fila[2] = subtitulos.getTiempoHoras().get(subtitulos.posicionResaltado.get(i)-1)+":"+
			 subtitulos.getTiempoMinutos().get(subtitulos.posicionResaltado.get(i)-1)+":"+
			 subtitulos.tiempoSegundos.get(subtitulos.posicionResaltado.get(i)-1);
			 
			 ((DefaultTableModel) table.getModel()).addRow(fila);
		}
		
		
	}

	public void agregarRutaArchivos(PanelTexto panel, JTextField txtRuta) {
		chooser = new JFileChooser(); 
	       chooser.setCurrentDirectory(new java.io.File("."));
	       chooser.setDialogTitle(choosertitle);
	       chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	       //
	       // disable the "All files" option.
	       //
	       chooser.setAcceptAllFileFilterUsed(false);
	       //    
	       if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
	    	 eliminarArchivosCarpeta("E:\\Escritorio\\LuceneFinal\\Index");
	         System.out.println("getCurrentDirectory(): " 
	            +  chooser.getCurrentDirectory());
	         System.out.println("getSelectedFile() : " 
	            +  chooser.getSelectedFile());
	         txtRuta.setText(""+chooser.getSelectedFile());
	         }
	       else {
	         System.out.println("No Selection ");
	         }
		
	}

	public Documento calcularTiempoLectura(Documento documento) {
		
		for(int i = 0; i < documento.ruta.size();i++) {
			int cantidadPalabras = documento.cantPalabras.get(i);
			
			System.out.println("cantida de palabras en funcion tiempo:"+cantidadPalabras);
			int tiempo = cantidadPalabras/200;
			double parteDecimal = tiempo % 1;
			double parteEntera = tiempo - parteDecimal;
			double segundos =(parteDecimal*0.6*100);
			double minutos = parteEntera;
			if(segundos > 30) {
				minutos++;
			}
			
			documento.lecturaMinutos.add(minutos);
			System.out.println("Minutos:"+minutos);
			System.out.println("Segundos:"+segundos);
			System.out.println("mintosDouble:"+minutos);
			//documento.lecturaMinutos.add(minutos);
		}
		return documento;
	}

	public void limpiarTabla(JTable table) {
		System.out.println("ENTRA A LIMPIAR");
		try {
            DefaultTableModel modelo=(DefaultTableModel) table.getModel();
            int filas=table.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
		
	}

	public void eliminarArchivosCarpeta(String directorioCarpeta) {
		File file = new File(directorioCarpeta);      
	      String[] myFiles;    
	          if(file.isDirectory()){
	              myFiles = file.list();
	              for (int i=0; i<myFiles.length; i++) {
	                  File myFile = new File(file, myFiles[i]); 
	                  myFile.delete();
	              }
	           }
		
	}

	public Subtitulos leerSRT(PanelTexto panel1, Subtitulos subtitulos) throws ParseException {
		Subtitulos sub;
		SRTInfo info = SRTReader.read(new File("E:\\Escritorio\\LuceneFinal\\CaptionsSRT\\CaptionsEN.srt"));
		sub = print(info, subtitulos);
		
		//for(int i = 0; i < listaSubtitulos.size(); i++) {
			//System.out.println(i+" "+listaSubtitulos.get(i));
		//}

		return sub;
	}
	
	private static Subtitulos print(SRTInfo info, Subtitulos subtitulos) throws ParseException {
		ArrayList<String> listaSubtitulos = new ArrayList<String>();
        for (SRT s : info) {
        	//subtitulos.posicion.add(s.number);
        	String tiempoString = SRTTimeFormat.format(s.startTime);
        	
        	String [] valores = tiempoString.split(":");
        	
        	int hora = Integer.parseInt(valores[0]);
        	int minutos = Integer.parseInt(valores[1]);
        	NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
        	double segundos = nf.parse(valores[2]).doubleValue();
        	
        	
        	//System.out.println("valores del string: "+hora +" - "+minutos + " - "+segundos);
        	
        	subtitulos.tiempoHoras.add(hora);
        	subtitulos.tiempoMinutos.add(minutos);
        	subtitulos.tiempoSegundos.add(segundos);
        	//subtitulos.tiempoMinutos.add(SRTTimeFormat.format(s.startTime));
           System.out.println("Number: " + s.number);
            System.out.println("Start time: " + SRTTimeFormat.format(s.startTime));
           System.out.println("End time: " + SRTTimeFormat.format(s.endTime));
           System.out.println("Texts:");
            for (String line : s.text) {
            	listaSubtitulos.add(line);
            	subtitulos.listSubtitulos.add(line);
            	//subtitulos.listSubtitulos.add(line);
                System.out.println("    " + line);
            }
           // System.out.println();
        }
		
        return subtitulos;
	}

	public void abrirURL(String URL) throws java.net.URISyntaxException {
		
		 if (java.awt.Desktop.isDesktopSupported()) {
	            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

	            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
	                try {
	                    java.net.URI uri = new java.net.URI(URL);
	                    desktop.browse(uri);
	                } catch ( IOException ex) {
	                   // Logger.getLogger(Acerca.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	}

	/*public String convervirTiempoEnLink(int index, Subtitulos subtitulos, String url) {
		System.out.println("Valor index en conversor: "+index);
		System.out.println("Valor de la posicion Resaltado: "+subtitulos.posicionResaltado.get(index));
		double horas = subtitulos.tiempoHoras.get(subtitulos.posicionResaltado.get(index));
		double minutos = subtitulos.tiempoMinutos.get(subtitulos.posicionResaltado.get(index));
		double segundos = subtitulos.tiempoSegundos.get(subtitulos.posicionResaltado.get(index));
		double tiempo = (horas*360)+(minutos*60)+segundos;
		url = url+"&t="+tiempo+"s";
		return url;
	}*/
	

	public String convertirTiempoEnLink(JTable table, JTextField txtLinkYT ) throws ParseException {
		int index = table.getSelectedRow();
		TableModel model = table.getModel();
		
		String tiempoString = model.getValueAt(index, 2).toString();
		String [] valores = tiempoString.split(":");
		
		double horas = Double.parseDouble(valores[0]);
    	double minutos = Double.parseDouble(valores[1]);
    	NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
    	
			double segundos = nf.parse(valores[2]).doubleValue();
			int segundosInt = (int) segundos;
			System.out.println("Segundos enteros: "+segundosInt);
			
			System.out.println("Horas: "+horas+" minutos: "+minutos+" segundos: "+segundos);
			if(segundos>=10000) {
				segundos = segundos/1000;
			}else { 
				if(segundos >=1000){
					segundos = segundos/100;
				}
				else {
					if(segundos >= 100) {
					segundos = segundos/10;
					}
				}
			}
			double tiempo = (horas*360)+(minutos*60)+(segundos);
			System.out.println("tiempo total: "+tiempo+" segundos");
			double parteDecimal = tiempo % 1;
			double parteEntera = tiempo - parteDecimal;
			int tiempoFinal = (int) parteEntera;
			System.out.println("tiempo a poner en el link: "+tiempoFinal);
				
			String url = txtLinkYT.getText()+"&t="+tiempoFinal+"s";
			
			return url;
		
	}

	/*public void hiloAutocompletado(final JComboBox comboBox, final Object[] elements) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	AutoCompleteSupport autocomplete = AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(elements));
        		autocomplete.setFilterMode(TextMatcherEditor.CONTAINS);
            }
        });
		
		
	}
	*/

}
