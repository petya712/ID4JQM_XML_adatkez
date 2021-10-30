package hu.domparse.ID4JQM;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMReadID4JQM {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        //  Az XML file objektumának létrehozása
        File xmlFile = new File("XMLID4JQM.xml");

        //  Document Builder létrehozása.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //  Az XML fájl DOM objektummá való konvertálása.
        Document doc = dBuilder.parse(xmlFile);

        //  A dokumentum normalizálása.
        doc.getDocumentElement().normalize();

        //A gyökér elem nevének meghatározása és az iskolához kapcsolódó elemek és attribútumok kiírása.
        System.out.println("A dokuementum gyökér eleme: " + doc.getDocumentElement().getNodeName());
        System.out.println(doc.getDocumentElement().getAttribute("id"));
        System.out.println(doc.getElementsByTagName("nev").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("varos").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("telefonszam").item(0).getTextContent());

        //  Az összes <osztaly> elem beillesztése egy NodeList-be.
        NodeList nList = doc.getElementsByTagName("osztaly");

        /*
            For ciklussal végig menni a NodeList-en és kiírni a dokumentum összes elemét és attribútumát.
            Az osztalyokon megy keresztül a ciklus.
            Minden osztalyhoz érve a osztaly ID-t átadva meghívja a diák és a tanár kiíró függvényeket.        
        */
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("------------------------------------------");
                System.out.println();
                System.out.println("------------------------------------------");

                Element elem = (Element) nNode;

                String id = elem.getAttribute("id");

                String iskolaid = elem.getAttribute("iskolaid");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("evfolyam").item(0);
                String evfolyam = node2.getTextContent();

                System.out.println("Osztály ID: " + id);
                System.out.println("Iskola ID: " + iskolaid);
                System.out.println("\tOsztálynév: " + nev);
                System.out.println("\tÉvfolyam: " + evfolyam);

                System.out.println("\nA(z) " + nev + " osztály tanárai:\n");
                ListTanar(doc, id);

                System.out.println("\nA(z) " + nev + " osztály diákjai:\n");
                ListDiak(doc, id);
            }
        }
    }

    public static void ListDiak (Document doc, String osztalyid) {
        NodeList nList = doc.getElementsByTagName("diak");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                if (elem.getAttribute("osztalyid").toString().equals(osztalyid)) {
                    String id = elem.getAttribute("id");

                    Node node1 = elem.getElementsByTagName("vezeteknev").item(0);
                    String vezeteknev = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("keresztnev").item(0);
                    String keresztnev = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("szulinap").item(0);
                    String szulinap = node3.getTextContent();

                    Node node4 = elem.getElementsByTagName("nem").item(0);
                    String nem = node4.getTextContent();

                    Node node5 = elem.getElementsByTagName("cim").item(0);
                    String cim = node5.getTextContent();

                    System.out.println("Diák ID: " + id);
                    System.out.println("\tVezetéknév: " + vezeteknev);
                    System.out.println("\tKeresztnév: " + keresztnev);
                    System.out.println("\tSzületési idő: " + szulinap);
                    System.out.println("\tNem: " + nem);
                    System.out.println("\tLakcím: " + cim);
                }
            }
        }
    }

    public static void ListTanar (Document doc, String osztalyid) {
        NodeList nList = doc.getElementsByTagName("tanar");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                if (elem.getAttribute("osztalyid").toString().equals(osztalyid)) {
                    String id = elem.getAttribute("id");

                    Node node1 = elem.getElementsByTagName("vezeteknev").item(0);
                    String vezeteknev = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("keresztnev").item(0);
                    String keresztnev = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("szulinap").item(0);
                    String szulinap = node3.getTextContent();

                    Node node4 = elem.getElementsByTagName("fokozat").item(0);
                    String fokozat = node4.getTextContent();

                    Node node5 = elem.getElementsByTagName("cim").item(0);
                    String cim = node5.getTextContent();

                    System.out.println("Tanár ID: " + id);
                    System.out.println("\tVezetéknév: " + vezeteknev);
                    System.out.println("\tKeresztnév: " + keresztnev);
                    System.out.println("\tSzületési idő: " + szulinap);
                    System.out.println("\tFokozat: " + fokozat);
                    System.out.println("\tLakcím: " + cim);
                }
            }
        }
    }
}
