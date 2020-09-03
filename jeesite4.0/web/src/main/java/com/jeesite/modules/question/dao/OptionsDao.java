/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.question.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.question.entity.Options;

/**
 * 问题DAO接口
 * @author liwl
 * @version 2020-08-29
 */
@MyBatisDao
public interface OptionsDao extends CrudDao<Options> {
	
}