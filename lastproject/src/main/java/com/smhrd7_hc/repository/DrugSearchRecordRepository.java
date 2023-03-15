package com.smhrd7_hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.entity.Member;

@Repository
public interface DrugSearchRecordRepository extends JpaRepository<DrugSearchRecord, DrugSearchRecordPK> {

	@Query("SELECT dsr FROM DrugSearchRecord dsr WHERE dsr.drugSearchRecordPK.id.id = :memberId ORDER BY dsr.searchDate DESC")
	List<DrugSearchRecord> findByMemberId(@Param("memberId") String memberId);

	@Query("SELECT dsr FROM DrugSearchRecord dsr WHERE dsr.drugSearchRecordPK.id.id = :memberId AND dsr.drugSearchRecordPK.drugCode.drugCode = :drugCode")
	DrugSearchRecord findByMemberId(@Param("memberId") String memberId, @Param("drugCode") String drugCode);

	@Modifying
	@Query("UPDATE DrugSearchRecord dsr SET dsr.pillLike = :pillLike WHERE dsr.drugSearchRecordPK.id.id = :memberId AND dsr.drugSearchRecordPK.drugCode.drugCode = :drugCode ")
	void updatePillLike(@Param("pillLike") Integer pillLike, @Param("memberId") String memberId,
			@Param("drugCode") String drugCode);

	@Modifying
	@Query("UPDATE DrugSearchRecord dsr SET dsr.pillDislike = :pillDislike WHERE dsr.drugSearchRecordPK.id.id = :memberId AND dsr.drugSearchRecordPK.drugCode.drugCode = :drugCode ")
	void updatePillDislike(@Param("pillDislike") Integer pillDisLike, @Param("memberId") String memberId,
			@Param("drugCode") String drugCode);

	@Modifying
	@Query("UPDATE DrugSearchRecord dsr SET dsr.searchDate = CURRENT_TIMESTAMP WHERE dsr.drugSearchRecordPK.id.id = :memberId AND dsr.drugSearchRecordPK.drugCode.drugCode = :drugCode")
	void updateSearchDate(@Param("memberId") String memberId, @Param("drugCode") String drugCode);
}
