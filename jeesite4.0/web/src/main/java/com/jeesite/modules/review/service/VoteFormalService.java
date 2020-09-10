/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.review.dao.ReviewTermOptionsDao;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.entity.VoteNaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷Service
 *
 * @author liwl
 * @version 2020-08-26
 */
@Service
@Transactional(readOnly = true)
public class VoteFormalService extends CrudService<VoteNaireDao, VoteNaire> {
    @Autowired
    private ReviewTermOptionsDao reviewTermOptionsDao;

    /**
     * 查询参评单位
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> getReviewTermList() {
        return reviewTermOptionsDao.getReviewTermList();
    }

    /**
     * getReviewTermListByOfficeCode
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> getReviewTermListByOfficeCode(String officeCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("officeCode", officeCode);
        List<Map<String, Object>> result = reviewTermOptionsDao.getReviewTermListByOfficeCode(params);
        if (null != result && !result.isEmpty() && result.size() > 0) {
            for (Map<String, Object> m : result) {
                params = new HashMap<>();
                params.put("termId", m.get("id"));
                m.put("options", reviewTermOptionsDao.getReviewTermOptionsListByTermId(params));
            }
        }
        return result;
    }

}