package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import controlador.Coordinador;

public class Contadores {
	Coordinador miCoordinador;
	ArrayList<Integer> cont1;
	int cont2;
	int i;
	public Contadores(ArrayList<Integer> cont1) {
		this.cont1 = cont1;
		
	}
	
	public ArrayList<Integer> getCont1() {
		return cont1;
	}
	public void setCont1(ArrayList<Integer> cont1) {
		this.cont1 = cont1;
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}

	public int sumar(String num1, String num2) {
		int n1 = Integer.parseInt(num1);
		int n2 = Integer.parseInt(num2);
		System.out.println("Desde sumar");
		return n1+n2;
	}

public Documento contarPalabrasArchivo(Documento documento) throws FileNotFoundException, IOException{
		
		String ruta;
		
		for(int l = 0; l < documento.getRuta().size(); l++) {
			i = 0;
			ruta = documento.getRuta().get(l);
			StreamTokenizer st = new StreamTokenizer(new FileReader(ruta));
			
			while(st.nextToken()!=StreamTokenizer.TT_EOF) {
				if(st.ttype == StreamTokenizer.TT_WORD) {
					i++;
				}else if(st.ttype==StreamTokenizer.TT_NUMBER) {
					cont2++;
				}
			}
			documento.cantPalabras.add(i);
		}
		return documento;
	}
}
