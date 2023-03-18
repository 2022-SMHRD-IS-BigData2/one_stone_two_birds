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
	
	public HashMap<String, Object> getDrugInfo(String jsonString) {
	    Gson gson = new Gson();
	    JsonObject obj = gson.fromJson(jsonString, JsonObject.class);
	    JsonObject body = obj.getAsJsonObject("body");
	    JsonArray items = body.getAsJsonArray("items");
	    List<HashMap<String, Object>> itemList = new ArrayList<>();

	    for (int i = 0; i < items.size(); i++) {
	        JsonObject item = items.get(i).getAsJsonObject();
	        HashMap<String, Object> itemMap = new HashMap<>();

	        for (Map.Entry<String, JsonElement> entry : item.entrySet()) {
	            String key = entry.getKey();
	            JsonElement value = entry.getValue();

	            if (value.isJsonPrimitive()) {
	                itemMap.put(key, value.getAsString());
	            } else if (value.isJsonArray()) {
	                itemMap.put(key, value.getAsJsonArray());
	            } else if (value.isJsonObject()) {
	                itemMap.put(key, value.getAsJsonObject());
	            }
	        }

	        itemList.add(itemMap);
	    }

	    return itemList.get(0);
	}
}
