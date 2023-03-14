package com.smhrd7_hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;

@Repository
public interface DrugSearchRecordRepository extends JpaRepository<DrugSearchRecord, DrugSearchRecordPK> {

	List<DrugSearchRecord> findByDrugSearchRecordPK(DrugSearchRecordPK drugSearchRecordPK);
	
}
