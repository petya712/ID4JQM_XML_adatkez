package domID4JQM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomReadID4JQM {
	
public static void main(String[] args) {
		
		try {
			DocumentBuilderFactory factory =
			DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("src/domID4JQM/usersID4JQM.xml"));
			document.getDocumentElement().normalize();
			
			System.out.println("Root element: " + document.getDocumentElement().getNodeName());
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}