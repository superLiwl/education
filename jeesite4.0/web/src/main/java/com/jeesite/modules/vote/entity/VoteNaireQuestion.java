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
 * 卷题Entity
 * @author liwl
 * @version 2020-08-30
 */
@Table(name="vote_naire_question", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="naire_id", attrName="naireId", label="卷id"),
		@Column(name="question_id", attrName="questionId", label="题id"),
	}, orderBy="a.id DESC"
)
public class VoteNaireQuestion extends DataEntity<VoteNaireQuestion> {
	
	private static final long serialVersionUID = 1L;
	private String naireId;		// 卷id
	private String questionId;		// 题id
	
	public VoteNaireQuestion() {
		this(null);
	}

	public VoteNaireQuestion(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="卷id长度不能超过 64 个字符")
	public String getNaireId() {
		return naireId;
	}

	public void setNaireId(String naireId) {
		this.naireId = naireId;
	}
	
	@Length(min=0, max=255, message="题id长度不能超过 255 个字符")
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
}