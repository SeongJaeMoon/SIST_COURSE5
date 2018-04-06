package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

public class Sample001 {

	public static void main(String[] args) {

		try {
			// ����) members.xml ����(������Ʈ �̸� ��ġ)�� ���������� �̸� �����ؾ� �Ѵ�.
			File inputFile = new File("members.xml");

			// ���������� �����ϴ� XML ������ �޸𸮷� �о���̰�, XML������ �´��� Ȯ���ϴ� ����
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			// getDocumentElement() - Returns the root element of the document.
			// getNodeName() - ���� ������ ���(������Ʈ, �Ӽ�, ...)�� �̸� ���� ��ȯ
			System.out.print("Root element: ");
			System.out.println(doc.getDocumentElement().getNodeName());

			// getElementsByTagName() - Ư�� �̸��� ���� ������Ʈ ��ü ��ȯ. ��ȯ�ڷ��� NodeList.
			// getLength()
			// item()
			NodeList nList = doc.getElementsByTagName("member");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.print("\nCurrent Element :");
				System.out.println(nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					//getAttribute() - Ư�� ������Ʈ�� �Ӽ��� ��ȯ
					System.out.print("mid : ");
					System.out.println(eElement.getAttribute("mid"));
					
					NodeList nameList = eElement.getElementsByTagName("name");
					for (int count = 0; count < nameList.getLength(); count++) {
						Node node1 = nameList.item(count);
						if (node1.getNodeType() == node1.ELEMENT_NODE) {
							Element name = (Element) node1;
							//getTextContent() - Ư�� ������Ʈ�� �������� ��ȯ
							System.out.print("name : ");
							System.out.println(name.getTextContent());
						}
					}
					
					
					NodeList phoneList = eElement.getElementsByTagName("phone");
					for (int count = 0; count < phoneList.getLength(); count++) {
						Node node1 = phoneList.item(count);
						if (node1.getNodeType() == node1.ELEMENT_NODE) {
							Element phone = (Element) node1;
							//getTextContent() - Ư�� ������Ʈ�� �������� ��ȯ
							System.out.print("phone : ");
							System.out.println(phone.getTextContent());
						}
					}
					
					
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
