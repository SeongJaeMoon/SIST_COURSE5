package com.test;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Sample002 {

	public static void main(String[] args) {
		
		//����) json-simple-1.1.1.jar ���� �ʿ�

		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("member.json"));
			
			JSONObject jobj = (JSONObject)obj;
			
			System.out.println(jobj.get("name"));
			System.out.println(jobj.get("phone"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
