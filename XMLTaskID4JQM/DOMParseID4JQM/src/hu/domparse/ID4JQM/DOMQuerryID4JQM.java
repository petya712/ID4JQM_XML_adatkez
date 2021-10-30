package hu.domparse.ID4JQM;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //olvasás lehetővé tétele
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();


        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("------------------------------");
        LoadQuery(doc);

	}
	
	public static void LoadQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("diak");
		Scanner in = new Scanner(System.in);

		System.out.println("Adja meg a diák ID-t:");
		String diakid = in.next();
		
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node nNode = nodeList.item(i);
    		Element element = (Element) nNode;
    		int db = element.getElementsByTagName("vezeteknev").getLength();
    		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
  
    			
    			if(element.getAttribute("id").equals(diakid)) {
    				QueryXML(db,doc,element);

    			}
    		}
    	}
    	in.close();
		
	}
	
	public static void QueryXML(int db,Document doc, Element element) {
		String id = element.getAttribute("id");
		
		System.out.println("\nDiák adatai:\n");
		System.out.println("Diák ID: " + id);
		for (int i = 0; i < db; i++) {
			
			String diakcim =  element.getElementsByTagName("cim").item(i).getTextContent();
			String diaknem =  element.getElementsByTagName("nem").item(i).getTextContent();
			String osztid =  element.getAttribute("osztalyid");
			String szulinap =  element.getElementsByTagName("szulinap").item(i).getTextContent();
			String vname =  element.getElementsByTagName("vezeteknev").item(i).getTextContent();
			String kname = element.getElementsByTagName("keresztnev").item(i).getTextContent();
			String iskolaneve = doc.getElementsByTagName("nev").item(i).getTextContent();

			System.out.println("Iskola neve: "+iskolaneve+"\nOsztálya: "+osztid+"\nVezetékneve: "+vname+"\nKeresztneve: "+kname+"\nSzületési ideje: "+szulinap+"\nNeme: "+diaknem+"\nLakcíme: "+diakcim);
		}
	}

}