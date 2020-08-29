/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.question.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 问题Entity
 * @author liwl
 * @version 2020-08-29
 */
@Table(name="vote_question", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="sort", attrName="sort", label="排序"),
		@Column(name="tree_level", attrName="treeLevel", label="用于展示下级选项，值固定为0", isQuery=false),
		@Column(name="parent_id", attrName="parentId", label="父id"),
		@Column(name="tree_leaf", attrName="treeLeaf", label="tree_leaf", isQuery=false),
	}, orderBy="a.id DESC"
)
public class Question extends DataEntity<Question> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Integer sort;		// 排序
	private String parentId;		// 父id
	private List<Options> voteOptionsList = ListUtils.newArrayList();		// 子表列表
	
	public Question() {
		this(null);
	}

	public Question(String id){
		super(id);
	}
	
	@Length(min=0, max=200, message="标题长度不能超过 200 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=64, message="父id长度不能超过 64 个字符")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public List<Options> getVoteOptionsList() {
		return voteOptionsList;
	}

	public void setVoteOptionsList(List<Options> voteOptionsList) {
		this.voteOptionsList = voteOptionsList;
	}
	
}