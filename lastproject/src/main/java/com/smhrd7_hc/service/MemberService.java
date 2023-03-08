package com.smhrd7_hc.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.entity.Role;
import com.smhrd7_hc.repository.MemberRepository;
import com.smhrd7_hc.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	private final RoleRepository roleRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void insert(Member member) {
		member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		Set<Role> rolesSet = new HashSet<Role>();
		rolesSet.add(roleRepository.findOneById(2L)); // id : 2 ROLE_USER 역할
		memberRepository.save(member.toEntity(rolesSet));
	}

}