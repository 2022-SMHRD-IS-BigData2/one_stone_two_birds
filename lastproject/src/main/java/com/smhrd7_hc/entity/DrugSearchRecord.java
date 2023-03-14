package com.smhrd7_hc.entity;

import javax.persistence.Column;
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
	
	@Column(length = 1, columnDefinition = "Integer default 0")
	private Integer pillLike;
	
	@Column(length = 1, columnDefinition = "Integer default 0")
	private Integer pillDislike;
}
