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

    //根据参评单位查询参评项目
    List<Map<String, Object>> getReviewTermListByOfficeCode(Map<String, Object> params);

    //根据参评项目查询参评人
    List<Map<String, Object>> getReviewTermOptionsListByTermId(Map<String, Object> params);
}