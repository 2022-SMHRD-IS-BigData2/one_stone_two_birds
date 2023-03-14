package com.smhrd7_hc.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.entity.Role;
import com.smhrd7_hc.repository.MemberRepository;
import com.smhrd7_hc.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private final MemberRepository memberRepository;

	@Autowired
	private final RoleRepository roleRepository;

	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PasswordEncoder encoder;

	public void insert(Member member) {
		member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		Set<Role> rolesSet = new HashSet<Role>();
		rolesSet.add(roleRepository.findOneById(2L)); // id : 2 ROLE_USER 역할
		memberRepository.save(member.toEntity(rolesSet));
	}
	
	@Transactional
	public void memberUpdate(Member member) {
	    Optional<Member> optional = memberRepository.findById(member.getId());
	    System.out.println("이것은: ");
	    System.out.println(optional);
	    if (optional.isPresent()) {
	        Member persistence = optional.get();
	        String rawPassword = member.getPwd();
	        System.out.println(rawPassword);
	        String encPassword = encoder.encode(rawPassword);
	        persistence.setPwd(encPassword);
	        persistence.setBirthday(member.getBirthday());
	        persistence.setGender(member.getGender());
	        persistence.setLivingArea(member.getLivingArea());
	        persistence.setNickname(member.getNickname());
	    } else {
	        throw new IllegalArgumentException("회원 찾기 실패");
	    }
	}
	

}