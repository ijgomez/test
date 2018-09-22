package org.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourcesFactory {

	private static Map<String, String> cache = new HashMap<>();

    public static String fromQueryName(String resourceName, String id) {
    	String value = null;
        String key;
        
        key = resourceName + id;
        if (!ResourcesFactory.cache.containsKey(key)) {
        	value = readQuery(resourceName, id, null);
        	ResourcesFactory.cache.put(key, value);
        }
        return ResourcesFactory.cache.get(key);
    }
    
    public static String fromQueryName(String resourceName, String id, String system) {
    	String value = null;
        String key;
        
        key = resourceName + id + system;
        if (!ResourcesFactory.cache.containsKey(key)) {
        	value = readQuery(resourceName, id, system);
        	ResourcesFactory.cache.put(key, value);
        }
        return ResourcesFactory.cache.get(key);
    }

	private static String readQuery(String resourceName, String id, String system) {
		String query;
		String expression;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			try (InputStream is = ResourcesFactory.class.getResourceAsStream(resourceName)) {
				Document xmlDocument = builder.parse(is);
				
				expression = "/querys/query";
				
				if (id != null) {
					expression = expression.concat("[@id='" + id + "']");
				} 
				
				if (system != null) {
					expression = expression.concat("[@system='" + system + "']");
				}
				
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
				
				if (nodeList.getLength() != 1) {
					throw new RuntimeException("data not found.");
				} else {
					Node item = nodeList.item(0);
					query = item.getTextContent().trim();
				}
			}
			return query;
		} catch (XPathExpressionException | DOMException | ParserConfigurationException | SAXException | IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

    private ResourcesFactory() { }
}
