/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.vote.entity.VoteNaire;

/**
 * 问卷DAO接口
 * @author liwl
 * @version 2020-08-26
 */
@MyBatisDao
public interface VoteNaireDao extends CrudDao<VoteNaire> {
	
}