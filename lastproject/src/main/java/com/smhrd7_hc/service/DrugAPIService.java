package com.smhrd7_hc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrugAPIService {

	public StringBuilder drugApi(String drugCode,String drugName) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=nOpbKEOOTeYPonSnPwFJ%2FbGrXOM4uVVRmGPmE8%2F%2BjvwOY%2BZOvY2P6ZMyelh0%2F4m8VX3granOhVp50XlIT3BdBA%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		// urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" +
		// URLEncoder.encode("3000", "UTF-8")); /*페이지번호*/
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode(drugCode, "UTF-8")); /* 품목기준코드 */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* 응답데이터 형식(xml/json) Default:xml */
		urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode(drugName, "UTF-8")); /*제품명*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb;
	}
	
	// json타입의 문자열을 입력하면 객체 형태로 반환해주는 함수
	public HashMap<String, Object> getDrugInfo(String jsonString) {
	    // Gson 객체 생성
	    Gson gson = new Gson();

	    // jsonString을 JsonObject로 변환
	    JsonObject obj = gson.fromJson(jsonString, JsonObject.class);

	    // JsonObject에서 "body" key 값을 가진 object를 가져와서 "items" key 값을 가진 array를 가져옴
	    JsonObject body = obj.getAsJsonObject("body");
	    JsonArray items = body.getAsJsonArray("items");

	    // 변환된 item들을 저장할 ArrayList 생성
	    List<HashMap<String, Object>> itemList = new ArrayList<>();

	    // items 배열에 있는 모든 item을 반복하면서, 각 item을 HashMap<String, Object> 형태로 변환
	    for (int i = 0; i < items.size(); i++) {
	        // 각 item을 JsonObject로 변환
	        JsonObject item = items.get(i).getAsJsonObject();

	        // 변환된 item을 저장할 HashMap 생성
	        HashMap<String, Object> itemMap = new HashMap<>();

	        // item의 모든 key-value 쌍을 반복하면서, 각 key-value 쌍을 HashMap<String, Object> 형태로 저장
	        for (Map.Entry<String, JsonElement> entry : item.entrySet()) {
	            // key 값을 가져옴
	            String key = entry.getKey();

	            // value 값을 가져옴
	            JsonElement value = entry.getValue();

	            // value 값이 JsonPrimitive인 경우에는 String 형태로 변환하여 HashMap에 저장
	            if (value.isJsonPrimitive()) {
	                itemMap.put(key, value.getAsString());
	            }
	            // value 값이 JsonArray인 경우에는 JsonArray 형태로 변환하여 HashMap에 저장
	            else if (value.isJsonArray()) {
	                itemMap.put(key, value.getAsJsonArray());
	            }
	            // value 값이 JsonObject인 경우에는 JsonObject 형태로 변환하여 HashMap에 저장
	            else if (value.isJsonObject()) {
	                itemMap.put(key, value.getAsJsonObject());
	            }
	        }

	        // 변환된 item을 itemList에 추가
	        itemList.add(itemMap);
	    }

	    // itemList에서 첫 번째 item을 반환함
	    return itemList.get(0);
	}

}
