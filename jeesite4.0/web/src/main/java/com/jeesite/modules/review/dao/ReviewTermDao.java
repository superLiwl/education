/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.review.entity.RankVo;
import com.jeesite.modules.review.entity.ReviewTerm;

import java.util.List;
import java.util.Map;

/**
 * 参评管理DAO接口
 *
 * @author liwl
 * @version 2020-09-10
 */
@MyBatisDao
public interface ReviewTermDao extends CrudDao<ReviewTerm> {
    //查询投票结果数据
    List<Map<String, Object>> listRankData(RankVo rankVo);
    Long listRankDataCount(RankVo rankVo);
}