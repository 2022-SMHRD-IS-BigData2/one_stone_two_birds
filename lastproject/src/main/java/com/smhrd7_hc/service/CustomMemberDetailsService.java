package com.smhrd7_hc.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smhrd7_hc.entity.LoginRecord;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.entity.Role;
import com.smhrd7_hc.repository.LoginRecordRepository;
import com.smhrd7_hc.repository.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private LoginRecordRepository loginRecordRepository;
	
	@Override
	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(String id) {

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Member member = memberRepository.findOneById(id);

		if (member != null) {
			for (Role role : member.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); // DB에 저장되어 있는 권한을 부여한다.
			}

			String memberId = member.getId();
			List<LoginRecord> latestLoginRecord = loginRecordRepository.findLatestLoginRecord(memberId,
					PageRequest.of(0, 1));
			LoginRecord firstloginRecord = latestLoginRecord.isEmpty() ? null : latestLoginRecord.get(0);

			if (firstloginRecord != null) { // 로그인 한적있는지 검사
				String loginDate = firstloginRecord.getLoginDate().toString().split(" ")[0];
				String nowDate = LocalDate.now().toString();

				if (!(loginDate.equals(nowDate))) { // 오늘 로그인 했는지 검사
					LoginRecord loginRecord = new LoginRecord();
					loginRecord.setId(member);
					loginRecordRepository.save(loginRecord);
				}
			} else {
				LoginRecord loginRecord = new LoginRecord();
				loginRecord.setId(member);
				loginRecordRepository.save(loginRecord);

			}
			

			return new User(member.getId(), member.getPwd(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("can not find User : " + id);
		}
	}
	
}