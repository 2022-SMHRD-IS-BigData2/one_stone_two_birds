package com.smhrd7_hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.DrugSearchRecordRepository;
import com.smhrd7_hc.repository.MemberRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/myPage/*")
public class MyPageController {

	@Autowired
	private final MemberRepository memberRepository;

	@Autowired
	private final DrugSearchRecordRepository drugSearchRecordRepository;

	@GetMapping("/edit")
	public String edit(Model model) {
		String result = "login";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& !"anonymousUser".equals(authentication.getPrincipal())) {
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();

		List<DrugSearchRecord> drugList = drugSearchRecordRepository.findByMemberId(id);

		if (drugList.size() > 0) {

			for (int i = 0; i < drugList.size(); i++) {
				drugList.get(i).getDrugSearchRecordPK().getId().setRoles(null);
			}
			model.addAttribute("drugList", drugList);
		}

		return "mypage";
	}

	@GetMapping("/like")
	public String like(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();

		List<DrugSearchRecord> drugList = drugSearchRecordRepository.findByMemberId(id);

		if (drugList.size() > 0) {

			for (int i = 0; i < drugList.size(); i++) {
				drugList.get(i).getDrugSearchRecordPK().getId().setRoles(null);
			}
			model.addAttribute("drugList", drugList);
		}
		return "like";
	}

}
