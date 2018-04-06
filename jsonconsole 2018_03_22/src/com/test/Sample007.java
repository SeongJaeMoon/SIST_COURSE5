package com.test;

import java.io.*;

import org.json.*;

public class Sample007 {

	public static void main(String[] args) {

		// 주의) json-20180130.jar 파일 필요

		try {
			JSONTokener tokener = new JSONTokener(new FileReader("members.json"));

			JSONObject jobj = new JSONObject(tokener);

			JSONArray jarray = jobj.getJSONArray("members");

			for (int a = 0; a < jarray.length(); ++a) {
				JSONObject temp = jarray.getJSONObject(a);

				System.out.println(temp.get("name"));
				System.out.println(temp.get("phone"));
				System.out.println("----------------");

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
