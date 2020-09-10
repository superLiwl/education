/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 参评管理Entity
 * @author liwl
 * @version 2020-09-10
 */
@Table(name="review_term_options", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="term_id", attrName="termId.id", label="参评表id"),
		@Column(name="option_name", attrName="optionName", label="名称", queryType=QueryType.LIKE),
		@Column(name="position", attrName="position", label="职位"),
		@Column(name="head_img", attrName="headImg", label="头像"),
		@Column(name="sort", attrName="sort", label="sort"),
	}, orderBy="a.id ASC"
)
public class ReviewTermOptions extends DataEntity<ReviewTermOptions> {
	
	private static final long serialVersionUID = 1L;
	private ReviewTerm termId;		// 参评表id 父类
	private String optionName;		// 名称
	private String position;		// 职位
	private String headImg;		// 头像
	private Integer sort;		// sort
	
	public ReviewTermOptions() {
		this(null);
	}


	public ReviewTermOptions(ReviewTerm termId){
		this.termId = termId;
	}
	
	public ReviewTerm getTermId() {
		return termId;
	}

	public void setTermId(ReviewTerm termId) {
		this.termId = termId;
	}
	
	@NotBlank(message="名称不能为空")
	@Length(min=0, max=100, message="名称长度不能超过 100 个字符")
	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	@Length(min=0, max=100, message="职位长度不能超过 100 个字符")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=255, message="头像长度不能超过 255 个字符")
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	@NotNull(message="sort不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}