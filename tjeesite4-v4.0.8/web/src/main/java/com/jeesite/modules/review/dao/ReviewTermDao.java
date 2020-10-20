/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.review.entity.RankVo;
import com.jeesite.modules.review.entity.ReviewTerm;
import com.jeesite.modules.review.entity.VoteInfoVo;

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

    //查询投票结果总数
    Long listRankDataCount(RankVo rankVo);

    //查询个人投票情况
    List<Map<String, Object>> listVoteData(VoteInfoVo voteInfoVo);

    //查询个人投票总数
    Long listVoteDataCount(VoteInfoVo voteInfoVo);

    Long listVoteStatisticsData(Map<String, Object> params);
}