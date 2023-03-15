package com.smhrd7_hc.controller;

import java.util.List;

import com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			@RequestParam(value = "drugName", required = false) String drugName) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();

		// 로그인 했을 경우 알약 코드로 검색이력 추가함
		if (id != null && !id.equals("anonymousUser")) {
			// 사진을 올려 검색한 경우
			if (drugCode != null) {
				DrugSearchRecord drugInfo = drugSearchService.drugSearchRecord(id, drugCode);

				drugName = "";

				if (drugInfo == null) {
					drugSearchService.inserRecord(id, drugCode);
				}else if (drugInfo != null) {
					drugSearchService.updateRecord(id, drugCode);
				}
				
				
				try {
					model.addAttribute("data", drugAPIService.drugApi(drugCode, drugName));
				} catch (Exception e) {
					// 예외 처리
				}
				// 글을 써서 검색한 경우
			} else if (drugName != null) {
				drugCode = "";
				try {
					StringBuilder result = drugAPIService.drugApi(drugCode, drugName);

					drugCode = result.toString().split("itemSeq\":\"")[1].split("\"")[0];

					DrugSearchRecord drugInfo = drugSearchService.drugSearchRecord(id, drugCode);

					if (drugInfo == null) {
						drugSearchService.inserRecord(id, drugCode);
					}else if (drugInfo != null) {
						drugSearchService.updateRecord(id, drugCode);
					}

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
					model.addAttribute("data", drugAPIService.drugApi(drugCode, drugName));
				} catch (Exception e) {
					// 예외 처리
				}
				// 글을 써서 검색한 경우
			} else if (drugName != null) {
				drugCode = "";
				try {
					model.addAttribute("data", drugAPIService.drugApi(drugCode, drugName));
				} catch (Exception e) {
					// 예외 처리
				}
			}
		}

		return "info";
	}

	@GetMapping("/test")
	public String test(Model model) {

		List<DrugSearchRecord> drugList = drugSearchService.drugFindAll();

		if (drugList.size() > 0) {
			for (int i = 0; i < drugList.size(); i++) {
				drugList.get(i).getDrugSearchRecordPK().getId().setRoles(null);
			}
			model.addAttribute("data", drugList);
		}

		return "test";
	}

}