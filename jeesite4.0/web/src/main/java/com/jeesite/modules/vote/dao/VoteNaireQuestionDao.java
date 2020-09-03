/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.vote.entity.VoteNaireQuestion;

/**
 * 卷题DAO接口
 * @author liwl
 * @version 2020-08-30
 */
@MyBatisDao
public interface VoteNaireQuestionDao extends CrudDao<VoteNaireQuestion> {
	
}