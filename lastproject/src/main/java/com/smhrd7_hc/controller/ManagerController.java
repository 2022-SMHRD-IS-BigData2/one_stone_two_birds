package com.smhrd7_hc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd7_hc.service.ManagerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/manager/*")
public class ManagerController {

	@Autowired
	private final ManagerService managerService;
	
	@GetMapping("/")
	public String managerPage(Model model) {
		
		String ageGenderCount = managerService.ageGenderJoinCount();
		String loginCount = managerService.LoginCount();
		String areaCount = managerService.LivingAreaCount();
		Map<String, List<Map<String, Object>>> drugSearchCount = managerService.drugSearchCount();
		
		model.addAttribute("ageGenderCountData", ageGenderCount);
		model.addAttribute("loginCountData", loginCount);
		model.addAttribute("areaCountData", areaCount);
		model.addAttribute("drugSearchCountData", drugSearchCount);
		
		
		return "manager";
	}
}
