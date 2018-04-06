package com.test;

import java.io.*;

import org.json.*;

public class Sample006 {

	public static void main(String[] args) {
		
		//����) json-20180130.jar ���� �ʿ�

		try {
			JSONTokener tokener = new JSONTokener(new FileReader("member.json"));
			
			JSONObject jobj = new JSONObject(tokener);
			
			String name = jobj.getString("name");
			String phone = jobj.getString("phone");
			
			System.out.println(name);
			System.out.println(phone);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
