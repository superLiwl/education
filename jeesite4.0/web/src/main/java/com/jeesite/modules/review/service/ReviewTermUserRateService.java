/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.review.entity.ReviewTermUserRate;
import com.jeesite.modules.review.dao.ReviewTermUserRateDao;

/**
 * 用户权重Service
 * @author liwl
 * @version 2020-09-15
 */
@Service
@Transactional(readOnly=true)
public class ReviewTermUserRateService extends CrudService<ReviewTermUserRateDao, ReviewTermUserRate> {
	
	/**
	 * 获取单条数据
	 * @param reviewTermUserRate
	 * @return
	 */
	@Override
	public ReviewTermUserRate get(ReviewTermUserRate reviewTermUserRate) {
		return super.get(reviewTermUserRate);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param reviewTermUserRate
	 * @return
	 */
	@Override
	public Page<ReviewTermUserRate> findPage(Page<ReviewTermUserRate> page, ReviewTermUserRate reviewTermUserRate) {
		return super.findPage(page, reviewTermUserRate);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param reviewTermUserRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ReviewTermUserRate reviewTermUserRate) {
		super.save(reviewTermUserRate);
	}
	
	/**
	 * 更新状态
	 * @param reviewTermUserRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ReviewTermUserRate reviewTermUserRate) {
		super.updateStatus(reviewTermUserRate);
	}
	
	/**
	 * 删除数据
	 * @param reviewTermUserRate
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ReviewTermUserRate reviewTermUserRate) {
		super.delete(reviewTermUserRate);
	}
	
}