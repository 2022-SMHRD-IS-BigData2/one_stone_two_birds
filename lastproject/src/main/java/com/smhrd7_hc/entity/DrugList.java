package com.smhrd7_hc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class DrugList {

	@Id
	@Column(length = 20)
	private String drugCode;

	private String itemImage;

}
