package com.smhrd7_hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Member findOneById(String id);
	
	void deleteById(String id);
}
