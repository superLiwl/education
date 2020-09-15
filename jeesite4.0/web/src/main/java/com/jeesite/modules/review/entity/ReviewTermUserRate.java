/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 用户权重Entity
 * @author liwl
 * @version 2020-09-15
 */
@Table(name="review_term_user_rate", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户id"),
		@Column(name="rate", attrName="rate", label="权重"),
	}, orderBy="a.id DESC"
)
public class ReviewTermUserRate extends DataEntity<ReviewTermUserRate> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String rate;		// 权重
	
	public ReviewTermUserRate() {
		this(null);
	}

	public ReviewTermUserRate(String id){
		super(id);
	}
	
	@Length(min=0, max=54, message="用户id长度不能超过 54 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=10, message="权重长度不能超过 10 个字符")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
}