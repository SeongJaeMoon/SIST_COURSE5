package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

public class Sample003 {

	public static void main(String[] args) {

		//guestbooks.xml ������ ������ �ܼ�â�� ���
		
		StringBuilder sb = new StringBuilder();

		try {
			File inputFile = new File("guestbooks.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			//��Ʈ ������Ʈ ���� �� ��Ʈ ������Ʈ�� �̸� ��ȯ �� ���
			System.out.println(String.format("<%s>", doc.getDocumentElement().getNodeName()));
			
			//��Ʈ ������Ʈ�� �ڽ� ������Ʈ�μ� ù ��° <item> ������Ʈ�� ���� ���� �� �̸� ��ȯ
			String tagName = doc.getDocumentElement().getFirstChild().getNextSibling().getNodeName();

			//<item> �̸��� ���� ������Ʈ�鿡 ���� ��ȯ
			NodeList nList = doc.getElementsByTagName(tagName);
			
			//ù ��° <item> ������Ʈ�� ���� �ڽ� ������Ʈ���� nodeName ��ȯ
			//-> name_, pw, content, ...
			NodeList childList = nList.item(0).getChildNodes();
			int namesLength = childList.getLength();
			for (int a = 0; a < namesLength; a++) {
				Node nNode = childList.item(a);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					sb.append(String.format("%s", nNode.getNodeName()));
					if (a < namesLength-2) {
						sb.append(String.format(" / "));
					}
				}
			}
			sb.append("\n");
			
			//<item> �̸��� ���� ������Ʈ���� �ε������� ����
			for (int a = 0; a<nList.getLength(); ++a) {
				Node nNode = nList.item(a);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//<item> �̸��� ���� ������Ʈ�� �ڽ� ������Ʈ Ž��
					NodeList mList = nNode.getChildNodes();
					sb.append(String.format("%d. ", (a+1)));
					for (int b = 0; b<mList.getLength(); ++b) {
						Node mNode = mList.item(b);
						if (mNode.getNodeType() == Node.ELEMENT_NODE) {
							sb.append(String.format("%s ", mNode.getTextContent()));
							if (b < namesLength-2) {
								sb.append(String.format(" / "));
							}
						}
					}
					sb.append("\n");
				}
			}

			//StringBuilder�� ����� ���ڿ��� �ܼ�â�� ���
			System.out.println(sb.toString());

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		

}
