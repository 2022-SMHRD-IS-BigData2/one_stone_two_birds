package com.smhrd7_hc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.MemberRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/myPage/*")
public class MyPageController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/edit")
	public String edit(Model model) {
		String result = "login";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
			String username = authentication.getName();
			Member userInfo = memberRepository.findOneById(username);
			userInfo.setPwd("");
			model.addAttribute("userInfo", userInfo);
			result = "edit";
		}
		return result;
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		return "mypage";
	}
	
	@GetMapping("/like")
	public String like(Model model) {
		return "like";
	}

}
