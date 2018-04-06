package com.test;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Sample003 {

	public static void main(String[] args) {

		//주의) json-simple-1.1.1.jar 파일 필요
		
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("members.json"));

			JSONObject jobj = (JSONObject) obj;

			JSONArray jarray = (JSONArray) jobj.get("members");
			
			for (int a = 0; a<jarray.size(); ++a) {
				JSONObject temp = (JSONObject) jarray.get(a);
				System.out.println(temp.get("name"));
				System.out.println(temp.get("phone"));
				System.out.println("----------------");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
