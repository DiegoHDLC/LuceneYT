package modelo;


import java.net.*;
import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class Test {
	
	static String [] palabras;
	public static void main(String[]args) throws FileNotFoundException, IOException {
		String line;
		ArrayList<String> TotalPalabras = new ArrayList<String>();
		
			for(int i=1; i <= 243; i++) {
				try (
				    InputStream fis = new FileInputStream("E:\\Escritorio\\LuceneFinal\\CaptionsTXT\\subLinea_"+i+".txt");
				    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				    BufferedReader br = new BufferedReader(isr);
				) {
					
				    while ((line = br.readLine()) != null) {
				    	palabras = line.split(" ");
				    	for(int k = 0; k < palabras.length; k++) {
				    		TotalPalabras.add(palabras[k]);
				    	}
				    	//System.out.println("-------------------");
				    	
				    }
				    
				    
				}
			}
			for(int i = 0; i < TotalPalabras.size();i++) {
				System.out.println("Total de palabras: "+TotalPalabras.get(i));
			}
	    
	}
}
