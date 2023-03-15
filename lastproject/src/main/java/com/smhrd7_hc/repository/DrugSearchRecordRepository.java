package com.smhrd7_hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.entity.Member;

@Repository
public interface DrugSearchRecordRepository extends JpaRepository<DrugSearchRecord, DrugSearchRecordPK> {

    @Query("SELECT dsr FROM DrugSearchRecord dsr WHERE dsr.drugSearchRecordPK.id.id = :memberId")
    List<DrugSearchRecord> findByMemberId(@Param("memberId") String memberId);
	
	
}
