/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 问卷Entity
 * @author liwl
 * @version 2020-08-26
 */
@Table(name="vote_naire", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="问卷名称", queryType=QueryType.LIKE),
		@Column(name="content", attrName="content", label="问卷描述"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="statues", attrName="statues", label="状态", comment="状态：1-草稿，2-发布，3-结束"),
		@Column(name="del_flag", attrName="delFlag", label="0-未删除，1-已删除"),
		@Column(name="sort", attrName="sort", label="排序"),
	}, orderBy="a.id DESC"
)
public class VoteNaire extends DataEntity<VoteNaire> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 问卷名称
	private String content;		// 问卷描述
	private Date createTime;		// 创建时间
	private String statues;		// 状态：1-草稿，2-发布，3-结束
	private String delFlag;		// 0-未删除，1-已删除
	private Integer sort;		// 排序
	
	public VoteNaire() {
		this(null);
	}

	public VoteNaire(String id){
		super(id);
	}
	
	@Length(min=0, max=50, message="问卷名称长度不能超过 50 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=1, message="状态长度不能超过 1 个字符")
	public String getStatues() {
		return statues;
	}

	public void setStatues(String statues) {
		this.statues = statues;
	}
	
	@Length(min=0, max=1, message="0-未删除，1-已删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}