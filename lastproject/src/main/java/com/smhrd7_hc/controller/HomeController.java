package com.smhrd7_hc.controller;


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
	public String info(Model model,@RequestParam(value="drugCode") String drugCode) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		
		if(!(id == null || id.equals("anonymousUser"))) {
			DrugSearchRecord drugInfo = drugSearchService.drugSearchRecord(id, drugCode);
			if(drugInfo == null) {
				drugSearchService.inserRecord(id, drugCode);
			}
		}
		
		
		try {
			model.addAttribute("data",  drugAPIService.drugApi(drugCode));
			
		} catch (Exception e) {
		}
		
		return "info";
	}
	

}