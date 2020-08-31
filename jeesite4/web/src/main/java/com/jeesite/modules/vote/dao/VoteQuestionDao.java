/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.vote.entity.VoteQuestion;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.List;
import java.util.Map;

/**
 * 问题DAO接口
 * @author liwl
 * @version 2020-08-26
 */
@MyBatisDao
public interface VoteQuestionDao extends CrudDao<VoteQuestion> {

	List<Map<String, Object>> findList(Map<String, Object> params);

	List<Map<String, Object>> findSubList(Map<String, Object> params);

}