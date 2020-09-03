/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.vote.entity.VoteAnswer;
import com.jeesite.modules.vote.dao.VoteAnswerDao;

/**
 * 投票结果Service
 * @author liwl
 * @version 2020-08-27
 */
@Service
@Transactional(readOnly=true)
public class VoteAnswerService extends CrudService<VoteAnswerDao, VoteAnswer> {
	
	/**
	 * 获取单条数据
	 * @param voteAnswer
	 * @return
	 */
	@Override
	public VoteAnswer get(VoteAnswer voteAnswer) {
		return super.get(voteAnswer);
	}
	
	/**
	 * 查询分页数据
	 * @param voteAnswer 查询条件
	 * @param voteAnswer.page 分页对象
	 * @return
	 */
	@Override
	public Page<VoteAnswer> findPage(VoteAnswer voteAnswer) {
		return super.findPage(voteAnswer);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param voteAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(VoteAnswer voteAnswer) {
		super.save(voteAnswer);
	}
	
	/**
	 * 更新状态
	 * @param voteAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(VoteAnswer voteAnswer) {
		super.updateStatus(voteAnswer);
	}
	
	/**
	 * 删除数据
	 * @param voteAnswer
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(VoteAnswer voteAnswer) {
		super.delete(voteAnswer);
	}
	
}