package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
 
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import controlador.Coordinador;
 
public class LuceneWriteIndexFromFile 
{
	Coordinador miCoordinador;
    public static void main(String[] args)
    
    
    {
    	
    }
    
    public void escribirIndexDesdeArchivo(String ruta) {
        //Input folder
        String docsPath = ruta;
         
        //Output folder
        String indexPath = "E:\\Escritorio\\LuceneFinal\\Index";
 
        //Input Path Variable
        final Path docDir = Paths.get(docsPath);
 
        try
        {
            //Instancia org.apache.lucene.store.Directory
            Directory dir = FSDirectory.open( Paths.get(indexPath) );
             
           
            //Analizador con las "stopword" por defecto
            Analyzer analyzer = new StandardAnalyzer();
             
            
            //Configuraci�n del IndexWriter
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
             
            
            //IndexWriter escribe los nuevos archivos indexados al directorio
            IndexWriter writer = new IndexWriter(dir, iwc);
             
            //Es un metodo recursivo para iterar todos los archivos y directorio
            indexDocs(writer, docDir);
 
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
     
    static void indexDocs(final IndexWriter writer, Path path) throws IOException 
    {
        //Directory?
        if (Files.isDirectory(path)) 
        {
            
        	//Iterar directorio
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() 
            {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException 
                {
                    try
                    {
                      
                    	//Indexar este archivo
                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    } 
                    catch (IOException ioe) 
                    {
                        ioe.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } 
        else
        {
            
        	//Indexar este archivo
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }
 
    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException 
    {
        try (InputStream stream = Files.newInputStream(file)) 
        {
            
        	//Crea un documento lucene
            Document doc = new Document();
             
            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new String(Files.readAllBytes(file)), Store.YES));
             
            //Actualiza el documento primero eliminando el/los documento/s
            //conteniendo <code>termino</code> y luego agregando el nuevo
            //documento. 

            writer.updateDocument(new Term("path", file.toString()), doc);
        }
    }

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}
}
