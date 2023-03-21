package com.smhrd7_hc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class DrugAccuracyList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Idx;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id")
	private Member id;
	
	@Column(updatable = false, insertable = false, columnDefinition = "datetime default now()")
	private Date indate;
	
	private String drugCode; // 예측답 
	
	private String accuracy; // 예측 확률

}
