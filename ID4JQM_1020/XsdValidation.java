package ID4JQM_1020;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XsdValidation {
    public static void main(String[] args) throws SAXException, ParserConfigurationException {
        File xmlDocument = new File("macskakID4JQM.xml");
        File schemafile = new File("macskakID4JQM.xsd");

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(schemafile);

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setSchema(schema);

        SAXParser parser = spf.newSAXParser();

        XsdValidationHandler dh = new XsdValidationHandler();

        try {
            parser.parse(xmlDocument, dh);
            System.out.println("Successful validation!");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Unsuccessful validation!");
        }
    }
}

class XsdValidationHandler extends DefaultHandler {
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }
}