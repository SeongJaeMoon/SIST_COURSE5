package com.test;

import java.util.*;

import java.io.*;
import java.net.*;

import org.json.*;

public class NaverMovie {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String clientId = "bTtH....glH";// 애플리케이션 클라이언트 아이디값;
		String clientSecret = "7Az....W";// 애플리케이션 클라이언트 시크릿값;
		while (true) {
			try {

				System.out.print("검색할 영화제목 (0, 프로그램종료): ");
				String query = sc.nextLine();
				if (query.equals("0")) {
					break;
				}
				String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="
						+ URLEncoder.encode(query, "UTF-8"); // json 결과
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
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

				System.out.println("Enter키...");
				sc.nextLine();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		System.out.println("프로그램 종료.");
		sc.close();
	}
}