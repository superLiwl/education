/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.service;

import java.util.List;
import java.util.Map;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.vote.entity.VoteQuestion;
import com.jeesite.modules.vote.dao.VoteQuestionDao;

/**
 * 问题Service
 * @author liwl
 * @version 2020-08-26
 */
@Service
@Transactional(readOnly=true)
public class VoteQuestionService extends CrudService<VoteQuestionDao, VoteQuestion> {

	@Autowired
	private VoteQuestionDao voteQuestionDao;
	/**
	 * 获取单条数据
	 * @param voteQuestion
	 * @return
	 */
	@Override
	public VoteQuestion get(VoteQuestion voteQuestion) {
		return super.get(voteQuestion);
	}
	
	/**
	 * 查询分页数据
	 * @param voteQuestion 查询条件
	 * @return
	 */
	@Override
	public Page<VoteQuestion> findPage(VoteQuestion voteQuestion) {
		return super.findPage(voteQuestion);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(VoteQuestion voteQuestion) {
		super.save(voteQuestion);
	}
	
	/**
	 * 更新状态
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(VoteQuestion voteQuestion) {
		super.updateStatus(voteQuestion);
	}
	
	/**
	 * 删除数据
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(VoteQuestion voteQuestion) {
		super.delete(voteQuestion);
	}

	public List<Map<String, Object>> findList(Map<String, Object> params){
		List<Map<String, Object>> result = voteQuestionDao.findList(params);
		if(null != result && !result.isEmpty() && result.size() > 0){
			for (Map<String, Object> m : result) {
				params.put("parentId",m.get("id"));
				m.put("subList",voteQuestionDao.findSubList(params));
			}
		}
		return result;
	}
}