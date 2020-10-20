/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 投票用户Entity
 * @author liwl
 * @version 2020-08-29
 */
@Table(name="vote_user_naire", alias="a", columns={
		@Column(name="id", attrName="id", label="主键id", isPK=true),
		@Column(name="user_code", attrName="userCode", label="用户编码"),
		@Column(name="naire_id", attrName="naireId", label="问卷id"),
		@Column(name="proportion", attrName="proportion", label="投票比例"),
	}, orderBy="a.id DESC"
)
public class VoteUserNaire extends DataEntity<VoteUserNaire> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户编码
	private String naireId;		// 问卷id
	private String proportion;		// 投票比例
	
	public VoteUserNaire() {
		this(null);
	}

	public VoteUserNaire(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="用户编码长度不能超过 64 个字符")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=64, message="问卷id长度不能超过 64 个字符")
	public String getNaireId() {
		return naireId;
	}

	public void setNaireId(String naireId) {
		this.naireId = naireId;
	}
	
	@Length(min=0, max=6, message="投票比例长度不能超过 6 个字符")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
}