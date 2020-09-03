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
 * 投票结果Entity
 * @author liwl
 * @version 2020-08-27
 */
@Table(name="vote_answer", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户id"),
		@Column(name="question_id", attrName="questionId", label="选择的答案"),
		@Column(name="naire_id", attrName="naireId", label="问卷id"),
	}, orderBy="a.id DESC"
)
public class VoteAnswer extends DataEntity<VoteAnswer> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String questionId;		// 选择的答案
	private String naireId;		// 问卷id
	
	public VoteAnswer() {
		this(null);
	}

	public VoteAnswer(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="用户id长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=64, message="选择的答案长度不能超过 64 个字符")
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	@Length(min=0, max=64, message="问卷id长度不能超过 64 个字符")
	public String getNaireId() {
		return naireId;
	}

	public void setNaireId(String naireId) {
		this.naireId = naireId;
	}
	
}