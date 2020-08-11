package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.fredy.jsrt.api.SRT;
import org.fredy.jsrt.api.SRTInfo;
import org.fredy.jsrt.api.SRTReader;
import org.fredy.jsrt.api.SRTTimeFormat;

import vista.paneles.*;

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

	public void agregarDatosAFila(JTable table, Documento documento) {
		
		for(int i = 0; i < documento.getRuta().size();i++) {
			int numCols = table.getModel().getColumnCount();
			Object [] fila = new Object[numCols]; 
	        
			 fila[0] = documento.getRuta().get(i);
			 fila[1] = documento.getCantPalabras().get(i);
			 if(documento.getLecturaMinutos().get(i) < 1) {
				 fila[2] = "< 1 minuto";
			 }else {
				 fila[2] = documento.getLecturaMinutos().get(i)+"minuto(s)";
			 }
			 //fila[2] = "mundo";
			 
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
	    	   eliminarIndex();
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

	public void eliminarIndex() {
		File file = new File("E:\\Escritorio\\LuceneTest2\\Index");      
	      String[] myFiles;    
	          if(file.isDirectory()){
	              myFiles = file.list();
	              for (int i=0; i<myFiles.length; i++) {
	                  File myFile = new File(file, myFiles[i]); 
	                  myFile.delete();
	              }
	           }
		
	}

	public ArrayList<String> leerSRT(PanelTexto panel1) {
		ArrayList<String> listaSubtitulos = new ArrayList<String>();
		SRTInfo info = SRTReader.read(new File("E:\\Escritorio\\LuceneTest2\\CaptionsSRT\\CaptionsEN.srt"));
		listaSubtitulos = print(info);
		
		for(int i = 0; i < listaSubtitulos.size(); i++) {
			System.out.println(i+" "+listaSubtitulos.get(i));
		}
		
		return listaSubtitulos;
	
	 
	}
	
	private static ArrayList<String> print(SRTInfo info) {
		ArrayList<String> listaSubtitulos = new ArrayList<String>();
        for (SRT s : info) {
            System.out.println("Number: " + s.number);
            System.out.println("Start time: " + SRTTimeFormat.format(s.startTime));
            System.out.println("End time: " + SRTTimeFormat.format(s.endTime));
            System.out.println("Texts:");
            for (String line : s.text) {
            	listaSubtitulos.add(line);
                System.out.println("    " + line);
            }
            System.out.println();
        }
		
        return listaSubtitulos;
	}
	

}
