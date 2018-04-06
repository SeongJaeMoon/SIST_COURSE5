package com.test;

import java.util.*;

import java.io.*;
import java.net.*;

import org.json.*;

public class NaverMovie {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String clientId = "bTtH....glH";// ���ø����̼� Ŭ���̾�Ʈ ���̵�;
		String clientSecret = "7Az....W";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����;
		while (true) {
			try {

				System.out.print("�˻��� ��ȭ���� (0, ���α׷�����): ");
				String query = sc.nextLine();
				if (query.equals("0")) {
					break;
				}
				String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="
						+ URLEncoder.encode(query, "UTF-8"); // json ���
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) { // ���� ȣ��
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // ���� �߻�
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();

				try {

					JSONObject jobj = new JSONObject(response.toString());

					JSONArray jarray = jobj.getJSONArray("items");

					for (int a = 0; a < jarray.length(); a++) {

						JSONObject temp = jarray.getJSONObject(a);

						System.out.printf(
								" title: %s%n link: %s%n image: %s%n subtitle: %s%n pubDate: %s%n director: %s%n actor: %s%n userRating: %s%n",
								temp.getString("title"), temp.getString("link"), temp.getString("image"),
								temp.getString("subtitle"), temp.getString("pubDate"), temp.getString("director"),
								temp.getString("actor"), temp.getString("userRating"));
						System.out.println("--------------------");
					}
				} catch (Exception e) {
					System.out.println(e);
				}

				System.out.println("EnterŰ...");
				sc.nextLine();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		System.out.println("���α׷� ����.");
		sc.close();
	}
}