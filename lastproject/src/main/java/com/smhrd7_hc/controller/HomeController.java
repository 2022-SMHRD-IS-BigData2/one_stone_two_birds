package com.smhrd7_hc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "/home" })
	public String home() {
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
	public String info() {
		return "info";
	}
}