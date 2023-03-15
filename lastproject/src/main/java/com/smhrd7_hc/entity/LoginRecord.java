package com.smhrd7_hc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRecord {

	@Id
	@Column(length = 100)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginNo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Member.class)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Member id;

	@Column(updatable = false, insertable = false, columnDefinition = "datetime default now()")
	private Date loginDate;

}
