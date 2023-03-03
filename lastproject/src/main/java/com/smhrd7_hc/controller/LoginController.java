package com.smhrd7_hc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

	private final MemberService memberService;

	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		System.out.println(request);
		return "login";
	}

	@GetMapping("/signUp")
	public String signUp(Model model) {
		model.addAttribute("Member", new Member());
		return "signUp";
	}

	@PostMapping("/signUp")
	public String signUp(@ModelAttribute("Member") Member member) {
		memberService.insert(member);
		return "redirect:/login";
	}
	
}