package com.smhrd7_hc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd7_hc.entity.DrugList;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.DrugListRepository;
import com.smhrd7_hc.repository.MemberRepository;

@RestController
@RequestMapping(value = "/ajax/*")
public class MemberRestcontroller {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DrugListRepository drugListRepository;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String kakaoMemberSearch(@RequestParam(value = "id") String id) {
		Member member = memberRepository.findOneById(id);

		String result = "false";
		if (member != null) {
			result = "true";
		}

		return result;
	}
	
	@RequestMapping(value="/drugImage", method = RequestMethod.GET)
	public DrugList drugImage(@RequestParam(value = "drugCode") String drugCode) {
		
		System.out.println("drugCode: "+drugCode);
		DrugList drugImg = drugListRepository.findOneByDrugCode(drugCode);
		
		return drugImg;
	}

}
