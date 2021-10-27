package hu.domparse.ID4JQM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQuerryID4JQM {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException  {
		// TODO Auto-generated method stub
		File xmlFile = new File("XMLID4JQM.xml"); //xml fájl bekérése
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //olvasás lehetõvé tétele
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();


        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("------------------------------");
        LoadQuery(doc);

	}
	
	public static void LoadQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("diak");

    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node nNode = nodeList.item(i);
    		Element element = (Element) nNode;
    		int db = element.getElementsByTagName("vezeteknev").getLength();
    		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
    			
    			if(element.getAttribute("id").equals("DI002")) {
    				QueryXML(db, doc, element);

    			}
    			if(element.getAttribute("id").equals("DI003")) {
    				QueryXML(db, doc, element);

    			}
    		}
    	}
		
	}
	
	public static void QueryXML(int db, Document doc, Element element) {
		String id = element.getAttribute("id");
		
		System.out.println("\nJelenlegi elem:");
		System.out.println("Diák id: " + id);
		for (int i = 0; i < db; i++) {
			
			String vname =  element.getElementsByTagName("vezeteknev").item(i).getTextContent();
			String kname = element.getElementsByTagName("keresztnev").item(i).getTextContent();
			System.out.println("Diák vezetékneve: "+vname+"\nDiák keresztneve: "+kname);
		}
	}

}