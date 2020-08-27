/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.vote.entity.VoteNaire;

import java.util.List;
import java.util.Map;

/**
 * 问卷DAO接口
 * @author liwl
 * @version 2020-08-26
 */
@MyBatisDao
public interface VoteNaireDao extends CrudDao<VoteNaire> {
    void updateStatues(Map<String, Object> params);

    List<String> checkQuesIds(Map<String, Object> params);
}