package domID4JQM;


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomWriteID4JQM {
	public static void main(String[] args) {
	try {
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/domID4JQM/usersID4JQM.xml"));
		document.getDocumentElement().normalize();
		
		System.out.println("Root element: " + document.getDocumentElement().getNodeName());
		document.getDocumentElement().setAttribute("xmlns", "domID4JQM");
		NodeList nList = document.getElementsByTagName("user");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("User id: " + eElement.getAttribute("id"));
               System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
               System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
               System.out.println("Profession : " + eElement.getElementsByTagName("profession").item(0).getTextContent());
               
            }
         }
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(document);
         StreamResult result = new StreamResult(new File("users1ID4JQM.xml"));
         transformer.transform(source, result);
		
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
}