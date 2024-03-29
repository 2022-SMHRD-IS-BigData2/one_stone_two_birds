package com.smhrd7_hc.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private final MemberService memberService;

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("Member", new Member());
		return "join";
	}

	@PostMapping("/join")
	public String join(@ModelAttribute("Member") Member member) {
		memberService.insert(member);
		return "redirect:/login";
	}

}