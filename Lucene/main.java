import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.util.stream.*;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.*;
import org.apache.lucene.store.FSDirectory;
import org.xml.sax.*;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;

import java.util.List;

class Test {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Path indexPath = Paths.get("indexDirectory");
        Files.createDirectory(indexPath);
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        
        JSONParser parser = new JSONParser();
        try {
            //https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
            File folder = new File("SplittedXML");
            File[] listof = folder.listFiles();

            for (File file : listof) {
                String name = file.getName();
                List<String> lines = Files.readAllLines(Paths.get("SplittedXML/"+ name));

                Document doc = new Document();
                
                for (String line : lines) {
                    JSONObject obj = (JSONObject) parser.parse(line);
                    doc.add(new Field((String) obj.get("Id"), line, TextField.TYPE_STORED));
                }
                iwriter.addDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(iwriter.getDirectory());
        iwriter.close();


    }
}
