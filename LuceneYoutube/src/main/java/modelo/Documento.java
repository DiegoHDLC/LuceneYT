package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import javax.swing.JEditorPane;

import com.sun.javafx.geom.transform.CanTransformVec3d;

import controlador.Coordinador;
import jdk.nashorn.internal.runtime.ListAdapter;

public class Documento {
	private Coordinador miCoordinador;
	private static FileReader fr;
	private static File file;
	public static int i = 0;
	public static int j = 0;
	ArrayList<String> ruta;
	ArrayList<Integer> cantPalabras;
	ArrayList<Double> lecturaMinutos;
	ArrayList<Integer> lecturaSegundos;
	
	public Documento(ArrayList<String> ruta, ArrayList<Integer> catPalabras, ArrayList<Double> lecturaMinutos) {
		this.ruta = ruta;
		this.cantPalabras = catPalabras;
		this.lecturaMinutos = lecturaMinutos;
	}
	public void setRuta(ArrayList<String> ruta) {
		this.ruta = ruta;
	}
	
	public ArrayList<String> getRuta() {
		return ruta;
	}
	
	public ArrayList<Integer> getCantPalabras() {
		return cantPalabras;
	}
	public void setCantPalabras(ArrayList<Integer> cantPalabras) {
		this.cantPalabras = cantPalabras;
	}
	public ArrayList<Double> getLecturaMinutos() {
		return lecturaMinutos;
	}
	
	public void setLecturaMinutos(ArrayList<Double> lecturaMinutos) {
		this.lecturaMinutos = lecturaMinutos;
	}
	
	
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	public void leerHTML(JEditorPane area2) {
		
		String content = "";
	    try {
	    	//int j = cantidadDeArchivosHTML() ;
	    	
	        BufferedReader in = new BufferedReader(new FileReader("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados\\archivoResaltado["+0+"].html"));
	        String str;
	        while ((str = in.readLine()) != null) {
	            content +=str;
	        }
	        area2.setText(content);
	        in.close();
	        
	    } catch (IOException e) {
	    }
	}
	
	public static int cantidadDeArchivosHTML() {
		File carpeta = new File("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados"); 
		File[] lista = carpeta.listFiles();
		return lista.length;
	}
	
	public static int cantidadDeArchivosTXT() {
		File carpeta = new File("E:\\Escritorio\\LuceneFinal\\Archivos"); 
		File[] lista = carpeta.listFiles();
		return lista.length;
	}
	
	
	
	
	public static File crearDocumento(String frag, int i, String formato) {
    	BufferedWriter bw = null;
        FileWriter fw = null;
        
        try {
        	
            String data = frag;
            if(formato == "txt") {
            	 file = new File("E:\\Escritorio\\LuceneFinal\\CaptionsTXT\\subLinea_"+(i+1)+"."+formato);
            }else {
            	file = new File("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados\\archivoResaltado["+i+"]."+formato);
            }
            
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar informaci�n al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
          
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                            //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return file;
    }
	
	public void leerArchivo(JEditorPane area2) {
		area2.setText("");
		
		try {
			File archivo = new File("E:\\Escritorio\\LuceneFinal\\Archivos Resaltados\\archivoResaltado.html");
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			//Lectura del fichero
			String linea;
			while((linea = br.readLine()) != null) {

				area2.setText(area2.getText() + linea + "\n");
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != fr) {
					fr.close();
				}
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
