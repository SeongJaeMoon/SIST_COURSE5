package com.test;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Sample004 {

	public static void main(String[] args) {
		
		//주의) json-simple-1.1.1.jar 파일 필요
		
		// 문제) breakfast_menu.json 파일의 정보를 콘솔창에 출력
		/*
		 * 출력 예) <breakfast_menu> name / price / calories / picture / description 1.
		 * XXXX / XXXX / XXXXXXX / XXXXX / XXXXXXXXX 2. 3. 4. 5.
		 */

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("breakfast_menu.json"));

			JSONObject jobj = (JSONObject) obj;
			JSONObject jobjj = (JSONObject) jobj.get("breakfast_menu");
			JSONArray jarray = (JSONArray) jobjj.get("food");
			System.out.println("name / price / calories / picture / description");
			// Array는 인덱스 제공
			for (int a = 0; a < jarray.size(); ++a) {
				JSONObject temp = (JSONObject) jarray.get(a);
				String name = (String) temp.get("name");
				String price = (String) temp.get("price");
				String description = (String) temp.get("description");
				String calories = (String) temp.get("calories");
				String picture = (String) temp.get("picture");
				System.out.printf("%d %s /%s /%s /%s /%s \n", (a + 1), name, price, description, calories, picture);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
