package hu.domparse.ID4JQM;

import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMModifyID4JQM {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        //  A forrás és cél XML file objektumának létrehozása.
        File xmlFile = new File("XMLID4JQM.xml");
        //File xmlOutputFile = new File("XMLID4JQMResults.xml");

        //  Scanner olvasó példányosítása.
        Scanner in = new Scanner(System.in);

        //  Document Builder létrehozása.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //  Az XML fájl DOM objektummá való konvertálása.
        Document doc = dBuilder.parse(xmlFile);
        
        //  A dokumentum normalizálása.
        doc.getDocumentElement().normalize();

        //  Az <osztaly> tag elemek kiválasztása.
        NodeList nList = doc.getElementsByTagName("osztaly");
        System.out.println("Osztály nevek felülírása:\n");
        
     // Jelenlegi osztályok kiiratása.
        for (int i = 0; i < nList.getLength(); i++) {
        	Node nNode = nList.item(i);
        	Element elem = (Element) nNode;
            String id = elem.getAttribute("id");
            
			System.out.println((i+1)+". sorszámú osztály adatai: ");
			DOMReadID4JQM.ListOsztaly(doc, id);
			System.out.println("-------------------------------------------");
		}
        //Osztály nevének módosítása.
        for (int i = 0; i < nList.getLength(); i++) { 
            //  Az <osztaly> tag (i)-ik eleme.
            Node nNode = nList.item(i);

            Element elem = (Element) nNode;

            //  A név node kiválasztása.
            Node node1 = elem.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();

            //  Adat bekérése és a node értékének megváltoztatása.
            System.out.println("Az osztály jelenlegi neve: " + nev);
            System.out.println("Adja meg az osztály új nevét: ");
            String newname = in.next();
            node1.setTextContent(newname);
        }
        
        //  A Scanner bezárása.
        in.close();

        //  A módosított dokuemtum kiírása file-ba.
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        //  DOMSource, mint a módosított adatok ideiglenes tárolója a kiíráshoz.
        DOMSource source = new DOMSource(doc);

        //  A kiírandó file előkészítése.
        StreamResult result = new StreamResult(new File("XMLID4JQM.xml"));

        //  A file kiírása.
        transformer.transform(source, result);
    }
}
