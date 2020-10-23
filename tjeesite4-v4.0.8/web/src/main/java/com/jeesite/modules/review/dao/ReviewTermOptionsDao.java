/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.review.entity.ReviewTermOptions;

import java.util.List;
import java.util.Map;

/**
 * 参评管理DAO接口
 *
 * @author liwl
 * @version 2020-09-10
 */
@MyBatisDao
public interface ReviewTermOptionsDao extends CrudDao<ReviewTermOptions> {

    //查询参评单位
    List<Map<String, Object>> getReviewTermList();

    Map<String, Object> getDetailInfo(String id);

    //根据参评单位查询参评项目
    List<Map<String, Object>> getReviewTermListByOfficeCode(Map<String, Object> params);

    //根据参评项目查询参评人
    List<Map<String, Object>> getReviewTermOptionsListByTermId(Map<String, Object> params);

    //查询出投票类别
    List<String> getTremClass(Map<String, Object> params);

    //根据类别获取参选投票的数量
    Long getOptionsCountByClass(Map<String, Object> params);

    //根据类别、用户 --获取已经投过的票
    Long getAnswerOptionsByClass(Map<String, Object> params);

    //投票查询
    List<Map<String, Object>> searchList(Map<String, Object> params);

    //获取已投票结果
    List<Map<String, Object>> getHasChecked(Map<String, Object> params);

    //导出投票列表数据
    List<Map<String, Object>> exportDataByType(Map<String, Object> params);
}