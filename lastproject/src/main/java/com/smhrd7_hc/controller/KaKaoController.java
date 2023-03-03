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
@RequestMapping(value = "/kakao/*")
public class KaKaoController {


	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService ms;

//	@RequestMapping(value = "/kakao", method = {RequestMethod.GET})
//	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
//			HttpServletRequest request) throws Exception {
//		System.out.println("#########" + code);
//
//		String access_Token = ks.getAccessToken(code);
//		HashMap<String, Object> userInfo = ks.getUserInfo(access_Token);
//
////		Member member = new Member();
////		member.setId((String)userInfo.get("email"));
////		member.setNickname((String)userInfo.get("nickname"));
//
//		Member member = memberRepository.findOneById((String) userInfo.get("email"));
//		Member user = new Member();
//		String result = "signUp";
//		user.setId((String) userInfo.get("email"));
//		user.setNickname((String) userInfo.get("nickname"));
//		user.setPwd(access_Token);
//
//		if (member != null) {
//			result = "home";
//			System.out.println("###access_Token#### : " + access_Token);
//			System.out.println("###nickname#### : " + userInfo.get("nickname"));
//			System.out.println("###email#### : " + userInfo.get("email"));
//			System.out.println("###birthday### : " + userInfo.get("birthday"));
//
//			request.setAttribute("username", user.getId());
//			request.setAttribute("userpassword", access_Token);
//		} else {
//
//			model.addAttribute("userInfo", user);
//		}
//
//		return result;
//	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String kakaoLogin(@RequestParam(value = "username", required = false) String id,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "nickname", required = false) String nickname, Model model) throws Exception {

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

			System.out.println(password);

			model.addAttribute("userInfo", userInfo);

			nextPage = "signUp";
		}

		// 계정이 없을 경우 회원가입, 있을경우 로그인 하며 메인화면(지금은 login페이지로)
		return nextPage;

	}

	@RequestMapping(value = "/logout")
	public String kakaoLogout() {
		return "login";
	}

}