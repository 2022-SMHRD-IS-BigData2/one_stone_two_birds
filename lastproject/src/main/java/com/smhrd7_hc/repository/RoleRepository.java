package com.smhrd7_hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd7_hc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneById(Long id);
}