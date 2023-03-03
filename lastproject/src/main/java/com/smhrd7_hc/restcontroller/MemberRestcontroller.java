package com.smhrd7_hc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.MemberRepository;

@RestController
@RequestMapping(value="/ajax/*")
public class MemberRestcontroller {

	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String kakaoMemberSearch(@RequestParam(value="id") String id) {
		Member member = memberRepository.findOneById(id);
		
		System.out.println("전송완료");
		String result = "false";
		if(member != null) {
			result = "true";
			System.out.println("회원 있음");
		}
				
		return result;
	}
	
	@RequestMapping(value="/roadSearch", method= RequestMethod.GET)
	public Member kakaoRoadSearch(@RequestParam(value="roadAdrress") String roadAdrress) {
		
		System.out.println(roadAdrress);
		
		Member member = new Member();
		
		return member;
	}
}
