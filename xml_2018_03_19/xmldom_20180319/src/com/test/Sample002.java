package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

public class Sample002 {

	public static void main(String[] args) {
		//����) breakfast_menu.xml ������ ������ �ܼ�â�� ���
		/*
		��� ��)
		<breakfast_menu>
			name / price / calories / picture / description
		1.  XXXX / XXXX  / XXXXXXX / XXXXX / XXXXXXXXX
		2.
		3.
		4.
		5.
		*/
		
		StringBuilder sb = new StringBuilder();

		try {
			File inputFile = new File("breakfast_menu.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			//��Ʈ ������Ʈ ���� �� ��Ʈ ������Ʈ�� �̸� ��ȯ �� ���
			System.out.println(String.format("<%s>", doc.getDocumentElement().getNodeName()));
			
			//��Ʈ ������Ʈ�� �ڽ� ������Ʈ�μ� ù ��° <food> ������Ʈ�� ���� ���� �� �̸� ��ȯ
			String tagName = doc.getDocumentElement().getFirstChild().getNextSibling().getNodeName();

			//<food> �̸��� ���� ������Ʈ�鿡 ���� ��ȯ
			NodeList nList = doc.getElementsByTagName(tagName);
			
			//ù ��° <food> ������Ʈ�� ���� �ڽ� ������Ʈ���� nodeName ��ȯ
			//-> name, price, calories, picture, description
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
			
			//<food> �̸��� ���� ������Ʈ���� �ε������� ����
			for (int a = 0; a<nList.getLength(); ++a) {
				Node nNode = nList.item(a);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//<food> �̸��� ���� ������Ʈ�� �ڽ� ������Ʈ Ž��
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
