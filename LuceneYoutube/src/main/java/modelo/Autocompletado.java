package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class Autocompletado {
	
	public static String[] doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query,
			int hitsPerPage, IndexReader reader, String filtro, String index, String lineAux) throws IOException{
		
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		searcher = new IndexSearcher(reader);
		IndexReader readerAutoCompletado = reader;
		LuceneDictionary dictionary = new LuceneDictionary(readerAutoCompletado,filtro);
		
		Directory directory = new RAMDirectory();
		AnalyzingInfixSuggester analyzingInfixSuggester = new AnalyzingInfixSuggester(directory, new StandardAnalyzer());
		analyzingInfixSuggester.build(dictionary);
		List<Lookup.LookupResult> lookupResultList = analyzingInfixSuggester.lookup(lineAux, false, 10);
		System.out.println("Posibilidades: ");
		System.out.println(lookupResultList.size());
		for(Lookup.LookupResult lookupResult : lookupResultList) {
			System.out.println(lookupResult.key+": "+ lookupResult.value);
		}
		analyzingInfixSuggester.close();
		
		TopDocs results = searcher.search(query, 5*hitsPerPage);
		ScoreDoc[] hits = results.scoreDocs;
		
		System.out.print("hits: "+hits);
		
		return null;
		
	}
	
	public static void main (String [ ] args) throws IOException, ParseException {
		
		String INDEX_DIR = "E:\\Escritorio\\LuceneFinal\\Index";
		Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser qp = new QueryParser("contents", analyzer);
		Query query = qp.parse("computers");
		//doPagingSearch(in, searcher, query, 10, reader, filtro, index, lineAux)
		 
	}
        




	
}
    
    

        
           

    


