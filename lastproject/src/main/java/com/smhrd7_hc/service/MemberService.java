package com.smhrd7_hc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.entity.Role;
import com.smhrd7_hc.repository.MemberRepository;
import com.smhrd7_hc.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class MemberService {
 
	private final MemberRepository memberRepository;
 
	private final RoleRepository roleRepository;
 
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
 
	public void insert(Member member) {
		member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		Set<Role> rolesSet = new HashSet<Role>();
		rolesSet.add(roleRepository.findOneById(2L)); // id : 2 ROLE_USER 역할
		memberRepository.save(member.toEntity(rolesSet));
	}
	
	public HashMap<String, Object> getUserInfo(String access_Token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

			JsonObject properties = jsonObject.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = jsonObject.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
}