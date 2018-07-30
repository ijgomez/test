package org.example.test;

import java.io.File;
import java.io.FileReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class LuceneService {

	public void indexar(String searchPathname, String indexFilePathname) {
		IndexWriter writer = null;
		File dir = new File(searchPathname);
		Document doc = null;

		try {

			// Creamos el indice si no existe almacenandolo en un directorio del
			// HDD
			Directory directory = FSDirectory.open(new File(indexFilePathname));
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_9, new StandardAnalyzer(Version.LUCENE_4_9));

			writer = new IndexWriter(directory, indexWriterConfig);

			// Recorremos todos los archivos del directorio (No sus
			// subdirectorios)
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile() && file.canRead() && (file.getName().endsWith(".txt") || file.getName().endsWith(".xml"))) {
					System.out.println("Indexando el archivo: " + file.getAbsolutePath());

					// Cremos e inicializamos el Document con los datos del
					// archivo que queremos guardar
					doc = new Document();
					doc.add(new StringField("path", file.getPath(), Field.Store.YES));
					doc.add(new TextField("contents", new FileReader(file)));
					 

					// Añadimos el documento al indice.
					writer.addDocument(doc);
				}
			}

			// Organizamos y guardamos los datos.
//			writer.optimize();
			writer.close();

			System.out.println("OK");
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

	}
	
	public void search(String indexFilePathname, String textToSearch) {
		try {
			// Ubicación del índice
			Directory directory = FSDirectory.open(new File(indexFilePathname));
			
			DirectoryReader reader = DirectoryReader.open(directory);
			// Creamos un búscador
			IndexSearcher searcher = new IndexSearcher(reader);

			// Palaba a búscar dentro del índice aquellos documentos que
			// contentan la palabra "Carlos" y no la palabra "Avanzado"
			// String textToSearch = "+Grouchnikov -Avanzado";

			// Creamos la consulta
			QueryParser parser = new QueryParser(Version.LUCENE_4_9, "contents", new StandardAnalyzer(Version.LUCENE_4_9));
			Query query = parser.parse(textToSearch);

			TopDocs topDocs = searcher.search(query, 10);
			
			
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			if (scoreDocs.length == 0) {
				System.out.println("No se han encontrado coincidencias.");
				
			} else {
				for (ScoreDoc scoreDoc : scoreDocs) {
					Document doc = searcher.doc(scoreDoc.doc);
					System.out.println(scoreDoc + " - " + doc.get("path"));
					
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

}
