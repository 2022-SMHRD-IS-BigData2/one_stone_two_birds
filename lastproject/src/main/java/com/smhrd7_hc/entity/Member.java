package com.smhrd7_hc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor // 기본생성자
@Data
@DynamicUpdate
@Table(name = "member")
public class Member {

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 100)
	private String pwd;

	@Column(updatable = false, insertable = false, columnDefinition = "datetime default now()")
	private Date indate;

	@Column(length = 40)
	private String nickname;

	@Column(length = 60)
	private String birthday;

	@Column(length = 10, columnDefinition = "enum('male', 'female')")
	private String gender;

	@Column(length = 100)
	private String livingArea;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Builder
	public Member(String id, String pwd, String nickname, String birthday, String gender, String livingArea,
			Set<Role> roles) {
		this.id = id;
		this.pwd = pwd;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.livingArea = livingArea;
		this.roles = roles;
	}

	public Member toEntity(Set<Role> roles) {
		return Member.builder().id(id).pwd(pwd).nickname(nickname).birthday(birthday).gender(gender)
				.livingArea(livingArea).roles(roles).build();
	}

}
