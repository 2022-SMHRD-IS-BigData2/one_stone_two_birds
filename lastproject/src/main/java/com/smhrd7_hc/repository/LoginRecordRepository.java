package com.smhrd7_hc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.LoginRecord;

@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecord, Long> {
	
	@Query("SELECT lr FROM LoginRecord lr WHERE lr.id.id = :id ORDER BY lr.loginDate DESC")
	public List<LoginRecord> findLatestLoginRecord(@Param("id") String id, Pageable pageable);
}
