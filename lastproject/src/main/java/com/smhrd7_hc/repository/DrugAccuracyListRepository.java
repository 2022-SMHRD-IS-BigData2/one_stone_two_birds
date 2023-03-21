package com.smhrd7_hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.DrugAccuracyList;


@Repository
public interface DrugAccuracyListRepository extends JpaRepository<DrugAccuracyList, Long> {
	
	
	
}
