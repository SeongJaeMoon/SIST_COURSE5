package com.test;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.xpath.*;

import java.net.URL;
import java.util.Scanner;

public class Sample005 {

	public static void main(String[] args) {

		/* 
		���� XML ������ �б� (Java ���α׷�)
		- ���û RSS
		http://www.kma.go.kr/weather/lifenindustry/sevice_rss.jsp
		- ���û ���� �߱⿹��
		http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109
		stnId=108 (����)
		stnId=109 (����, ���)
		stnId=105 (����)
		stnId=131 (��û�ϵ�)
		stnId=133 (��û����)
		stnId=146 (����ϵ�)
		stnId=156 (���󳲵�)
		stnId=143 (���ϵ�)
		stnId=159 (��󳲵�)
		stnId=184 (����Ư����ġ��)
			
		- ���û ���׿��� ������
		����Ư���� ������ ����1��
		wideCode �õ� - 11 : ����
		cityCode �ñ��� - 680 : ������
		http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168000000
		wideCode
		[{"code":"11","value":"����Ư����"},{"code":"26","value":"�λ걤����"},{"code":"27","value":"�뱸������"},{"code":"28","value":"��õ������"},{"code":"29","value":"���ֱ�����"},{"code":"30","value":"����������"},{"code":"31","value":"��걤����"},{"code":"41","value":"��⵵"},{"code":"42","value":"������"},{"code":"43","value":"��û�ϵ�"},{"code":"44","value":"��û����"},{"code":"45","value":"����ϵ�"},{"code":"46","value":"���󳲵�"},{"code":"47","value":"���ϵ�"},{"code":"48","value":"��󳲵�"},{"code":"50","value":"����Ư����ġ��"}]
		cityCode
		[{"code":"11110","value":"���α�"},{"code":"11140","value":"�߱�"},{"code":"11170","value":"��걸"},{"code":"11200","value":"������"},{"code":"11215","value":"������"},{"code":"11230","value":"���빮��"},{"code":"11260","value":"�߶���"},{"code":"11290","value":"���ϱ�"},{"code":"11305","value":"���ϱ�"},{"code":"11320","value":"������"},{"code":"11350","value":"�����"},{"code":"11380","value":"����"},{"code":"11410","value":"���빮��"},{"code":"11440","value":""},{"code":"11470","value":"��õ��"},{"code":"11500","value":"������"},{"code":"11530","value":"���α�"},{"code":"11545","value":"��õ��"},{"code":"11560","value":"��������"},{"code":"11590","value":"���۱�"},{"code":"11620","value":"���Ǳ�"},{"code":"11650","value":"���ʱ�"},{"code":"11680","value":"������"},{"code":"11710","value":"���ı�"},{"code":"11740","value":"������"}]
		
		��¿�)
		����,��⵵ ���� �߱⿹�� - 2016�� 05�� 13�� (��)���� 06:00 ��ǥ
		
		��а��� �������� 15~16���� �� ���ڰ�, �� ���� ���� ������ �������� ��ü�� ���� ���� ���ڽ��ϴ�.<br />����� ���(������� : 10~13��, �ְ��� : 21~25��)�� ����ϰų� ���� ���ڽ��ϴ�.<br />�������� ���(2~5mm)���� ���ڽ��ϴ�.<br />�����ߺ��ػ��� ������ 15���� 1.0~2.5m�� ���� �ϰڰ�, �� ���� ���� 0.5~2.0m�� �ϰڽ��ϴ�.
		
		���� : ���� 
		    2016-05-16 00:00 / �帮�� �� / 12~20 / ����
		    2016-05-16 12:00 / �������� / 12~20 / ����
		    2016-05-17 00:00 / �������� / 13~24 / ����
		    2016-05-17 12:00 / �������� / 13~24 / ����
		    2016-05-18 00:00 / �������� / 14~26 / ����
		    2016-05-18 12:00 / �������� / 14~26 / ����
		    2016-05-19 00:00 / �������� / 15~27 / ����
		    2016-05-19 12:00 / �������� / 15~27 / ����
		    2016-05-20 00:00 / �������� / 17~27 / ����
		    2016-05-20 12:00 / �������� / 17~27 / ����
		    2016-05-21 00:00 / �������� / 17~27 / ����
		    2016-05-22 00:00 / �������� / 18~27 / ����
		    2016-05-23 00:00 / �������� / 18~27 / ����
		--------------------------
		
		*/

		Scanner sc = new Scanner(System.in);
		String[] stnId = { "108", "109", "105", "131", "133", "146", "156", "143", "159", "184" };

		do {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document xmlObj = null;

				// �ܺ� �Է� ó�� �߰�
				System.out.println("���û ���� �߱⿹��");
				System.out.println("-------------------------");
				System.out.println("1. ����");
				System.out.println("2. ����, ���");
				System.out.println("3. ����");
				System.out.println("4. ��û�ϵ�");
				System.out.println("5. ��û����");
				System.out.println("6. ����ϵ�");
				System.out.println("7. ���󳲵�");
				System.out.println("8. ���ϵ�");
				System.out.println("9. ��󳲵�");
				System.out.println("10. ����Ư����ġ��");
				System.out.print("���� ����(0 quit)?");
				String m = sc.nextLine();
				if (m.equals("0")) {
					break;
				}

				String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s",
						stnId[Integer.parseInt(m) - 1]);
				URL url = new URL(str);
				InputSource is = new InputSource(url.openStream());
				xmlObj = builder.parse(is);

				// ROOT ������Ʈ ����
				Element root = xmlObj.getDocumentElement();
				System.out.println(root.getNodeName());

				//rss/channel/item/title
				//rss/channel/item/description/header/wf
				//rss/channel/item/description/body/location[n]/city
				//rss/channel/item/description/body/location[n]/data/tmEf
				//rss/channel/item/description/body/location[n]/data/wf
				//rss/channel/item/description/body/location[n]/data/tmn
				//rss/channel/item/description/body/location[n]/data/tmx
				//rss/channel/item/description/body/location[n]/data/reliability
				
				XPath xPath = XPathFactory.newInstance().newXPath();

				System.out.println(xPath.compile("rss/channel/item/title").evaluate(xmlObj, XPathConstants.STRING));
				System.out.println(xPath.compile("rss/channel/item/description/header/wf").evaluate(xmlObj, XPathConstants.STRING));

				NodeList locationNodeList = (NodeList) xPath.compile("/rss/channel/item/description/body/location").evaluate(xmlObj, XPathConstants.NODESET);
				for (int a = 1; a <= locationNodeList.getLength(); ++a) {

					String city = xPath
							.compile(String.format("/rss/channel/item/description/body/location[%s]/city", a))
							.evaluate(xmlObj);
					System.out.printf("���� : %s %n", city);

					NodeList dataNodeList = (NodeList) xPath
							.compile(String.format("/rss/channel/item/description/body/location[%s]/data", a))
							.evaluate(xmlObj, XPathConstants.NODESET);
					for (int i = 0; i < dataNodeList.getLength(); ++i) {
						Node dataNode = dataNodeList.item(i);
						if (dataNode.getNodeType() == 1) {
							Element dataElement = (Element) dataNode;
							System.out.printf("    %s / %s / %s~%s / %s%n",
									dataElement.getElementsByTagName("tmEf").item(0).getTextContent(),
									dataElement.getElementsByTagName("wf").item(0).getTextContent(),
									dataElement.getElementsByTagName("tmn").item(0).getTextContent(),
									dataElement.getElementsByTagName("tmx").item(0).getTextContent(),
									dataElement.getElementsByTagName("reliability").item(0).getTextContent());
						}
					}
					System.out.println("--------------------------");
				}
				
				
				System.out.print("press any key to continue....");
				sc.nextLine(); // EnterŰ ����
				System.out.println();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (true);
		
		System.out.println("���α׷� ����.");
		sc.close();
		
	}

}
