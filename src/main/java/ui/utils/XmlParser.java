package ui.utils;

//import com.sun.tools.corba.se.idl.StringGen;
import org.w3c.dom.Document;
import ui.support.Config;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class XmlParser {

    static Document doc;

    public XmlParser() throws Exception{

        File fXmlFile = new File("src//test//resources//testData//data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);
    }

    public static  String getTagName(String tagName,int index){
        return doc.getElementsByTagName(tagName).item(index).getTextContent();
    }

    public static  String getAttributeForTag(String tagName,int index,String attribute){
        return doc.getElementsByTagName(tagName).item(index).getAttributes().getNamedItem(attribute).getTextContent();
    }
}
