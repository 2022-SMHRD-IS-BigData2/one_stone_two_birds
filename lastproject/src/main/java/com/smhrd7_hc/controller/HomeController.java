package com.smhrd7_hc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.service.DrugAPIService;
import com.smhrd7_hc.service.DrugSearchService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	@Autowired
	private final DrugAPIService drugAPIService;

	@Autowired
	private DrugSearchService drugSearchService;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/info")
	public String info(Model model, @RequestParam(value = "drugCode", required = false) String drugCode,
			@RequestParam(value = "drugName", required = false) String drugName,
			@RequestParam(value = "yoloResult", required = false) String yoloResult) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		
		if(yoloResult != null) {
	        Gson gson = new Gson();
	        YoloResultDto yoloResultDto = gson.fromJson(yoloResult, YoloResultDto.class);
	        String detectedDrugCode = yoloResultDto.getDrugCode();
		}
		// 로그인 했을 경우 알약 코드로 검색이력 추가함
		if (id != null && !id.equals("anonymousUser")) {
			// 사진을 올려 검색한 경우
			if (drugCode != null) {
				DrugSearchRecord drugInfo = drugSearchService.drugSearchRecord(id, drugCode);

				drugName = "";

				if (drugInfo == null) {
					drugSearchService.inserRecord(id, drugCode);
				} else if (drugInfo != null) {
					drugSearchService.updateRecord(id, drugCode);
				}

				try {
					String drugString = drugAPIService.drugApi(drugCode, drugName).toString();
					HashMap<String, Object> result = drugAPIService.getDrugInfo(drugString);
					model.addAttribute("data", result);
				} catch (Exception e) {
					// 예외 처리
				}
				// 글을 써서 검색한 경우
			} else if (drugName != null) {
				drugCode = "";
				try {
					String drugString = drugAPIService.drugApi(drugCode, drugName).toString();

					drugCode = drugString.split("itemSeq\":\"")[1].split("\"")[0];

					DrugSearchRecord drugInfo = drugSearchService.drugSearchRecord(id, drugCode);

					if (drugInfo == null) {
						drugSearchService.inserRecord(id, drugCode);
					} else if (drugInfo != null) {
						drugSearchService.updateRecord(id, drugCode);
					}
					
					HashMap<String, Object> result = drugAPIService.getDrugInfo(drugString);
					model.addAttribute("data", result);

				} catch (Exception e) {
					// 예외 처리
				}
			}
			// 로그인하지 않은 경우
		} else {
			// 사진을 올려 검색한 경우
			if (drugCode != null) {
				drugName = "";
				try {
					String drugString = drugAPIService.drugApi(drugCode, drugName).toString();
					HashMap<String, Object> result = drugAPIService.getDrugInfo(drugString);
					model.addAttribute("data", result);
				} catch (Exception e) {
					// 예외 처리
				}
				// 글을 써서 검색한 경우
			} else if (drugName != null) {
				drugCode = "";
				try {
					String drugString = drugAPIService.drugApi(drugCode, drugName).toString();
					HashMap<String, Object> result = drugAPIService.getDrugInfo(drugString);
					model.addAttribute("data", result);
					model.addAttribute("testData", drugString);
				} catch (Exception e) {
					// 예외 처리
				}
				
			}
		}

		return "info";
	}

}