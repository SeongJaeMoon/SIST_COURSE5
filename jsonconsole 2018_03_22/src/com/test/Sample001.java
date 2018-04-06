package com.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Sample001 {

	public static void main(String[] args) {

		//주의) json-simple-1.1.1.jar 파일 필요

		JSONParser parser = new JSONParser();

		String s = "{ \"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
		
		try {
			
			Object obj = parser.parse(s);
			JSONObject jobj = (JSONObject)obj;
			
			String name = (String)jobj.get("name");
			long age = (long)jobj.get("age");
			int age2 = Integer.parseInt(jobj.get("age").toString());
						
			System.out.println(name);
			System.out.println(age);
			System.out.println(age2);
			System.out.println(jobj.get("city"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
