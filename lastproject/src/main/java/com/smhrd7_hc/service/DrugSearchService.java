package com.smhrd7_hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd7_hc.entity.DrugSearchRecord;
import com.smhrd7_hc.entity.DrugSearchRecordPK;
import com.smhrd7_hc.repository.DrugSearchRecordRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DrugSearchService {
	
	@Autowired
	DrugSearchRecordRepository drugSearchRecordRepository;
	
//	public void like(DrugSearchRecordPK drugSearchRecordPK) {
//		List<DrugSearchRecord> drugList = drugSearchRecordRepository.findByMemberId(drugSearchRecordPK);
//		
//		if(drugList != null) {
//			System.out.println(drugList);
//		}
//	}
//	
//	public void dislike(DrugSearchRecordPK drugSearchRecordPK) {
//		List<DrugSearchRecord> durgList = drugSearchRecordRepository.findByDrugSearchRecordPK(drugSearchRecordPK);
//	}

}
