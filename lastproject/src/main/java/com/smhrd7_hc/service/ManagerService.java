package com.smhrd7_hc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.LoginRecord;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.DrugSearchRecordRepository;
import com.smhrd7_hc.repository.LoginRecordRepository;
import com.smhrd7_hc.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

	@Autowired
	private final MemberRepository memberRepository;

	@Autowired
	private final LoginRecordRepository loginRecordRepository;
	
	@Autowired
	private final DrugSearchRecordRepository drugSearchRecordRepository;

	private Gson gson = new Gson();

	// 회원 성별에 연령별로 묶어 데이터를 반환
	public String ageGenderJoinCount() {
		List<Member> userList = memberRepository.findAll();

		LinkedHashMap<String, LinkedHashMap<Integer, Integer>> genderMap = new LinkedHashMap<>();

		if (userList.size() > 0) {
			for (Member user : userList) {
				String gender = user.getGender();
				String birthday = user.getBirthday();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(birthday, formatter);
				int currentYear = LocalDate.now().getYear();
				int yearDifference = currentYear - date.getYear() + 1;
				yearDifference = (yearDifference / 10) * 10;
				if (yearDifference <= 0) {
					yearDifference += 10;
				}
				int age = yearDifference;

				LinkedHashMap<Integer, Integer> ageMap = genderMap.get(gender);
				if (ageMap == null) {
					ageMap = new LinkedHashMap<>();
					genderMap.put(gender, ageMap);
				}

				for (int i = 10; i <= 80; i += 10) {
					ageMap.put(i, ageMap.getOrDefault(i, 0));
				}
				ageMap.put(age, ageMap.getOrDefault(age, 0) + 1);
			}
		}

		for (LinkedHashMap<Integer, Integer> ageMap : genderMap.values()) {
			for (int i = 10; i <= 80; i += 10) {
				ageMap.putIfAbsent(i, 0);
			}
		}

		String json = gson.toJson(genderMap);

		return json;
	}

	// 로그인 이력을 날짜별로 누적시켜 반환
	public String LoginCount() {
		List<LoginRecord> loginList = loginRecordRepository.findAll();

		LocalDateTime now = LocalDateTime.now(); // 현재 날짜와 시간을 가져옴
		LocalDateTime startDate = now.minusDays(6); // 1주일 전 날짜

		HashMap<String, Integer> loginCountMap = new HashMap<>();

		for (LoginRecord login : loginList) {
			LocalDateTime loginDate = login.getLoginDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			// java.util.Date 객체인 login.getLoginDate()를 LocalDateTime으로 변환

			if (loginDate.isAfter(startDate) && loginDate.isBefore(now.plusDays(1))) {
				String key = loginDate.toLocalDate().toString();
				loginCountMap.put(key, loginCountMap.getOrDefault(key, 0) + 1);
			}
		}

		String json = gson.toJson(loginCountMap);

		return json;
	}

	// 사는 지역별로 누적시켜서 반환하는 함수
	public String LivingAreaCount() {
		List<Member> userList = memberRepository.findAll();
		HashMap<String, Integer> areaCountMap = new HashMap<>();
		if (userList.size() > 0) {
			for (Member member : userList) {
				String livingArea = member.getLivingArea();

				areaCountMap.put(livingArea, areaCountMap.getOrDefault(livingArea, 0) + 1);

			}
		}

		String json = gson.toJson(areaCountMap);

		return json;
	}
	
	// 검색된 약의 기록을 가져와 반환하는 함수
	public Map<String, List<Map<String, Object>>> drugSearchCount() {
	    List<DrugSearchRecord> drugSearchList = drugSearchRecordRepository.findAll();
	    LocalDate today = LocalDate.now();
	    LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
	    LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
	    Map<String, Integer> todayCountMap = new HashMap<>();
	    Map<String, Integer> weekCountMap = new HashMap<>();
	    Map<String, Integer> monthCountMap = new HashMap<>();

	    if (drugSearchList.size() > 0) {
	        for (DrugSearchRecord drug : drugSearchList) {
	            String drugName = drug.getDrugSearchRecordPK().getDrugCode().getDrugName();
	            String drugMarkerName = drug.getDrugSearchRecordPK().getDrugCode().getMakerName();
	            Date searchDate = drug.getSearchDate();
	            LocalDate searchLocalDate = searchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	            if (searchLocalDate.equals(today)) {
	                Integer count = todayCountMap.getOrDefault(drugName, 0);
	                todayCountMap.put(drugName, count + 1);
	            }

	            if (searchLocalDate.isAfter(startOfWeek) || searchLocalDate.isEqual(startOfWeek)) {
	                Integer count = weekCountMap.getOrDefault(drugName, 0);
	                weekCountMap.put(drugName, count + 1);
	            }

	            if (searchLocalDate.isAfter(startOfMonth) || searchLocalDate.isEqual(startOfMonth)) {
	                Integer count = monthCountMap.getOrDefault(drugName, 0);
	                monthCountMap.put(drugName, count + 1);
	            }
	        }
	    }

	 // Today data
	    List<Map<String, Object>> todayDataSorted = todayCountMap.entrySet().stream()
	            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	            .map(entry -> {
	                Map<String, Object> map = new LinkedHashMap<>();
	                String drugName = entry.getKey();
	                String drugMarkerName = drugSearchList.stream()
	                        .filter(drug -> drug.getDrugSearchRecordPK().getDrugCode().getDrugName().equals(drugName))
	                        .findFirst()
	                        .orElseThrow(() -> new RuntimeException("No drug found for drug name: " + drugName))
	                        .getDrugSearchRecordPK().getDrugCode().getMakerName();
	                map.put("drugName", drugName);
	                map.put("makerName", drugMarkerName);
	                map.put("count", entry.getValue());
	                return map;
	            })
	            .collect(Collectors.toList());

	    // Week data
	    List<Map<String, Object>> weekDataSorted = weekCountMap.entrySet().stream()
	            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	            .map(entry -> {
	                Map<String, Object> map = new LinkedHashMap<>();
	                String drugName = entry.getKey();
	                String drugMarkerName = drugSearchList.stream()
	                        .filter(drug -> drug.getDrugSearchRecordPK().getDrugCode().getDrugName().equals(drugName))
	                        .findFirst()
	                        .orElseThrow(() -> new RuntimeException("No drug found for drug name: " + drugName))
	                        .getDrugSearchRecordPK().getDrugCode().getMakerName();
	                map.put("drugName", drugName);
	                map.put("makerName", drugMarkerName);
	                map.put("count", entry.getValue());
	                return map;
	            })
	            .collect(Collectors.toList());

	    // Month data
	    List<Map<String, Object>> monthDataSorted = monthCountMap.entrySet().stream()
	            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	            .map(entry -> {
	                Map<String, Object> map = new LinkedHashMap<>();
	                String drugName = entry.getKey();
	                String drugMarkerName = drugSearchList.stream()
	                        .filter(drug -> drug.getDrugSearchRecordPK().getDrugCode().getDrugName().equals(drugName))
	                        .findFirst()
	                        .orElseThrow(() -> new RuntimeException("No drug found for drug name: " + drugName))
	                        .getDrugSearchRecordPK().getDrugCode().getMakerName();
	                map.put("drugName", drugName);
	                map.put("makerName", drugMarkerName);
	                map.put("count", entry.getValue());
	                return map;
	            })
	            .collect(Collectors.toList());

	    Map<String, List<Map<String, Object>>> result = new HashMap<>();
	    result.put("today", todayDataSorted);
	    result.put("week", weekDataSorted);
	    result.put("month", monthDataSorted);

	    return result;
	}

}
