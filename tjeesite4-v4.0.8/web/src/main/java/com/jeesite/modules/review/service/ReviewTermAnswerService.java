/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.review.entity.ReviewTermAnswer;
import com.jeesite.modules.review.dao.ReviewTermAnswerDao;

/**
 * 投票结果Service
 * @author liwl
 * @version 2020-09-11
 */
@Service
@Transactional(readOnly=true)
public class ReviewTermAnswerService extends CrudService<ReviewTermAnswerDao, ReviewTermAnswer> {
	
	/**
	 * 获取单条数据
	 * @param reviewTermAnswer
	 * @return
	 */
	@Override
	public ReviewTermAnswer get(ReviewTermAnswer reviewTermAnswer) {
		return super.get(reviewTermAnswer);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param reviewTermAnswer
	 * @return
	 */
	@Override
	public Page<ReviewTermAnswer> findPage(Page<ReviewTermAnswer> page, ReviewTermAnswer reviewTermAnswer) {
		return super.findPage(page, reviewTermAnswer);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param reviewTermAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ReviewTermAnswer reviewTermAnswer) {
		super.save(reviewTermAnswer);
	}
	
	/**
	 * 更新状态
	 * @param reviewTermAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ReviewTermAnswer reviewTermAnswer) {
		super.updateStatus(reviewTermAnswer);
	}
	
	/**
	 * 删除数据
	 * @param reviewTermAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ReviewTermAnswer reviewTermAnswer) {
		super.delete(reviewTermAnswer);
	}
	
}