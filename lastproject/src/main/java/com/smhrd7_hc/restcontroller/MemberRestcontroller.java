package com.smhrd7_hc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd7_hc.entity.DrugList;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.DrugListRepository;
import com.smhrd7_hc.repository.MemberRepository;
import com.smhrd7_hc.service.DrugSearchService;
import com.smhrd7_hc.service.MemberService;

@RestController
@RequestMapping(value = "/ajax/*")
public class MemberRestcontroller {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DrugListRepository drugListRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DrugSearchService drugSearchService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String kakaoMemberSearch(@RequestParam(value = "id") String id) {
		Member member = memberRepository.findOneById(id);

		String result = "false";
		if (member != null) {
			result = "true";
		}

		return result;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memberSearch(@RequestParam(value = "id") String id, @RequestParam(value = "pwd") String pwd) {

		Member member = memberRepository.findOneById(id);

		boolean res = passwordEncoder.matches(pwd, member.getPwd());

		String result = "false";
		if (res) {
			memberRepository.deleteById(id);
			result = "true";
		}

		return result;
	}

	@RequestMapping(value = "/drugImage", method = RequestMethod.GET)
	public DrugList drugImage(@RequestParam(value = "drugCode") String drugCode) {

		System.out.println("drugCode: " + drugCode);
		DrugList drugImg = drugListRepository.findOneByDrugCode(drugCode);

		return drugImg;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String memberUpdate(@RequestBody Member member) {
		System.out.println("넘어옴");
		memberService.memberUpdate(member);
		
		return "true";
	}
	
	@RequestMapping(value="/like", method = RequestMethod.GET)
	public String like(String id, String drugCode) {
		String result = drugSearchService.like(id, drugCode);
		return result;
	}
	@RequestMapping(value="/dislike", method = RequestMethod.GET)
	public String dislike(String id, String drugCode) {
		String result = drugSearchService.dislike(id, drugCode);
		return result;
	}

}
