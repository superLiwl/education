/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.question.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 问题Entity
 * @author liwl
 * @version 2020-08-29
 */
@Table(name="vote_options", alias="a", columns={
		@Column(name="id", attrName="id", label="主键id", isPK=true),
		@Column(name="title", attrName="title", label="title", queryType=QueryType.LIKE),
		@Column(name="question_id", attrName="questionId.id", label="问题表主键"),
	}, orderBy="a.id ASC"
)
public class Options extends DataEntity<Options> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title
	private Question questionId;		// 问题表主键 父类
	
	public Options() {
		this(null);
	}


	public Options(Question questionId){
		this.questionId = questionId;
	}
	
	@Length(min=0, max=50, message="title长度不能超过 50 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=64, message="问题表主键长度不能超过 64 个字符")
	public Question getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}
	
}