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
		File xmlFile = new File("XMLID4JQM.xml"); //Xml fájl bekérése
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //Olvasás lehetővé tétele
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();


        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());//Gyökér elem kiiratása
        System.out.println("------------------------------");
        LoadQuery(doc);

	}
	
	public static void LoadQuery(Document doc) throws TransformerException {
		NodeList diaknodeList = doc.getElementsByTagName("diak"); // Diákok listája
		NodeList osztalynList = doc.getElementsByTagName("osztaly");//Osztályok listája
		
		Element element = null;
		Node nNode = null;
		//Diákok ID és osztály ID szerinti kilistázása, hogy később tudjunk választani diákot lekérdezésre
		for (int i = 0; i < diaknodeList.getLength(); i++) {
			nNode = diaknodeList.item(i);
			element = (Element) nNode;
			String id =  element.getAttribute("id");
			String osztid =  element.getAttribute("osztalyid");
			String vnev = element.getElementsByTagName("vezeteknev").item(0).getTextContent();
			String knev = element.getElementsByTagName("keresztnev").item(0).getTextContent();
			System.out.println("\n"+(i + 1) + ") ID: "+ id +"\nOsztály ID: "+ osztid +"\nVezetékneve: "+ vnev +"\nKeresztneve: "+ knev + "\n");

		}
		
		Scanner in = new Scanner(System.in);

		System.out.println("Adja meg a diák ID-t:"); // Diák kiválasztása scanner inputról ID szerint
		String diakid = in.next();
		
		
    	for (int i = 0; i < diaknodeList.getLength(); i++) {
    		nNode = diaknodeList.item(i);
    		element = (Element) nNode;
    		int db = element.getElementsByTagName("vezeteknev").getLength();
    		if(nNode.getNodeType() == Node.ELEMENT_NODE) { // Ha megfelelő ID-t adtunk meg akkor elindítja a lekérdező metódust (QueryXML)
  
    			
    			if(element.getAttribute("id").equals(diakid)) {
    				QueryXML(db,doc,element,osztalynList);

    			}
    		}
    	}
    	in.close();
		
	}
	// A lekérdezett diák adatainak kiíratási metódusa
	public static void QueryXML(int db,Document doc, Element element, NodeList osztalynList) {
		Node nNode = null;
		Element elem = null;
		String id = element.getAttribute("id");
		
		System.out.println("\nDiák adatai:\n");
		System.out.println("Diák ID: " + id);
		for (int i = 0; i < db; i++) {
			
			nNode = osztalynList.item(i); //Az átadott osztály nodelistából kiválasztjuk az i. elemet
			elem = (Element) nNode;
			
			//Stringbe szedett kiiratása az elemeknek és attributumoknak a gyökér elemből (iskola) és a diák és osztály elemből
			String diakcim =  element.getElementsByTagName("cim").item(i).getTextContent();
			String diaknem =  element.getElementsByTagName("nem").item(i).getTextContent();
			String osztid =  element.getAttribute("osztalyid");
			String osztalynev =  elem.getElementsByTagName("nev").item(i).getTextContent();
			String szulinap =  element.getElementsByTagName("szulinap").item(i).getTextContent();
			String vname =  element.getElementsByTagName("vezeteknev").item(i).getTextContent();
			String kname = element.getElementsByTagName("keresztnev").item(i).getTextContent();
			String iskolaneve = doc.getElementsByTagName("nev").item(i).getTextContent();

			System.out.println("Iskola neve: "+iskolaneve+"\nOsztályának ID-je: "+osztid+"\nOsztályának neve "+osztalynev+"\nVezetékneve: "+vname+"\nKeresztneve: "+kname+"\nSzületési ideje: "+szulinap+"\nNeme: "+diaknem+"\nLakcíme: "+diakcim);
		}
	}

}
