package com.smhrd7_hc.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd7_hc.service.DrugAPIService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private final DrugAPIService drugAPIService;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        
        model.addAttribute("userInfo", user);
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
}