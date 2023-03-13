package com.smhrd7_hc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd7_hc.service.DrugAPIService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private final DrugAPIService drugAPIService;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	@GetMapping("/info")
	public String info(Model model) {
		try {
			model.addAttribute("data",  drugAPIService.drugApi());
			
		} catch (Exception e) {
		}
		
		return "info";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		return "edit";
	}
}