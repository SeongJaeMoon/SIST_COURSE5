package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

public class Sample001 {

	public static void main(String[] args) {

		try {
			// 주의) members.xml 파일(프로젝트 이름 위치)이 물리적으로 미리 존재해야 한다.
			File inputFile = new File("members.xml");

			// 물리적으로 존재하는 XML 파일을 메모리로 읽어들이고, XML문법에 맞는지 확인하는 과정
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			// getDocumentElement() - Returns the root element of the document.
			// getNodeName() - 현재 선택한 노드(엘리먼트, 속성, ...)의 이름 정보 반환
			System.out.print("Root element: ");
			System.out.println(doc.getDocumentElement().getNodeName());

			// getElementsByTagName() - 특정 이름을 가진 엘리먼트 전체 반환. 반환자료형 NodeList.
			// getLength()
			// item()
			NodeList nList = doc.getElementsByTagName("member");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.print("\nCurrent Element :");
				System.out.println(nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					//getAttribute() - 특정 엘리먼트의 속성값 반환
					System.out.print("mid : ");
					System.out.println(eElement.getAttribute("mid"));
					
					NodeList nameList = eElement.getElementsByTagName("name");
					for (int count = 0; count < nameList.getLength(); count++) {
						Node node1 = nameList.item(count);
						if (node1.getNodeType() == node1.ELEMENT_NODE) {
							Element name = (Element) node1;
							//getTextContent() - 특정 엘리먼트의 콘텐츠를 반환
							System.out.print("name : ");
							System.out.println(name.getTextContent());
						}
					}
					
					
					NodeList phoneList = eElement.getElementsByTagName("phone");
					for (int count = 0; count < phoneList.getLength(); count++) {
						Node node1 = phoneList.item(count);
						if (node1.getNodeType() == node1.ELEMENT_NODE) {
							Element phone = (Element) node1;
							//getTextContent() - 특정 엘리먼트의 콘텐츠를 반환
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
