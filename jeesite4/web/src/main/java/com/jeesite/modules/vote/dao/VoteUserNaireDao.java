/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.vote.entity.VoteUserNaire;

/**
 * 投票用户DAO接口
 * @author liwl
 * @version 2020-08-29
 */
@MyBatisDao
public interface VoteUserNaireDao extends CrudDao<VoteUserNaire> {
	
}