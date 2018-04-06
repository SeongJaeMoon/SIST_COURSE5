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
		원격 XML 데이터 읽기 (Java 프로그램)
		- 기상청 RSS
		http://www.kma.go.kr/weather/lifenindustry/sevice_rss.jsp
		- 기상청 육상 중기예보
		http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109
		stnId=108 (전국)
		stnId=109 (서울, 경기)
		stnId=105 (강원)
		stnId=131 (충청북도)
		stnId=133 (충청남도)
		stnId=146 (전라북도)
		stnId=156 (전라남도)
		stnId=143 (경상북도)
		stnId=159 (경상남도)
		stnId=184 (제주특별자치도)
			
		- 기상청 동네예보 웹서비스
		서울특별시 강남구 개포1동
		wideCode 시도 - 11 : 서울
		cityCode 시군구 - 680 : 강남구
		http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168000000
		wideCode
		[{"code":"11","value":"서울특별시"},{"code":"26","value":"부산광역시"},{"code":"27","value":"대구광역시"},{"code":"28","value":"인천광역시"},{"code":"29","value":"광주광역시"},{"code":"30","value":"대전광역시"},{"code":"31","value":"울산광역시"},{"code":"41","value":"경기도"},{"code":"42","value":"강원도"},{"code":"43","value":"충청북도"},{"code":"44","value":"충청남도"},{"code":"45","value":"전라북도"},{"code":"46","value":"전라남도"},{"code":"47","value":"경상북도"},{"code":"48","value":"경상남도"},{"code":"50","value":"제주특별자치도"}]
		cityCode
		[{"code":"11110","value":"종로구"},{"code":"11140","value":"중구"},{"code":"11170","value":"용산구"},{"code":"11200","value":"성동구"},{"code":"11215","value":"광진구"},{"code":"11230","value":"동대문구"},{"code":"11260","value":"중랑구"},{"code":"11290","value":"성북구"},{"code":"11305","value":"강북구"},{"code":"11320","value":"도봉구"},{"code":"11350","value":"노원구"},{"code":"11380","value":"은평구"},{"code":"11410","value":"서대문구"},{"code":"11440","value":""},{"code":"11470","value":"양천구"},{"code":"11500","value":"강서구"},{"code":"11530","value":"구로구"},{"code":"11545","value":"금천구"},{"code":"11560","value":"영등포구"},{"code":"11590","value":"동작구"},{"code":"11620","value":"관악구"},{"code":"11650","value":"서초구"},{"code":"11680","value":"강남구"},{"code":"11710","value":"송파구"},{"code":"11740","value":"강동구"}]
		
		출력예)
		서울,경기도 육상 중기예보 - 2016년 05월 13일 (금)요일 06:00 발표
		
		기압골의 영향으로 15~16일은 비가 오겠고, 그 밖의 날은 고기압의 영향으로 대체로 맑은 날이 많겠습니다.<br />기온은 평년(최저기온 : 10~13도, 최고기온 : 21~25도)과 비슷하거나 조금 높겠습니다.<br />강수량은 평년(2~5mm)보다 많겠습니다.<br />서해중부해상의 물결은 15일은 1.0~2.5m로 높게 일겠고, 그 밖의 날은 0.5~2.0m로 일겠습니다.
		
		도시 : 서울 
		    2016-05-16 00:00 / 흐리고 비 / 12~20 / 높음
		    2016-05-16 12:00 / 구름많음 / 12~20 / 보통
		    2016-05-17 00:00 / 구름조금 / 13~24 / 보통
		    2016-05-17 12:00 / 구름조금 / 13~24 / 보통
		    2016-05-18 00:00 / 구름조금 / 14~26 / 보통
		    2016-05-18 12:00 / 구름조금 / 14~26 / 보통
		    2016-05-19 00:00 / 구름많음 / 15~27 / 보통
		    2016-05-19 12:00 / 구름많음 / 15~27 / 보통
		    2016-05-20 00:00 / 구름조금 / 17~27 / 보통
		    2016-05-20 12:00 / 구름조금 / 17~27 / 보통
		    2016-05-21 00:00 / 구름조금 / 17~27 / 보통
		    2016-05-22 00:00 / 구름조금 / 18~27 / 보통
		    2016-05-23 00:00 / 구름조금 / 18~27 / 보통
		--------------------------
		
		*/

		Scanner sc = new Scanner(System.in);
		String[] stnId = { "108", "109", "105", "131", "133", "146", "156", "143", "159", "184" };

		do {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document xmlObj = null;

				// 외부 입력 처리 추가
				System.out.println("기상청 육상 중기예보");
				System.out.println("-------------------------");
				System.out.println("1. 전국");
				System.out.println("2. 서울, 경기");
				System.out.println("3. 강원");
				System.out.println("4. 충청북도");
				System.out.println("5. 충청남도");
				System.out.println("6. 전라북도");
				System.out.println("7. 전라남도");
				System.out.println("8. 경상북도");
				System.out.println("9. 경상남도");
				System.out.println("10. 제주특별자치도");
				System.out.print("지역 선택(0 quit)?");
				String m = sc.nextLine();
				if (m.equals("0")) {
					break;
				}

				String str = String.format("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=%s",
						stnId[Integer.parseInt(m) - 1]);
				URL url = new URL(str);
				InputSource is = new InputSource(url.openStream());
				xmlObj = builder.parse(is);

				// ROOT 엘리먼트 접근
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
					System.out.printf("도시 : %s %n", city);

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
				sc.nextLine(); // Enter키 포함
				System.out.println();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (true);
		
		System.out.println("프로그램 종료.");
		sc.close();
		
	}

}
