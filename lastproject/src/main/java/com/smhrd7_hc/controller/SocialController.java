package com.smhrd7_hc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.MemberRepository;
import com.smhrd7_hc.service.MemberService;

@Controller
@RequestMapping(value = "/social/*")
public class SocialController {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService ms;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String kakaoLogin(@RequestParam(value = "username", required = false) String id,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "birthday", required = false) String birthday, Model model) throws Exception {

		Member member = memberRepository.findOneById(id);
		String nextPage = "";

		// 계정이 있을 경우
		if (member != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password, member.getPwd())) {
				System.out.println("비교성공");
				member.setPwd(password);
			}
			model.addAttribute("userInfo", member);

			nextPage = "login";

		} else { // 계정이 없을 경우

			// 계정이 없을때의 페이지이동
			Member userInfo = new Member();
			userInfo.setId(id);
			userInfo.setPwd(password);
			userInfo.setNickname(nickname);
			userInfo.setGender(gender);
			userInfo.setBirthday(birthday);

			System.out.println(gender);
			System.out.println(birthday);

			model.addAttribute("userInfo", userInfo);

			nextPage = "join";
		}

		// 계정이 없을 경우 회원가입, 있을경우 로그인 하며 메인화면(지금은 login페이지로)
		return nextPage;

	}

	@RequestMapping(value = "/logout")
	public String kakaoLogout() {
		return "login";
	}

	@RequestMapping("/callback")
	public String callback() {
		return "callback";
	}

}