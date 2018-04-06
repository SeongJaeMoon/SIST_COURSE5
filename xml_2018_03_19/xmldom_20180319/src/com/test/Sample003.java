package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;

public class Sample003 {

	public static void main(String[] args) {

		//guestbooks.xml 파일의 정보를 콘솔창에 출력
		
		StringBuilder sb = new StringBuilder();

		try {
			File inputFile = new File("guestbooks.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			//루트 엘리먼트 접근 및 루트 엘리먼트의 이름 반환 및 출력
			System.out.println(String.format("<%s>", doc.getDocumentElement().getNodeName()));
			
			//루트 엘리먼트의 자식 엘리먼트로서 첫 번째 <item> 엘리먼트에 대한 접근 및 이름 반환
			String tagName = doc.getDocumentElement().getFirstChild().getNextSibling().getNodeName();

			//<item> 이름을 가진 엘리먼트들에 대한 반환
			NodeList nList = doc.getElementsByTagName(tagName);
			
			//첫 번째 <item> 엘리먼트에 대한 자식 엘리먼트들의 nodeName 반환
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
			
			//<item> 이름을 가진 엘리먼트들을 인덱스별로 접근
			for (int a = 0; a<nList.getLength(); ++a) {
				Node nNode = nList.item(a);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//<item> 이름을 가진 엘리먼트별 자식 엘리먼트 탐색
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

			//StringBuilder에 저장된 문자열을 콘솔창에 출력
			System.out.println(sb.toString());

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		

}
