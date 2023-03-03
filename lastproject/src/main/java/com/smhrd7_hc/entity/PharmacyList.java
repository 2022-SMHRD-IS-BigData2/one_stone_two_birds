package com.smhrd7_hc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pharmacyList")
public class PharmacyList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pharmacyNo;
	
	@Column(nullable=false)
	private String PharmacyName;
	
	@Column(nullable=false)
	private String pharmacyAdrees;
	
	@Column()
	private String pharmacyPhoneNumber;
	
	@Column(nullable=false)
	private String pharmacyStartEndTime;
	
	@Column(columnDefinition = "varchar(20) default '평일'")
	private String pharmacyDivision;


}
