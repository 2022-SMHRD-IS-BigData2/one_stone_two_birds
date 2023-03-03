package com.smhrd7_hc.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체생성자
@Data
//@SuppressWarnings("serial")
public class DrugSearchRecord{
	
	@EmbeddedId
	private DrugSearchRecordPK drugSearchRecordPK;

}
