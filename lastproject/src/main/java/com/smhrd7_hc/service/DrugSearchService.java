package com.smhrd7_hc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd7_hc.entity.DrugList;
import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.entity.Member;
import com.smhrd7_hc.repository.DrugListRepository;
import com.smhrd7_hc.repository.DrugSearchRecordRepository;
import com.smhrd7_hc.repository.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DrugSearchService {

	@Autowired
	DrugSearchRecordRepository drugSearchRecordRepository;

	@Autowired
	DrugListRepository drugListRepository;

	@Autowired
	MemberRepository memberRepository;

	public DrugSearchRecord drugSearchRecord(String id, String drugCode) {
		DrugSearchRecord drugList = drugSearchRecordRepository.findByMemberId(id, drugCode);

		return drugList;
	}

	public List<DrugSearchRecord> drugFindAll() {
		List<DrugSearchRecord> drugList = drugSearchRecordRepository.findAll();
		return drugList;
	}

	@Transactional
	public String like(String id, String drugCode) {
		DrugSearchRecord drugList = drugSearchRecordRepository.findByMemberId(id, drugCode);
		String result = "fail";
		if (drugList != null) {
			if (drugList.getPillLike() == 0) { // 좋아요를 누른적 없는 경우
				drugSearchRecordRepository.updatePillLike(1, id, drugCode);
				result = "up";
			} else if (drugList.getPillLike() == 1) { // 이미 좋아요를 누른 경우
				drugSearchRecordRepository.updatePillLike(0, id, drugCode);
				result = "down";
			}
		}
		return result;
	}

	@Transactional
	public String dislike(String id, String drugCode) {
		DrugSearchRecord drugList = drugSearchRecordRepository.findByMemberId(id, drugCode);
		String result = "fail";
		if (drugList != null) {
			if (drugList.getPillDislike() == 0) { // 싫어요를 누른적 없는 경우
				drugSearchRecordRepository.updatePillDislike(1, id, drugCode);
				result = "up";
			} else if (drugList.getPillDislike() == 1) { // 이미 좋아요를 누른 경우
				drugSearchRecordRepository.updatePillDislike(0, id, drugCode);
				result = "down";
			}
		}
		return result;
	}

	@Transactional
	public void inserRecord(String id, String drugCode) {
		DrugList drugList = drugListRepository.findOneByDrugCode(drugCode);
		Member member = memberRepository.findOneById(id);

		DrugSearchRecordPK pk = new DrugSearchRecordPK();
		pk.setDrugCode(drugList);
		pk.setId(member);

		DrugSearchRecord record = new DrugSearchRecord();
		record.setDrugSearchRecordPK(pk);
		record.setPillLike(0);
		record.setPillDislike(0);

		drugSearchRecordRepository.save(record);
	}

}
