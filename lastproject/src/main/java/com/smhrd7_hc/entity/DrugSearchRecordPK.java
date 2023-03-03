package com.smhrd7_hc.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Embeddable
public class DrugSearchRecordPK implements Serializable{
	
	private int drugCode;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id")
	private Member id;

	
}