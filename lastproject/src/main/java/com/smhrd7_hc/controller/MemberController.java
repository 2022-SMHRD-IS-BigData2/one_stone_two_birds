package com.smhrd7_hc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.smhrd7_hc.repository.MemberRepository;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository repository;

}
