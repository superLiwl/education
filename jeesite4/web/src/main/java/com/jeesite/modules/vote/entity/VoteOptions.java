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
 * 问题Entity
 * @author liwl
 * @version 2020-08-26
 */
@Table(name="vote_options", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="question_id", attrName="questionId.id", label="问题表主键", isInsert=false, isUpdate=false, isQuery=false),
		@Column(name="sort", attrName="sort", label="排序"),
		@Column(name="type", attrName="type", label="类型", isInsert=false, isUpdate=false, isQuery=false),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="sign", attrName="sign", label="标识", isInsert=false, isUpdate=false, isQuery=false),
		@Column(name="tree_level", attrName="treeLevel", label="用于展示上级选项，值固定为1", isInsert=false, isUpdate=false, isQuery=false),
	}, orderBy="a.id ASC"
)
public class VoteOptions extends DataEntity<VoteOptions> {
	
	private static final long serialVersionUID = 1L;
	private VoteQuestion questionId;		// 问题表主键 父类
	private Integer sort;		// 排序
	private String type;		// 类型
	private String title;		// 标题
	private String sign;		// 标识
	
	public VoteOptions() {
		this(null);
	}


	public VoteOptions(VoteQuestion questionId){
		this.questionId = questionId;
	}
	
	@Length(min=0, max=64, message="问题表主键长度不能超过 64 个字符")
	public VoteQuestion getQuestionId() {
		return questionId;
	}

	public void setQuestionId(VoteQuestion questionId) {
		this.questionId = questionId;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=10, message="类型长度不能超过 10 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="标题长度不能超过 100 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=10, message="标识长度不能超过 10 个字符")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}