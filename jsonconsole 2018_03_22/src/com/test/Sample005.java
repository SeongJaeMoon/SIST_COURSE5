package com.test;

import org.json.*;

public class Sample005 {

	public static void main(String[] args) {

		//주의) json-20180130.jar 파일 필요
		
		String s = "{ \"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
		
		JSONObject jobj = new JSONObject(s);
		
		String name = jobj.getString("name");
		int age = jobj.getInt("age");
		String city = jobj.getString("city");
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(city);
		
	}
	
}
