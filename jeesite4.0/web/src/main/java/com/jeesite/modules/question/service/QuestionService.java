/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.question.service;

import com.jeesite.modules.vote.dao.VoteQuestionDao;
import com.jeesite.modules.vote.entity.VoteQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.question.entity.Question;
import com.jeesite.modules.question.dao.QuestionDao;
import com.jeesite.modules.question.entity.Options;
import com.jeesite.modules.question.dao.OptionsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 问题Service
 * @author liwl
 * @version 2020-08-29
 */
@Service
@Transactional(readOnly=true)
public class QuestionService extends CrudService<QuestionDao, Question> {
	
	@Autowired
	private OptionsDao voteOptionsDao;

	@Autowired
	private VoteQuestionDao subDao;

	/**
	 * 获取单条数据
	 * @param voteQuestion
	 * @return
	 */
	@Override
	public Question get(Question voteQuestion) {
		Question entity = super.get(voteQuestion);
		if (entity != null){
			Options voteOptions = new Options(entity);
			voteOptions.setStatus(Options.STATUS_NORMAL);
			entity.setVoteOptionsList(voteOptionsDao.findList(voteOptions));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param voteQuestion 查询条件
	 * @return
	 */
	@Override
	public Page<Question> findPage(Question voteQuestion) {
		return super.findPage(voteQuestion);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Question voteQuestion) {
		super.save(voteQuestion);
		// 保存 VoteQuestion子表
		for (Options voteOptions : voteQuestion.getVoteOptionsList()){
			if (!Options.STATUS_DELETE.equals(voteOptions.getStatus())){
				voteOptions.setQuestionId(voteQuestion);
				if (voteOptions.getIsNewRecord()){
					voteOptionsDao.insert(voteOptions);
				}else{
					voteOptionsDao.update(voteOptions);
				}
			}else{
				voteOptionsDao.delete(voteOptions);
			}
		}
		//在保存一份到主表中
		VoteQuestion sub = new VoteQuestion();
		sub.setParentId(voteQuestion.getId());
		subDao.deleteByEntity(sub);
		List<VoteQuestion> subList = new ArrayList<>();
		for (Options voteOptions : voteQuestion.getVoteOptionsList()){
			sub = new VoteQuestion();
			sub.setId(UUID.randomUUID().toString());
			sub.setParentId(voteQuestion.getId());
			sub.setSort(1);
			sub.setTitle(voteOptions.getTitle());
			sub.setTreeLevel("1");
			sub.setTreeLeaf("1");
			subList.add(sub);
		}
		if(null != subList && !subList.isEmpty() && subList.size() > 0){
			subDao.insertBatch(subList);
		}
	}
	
	/**
	 * 更新状态
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Question voteQuestion) {
		super.updateStatus(voteQuestion);
	}
	
	/**
	 * 删除数据
	 * @param voteQuestion
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Question voteQuestion) {
		super.delete(voteQuestion);
		Options voteOptions = new Options();
		voteOptions.setQuestionId(voteQuestion);
		voteOptionsDao.deleteByEntity(voteOptions);

		VoteQuestion sub = new VoteQuestion();
		sub.setParentId(voteQuestion.getId());
		subDao.deleteByEntity(sub);
		subDao.deleteByEntity(sub);
	}
	
}