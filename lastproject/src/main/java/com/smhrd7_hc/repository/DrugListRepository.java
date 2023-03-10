package com.smhrd7_hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd7_hc.entity.DrugList;

public interface DrugListRepository extends JpaRepository<DrugList, String> {
	
	DrugList findOneByDrugCode(String drugCode);
}
