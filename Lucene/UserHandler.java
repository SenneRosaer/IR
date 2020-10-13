
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

class UserHandler extends DefaultHandler {
    public IndexWriter writer;
    private Integer counter = 0;

    UserHandler(IndexWriter i) {
        super();
        writer = i;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            System.out.println("id: " + attributes.getValue("Id"));
            Document doc = new Document();
            doc.add(new Field("id", "id: " + attributes.getValue("Id"), TextField.TYPE_STORED));
            
            try {
                writer.addDocument(doc);
                counter++;
                if (counter >= 50) {
                    System.out.println(writer.commit());
                    counter = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
