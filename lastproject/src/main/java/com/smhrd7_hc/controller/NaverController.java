package com.smhrd7_hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/naver/*")
public class NaverController {
	
	@RequestMapping("/callback")
	public String callback() {
		return "callback";
	}

}
