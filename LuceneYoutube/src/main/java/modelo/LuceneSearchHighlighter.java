package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
//import org.bouncycastle.tsp.cms.ImprintDigestInvalidException;

import controlador.Coordinador;
import jdk.nashorn.internal.runtime.ListAdapter;
import vista.ventanas.VentanaPrincipal;
 
public class LuceneSearchHighlighter 
{
	
	public static String textoABuscar;
	private static Coordinador miCoordinador;
	

	
    //This contains the lucene indexed documents
    private static final String INDEX_DIR = "E:\\Escritorio\\LuceneTest2\\Index";
	private static ArrayList<String[]> listaFrags;
	

 
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception 
    {
    
    }
    public static Documento ejecutarHighlighter(String txtBuscarPalabras) throws Exception {
    	
    	listaFrags = null;
        
    	//Obtiene la referencia al directorio
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
         
        //Index Reader - una interface para acceder a un punto en el tiempo visto del indexador lucene
        IndexReader reader = DirectoryReader.open(dir);
         
        
        //Crea un buscador lucene. Busca en un solo IndexReader.
        IndexSearcher searcher = new IndexSearcher(reader);
         
        
        //analyzer con las "stop words" predeterminadas
        Analyzer analyzer = new StandardAnalyzer();
         
        //Query parser para ser usado para crear TermQuery
        QueryParser qp = new QueryParser("contents", analyzer);
         
        
        //Crear la consulta
        System.out.println(txtBuscarPalabras+"");
        Query query = qp.parse(txtBuscarPalabras+""/*cottage private discovery concluded*/);
         
        //Buscar documentos lucene
        TopDocs hits = searcher.search(query, 10);
         
        /** Highlighter Code Start ****/
         
        //Uses HTML &lt;B&gt;&lt;/B&gt; tag to highlight the searched terms
        Formatter formatter = new SimpleHTMLFormatter();
         
        //Le da puntuacion a los fragmentos de texto por el numero de los terminos de la consulta
        //enontrados, basicamente el puntaje correspondiente en t�rminos simples
   
        QueryScorer scorer = new QueryScorer(query);
        
        //usado para marcar los terminos destacados en la mejor seccion de un texto
        Highlighter highlighter = new Highlighter(formatter, scorer);
         
        
        //Divide el texto en textos del mismo tama�o pero no divide tramos
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 10);
         
        //divide el texto en fragmentos del mismo tama�o sin preocuparse por detectar los l�mites de las oraciones.
        //Fragmenter fragmenter = new SimpleFragmenter(10);
         
        //establecer fragmentador en resaltador
        highlighter.setTextFragmenter(fragmenter);
        ArrayList<String> arrayRuta = new ArrayList<String>();
        ArrayList<Integer> arrayCantPal = new ArrayList<Integer>();
        ArrayList<Double> arrayTMinutos = new ArrayList<Double>();
        
        Documento documento = new Documento(arrayRuta, arrayCantPal, arrayTMinutos);
        //Iterar sobre los resultados encontrados
        for (int i = 0; i < hits.scoreDocs.length; i++) 
        {
        	
            int docid = hits.scoreDocs[i].doc;
            Document doc = searcher.doc(docid);
            String title = doc.get("path");
            arrayRuta.add(title);
            documento.setRuta(arrayRuta);
            
             //Imprimiendo donde se escuentras los documentos
            
            System.out.println("Path " + " : " + title);
            
             
            //Obtiene el texto almacenado desde el documento
            
            String text = doc.get("contents");
 
            
            //crea token stream
            TokenStream stream = TokenSources.getAnyTokenStream(reader, docid, "contents", analyzer);
             
          
            //obtiene los fragmentos de texto resaltados
            String[] frags = highlighter.getBestFragments(stream, text, 40);
           
            for (String frag : frags) 
            {
            	
                System.out.println("=======================");
                
                System.out.println(frag);
               //miCoordinador.crearDocumento(frag + "<br>","html");
               //miCoordinador.crearDocumento("<br>", "html");
               //miCoordinador.crearDocumento(frag, "txt");
      
            }
        }
        Documento.i++;
        Documento.j++;
        dir.close();
        return documento; 
        
    }
    
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
    
   public void escribirTxt(String ruta) {
	   
   }
    
    
    
    public static void imprimirListaFrags(){
    	for(int i=0; i<=listaFrags.size()-1;i++) {
    		System.out.println(listaFrags.get(i)+"");
    	}
    }

	
	
	public void setTextAResaltar(String texto) {
		textoABuscar = texto;
	}
}