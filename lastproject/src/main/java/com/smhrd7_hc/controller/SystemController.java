package com.smhrd7_hc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

	@GetMapping("/system")
	public String system(HttpServletRequest request) {
		return "system";
	}

	@GetMapping("/system/create")
	public String create(HttpServletRequest request) {
		return "systemCreate";
	}

	@GetMapping("/system/delete")
	public String delete(HttpServletRequest request) {
		return "systemDelete";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
}