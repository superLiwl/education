/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 参评管理Entity
 * @author liwl
 * @version 2020-09-10
 */
@Table(name="review_term", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="review_name", attrName="reviewName", label="参评项", queryType=QueryType.LIKE),
		@Column(name="sort", attrName="sort", label="排序"),
		@Column(name="office_code", attrName="officeCode", label="参评单位"),
		@Column(name="office_name", attrName="officeName", label="参评单位名称", queryType=QueryType.LIKE),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
	}, orderBy="a.id DESC"
)
public class ReviewTerm extends DataEntity<ReviewTerm> {
	
	private static final long serialVersionUID = 1L;
	private String reviewName;		// 参评项
	private Integer sort;		// 排序
	private String officeCode;		// 参评单位
	private String officeName;		// 参评单位名称
	private Date createTime;		// 创建时间
	private List<ReviewTermOptions> reviewTermOptionsList = ListUtils.newArrayList();		// 子表列表
	
	public ReviewTerm() {
		this(null);
	}

	public ReviewTerm(String id){
		super(id);
	}
	
	@NotBlank(message="参评项不能为空")
	@Length(min=0, max=100, message="参评项长度不能超过 100 个字符")
	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@NotBlank(message="参评单位不能为空")
	@Length(min=0, max=64, message="参评单位长度不能超过 64 个字符")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<ReviewTermOptions> getReviewTermOptionsList() {
		return reviewTermOptionsList;
	}

	public void setReviewTermOptionsList(List<ReviewTermOptions> reviewTermOptionsList) {
		this.reviewTermOptionsList = reviewTermOptionsList;
	}
	
}