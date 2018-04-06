package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.xpath.*;

import java.io.*;

public class Sample004 {

	public static void main(String[] args) {

		try {

			File inputFile = new File("members.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			System.out.print("Root element: ");
			System.out.println(doc.getDocumentElement().getNodeName());

			XPath xPath = XPathFactory.newInstance().newXPath();
			//String expression = "/members/member";
			String expression = "/members/member[@mid = 'M002']";
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// System.out.println(eElement.getTextContent());;
					System.out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
					System.out.println(eElement.getElementsByTagName("phone").item(0).getTextContent());
				}
			}
			
			System.out.println("--------------------");
			System.out.println(xPath.compile("/members/member[2]/@mid").evaluate(doc, XPathConstants.STRING));
			System.out.println(xPath.compile("//member[@mid = 'M002']/name").evaluate(doc, XPathConstants.STRING));
			

		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
