/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import com.jeesite.common.service.CrudService;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.modules.review.dao.ReviewTermOptionsDao;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.entity.VoteNaire;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
     * 根据组织编码获取下边的投票项与参评人
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

    /**
     * 获取总投票数，已投票数，当前登陆人已经投过的选项
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> getAnswerInfo() {
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = login.getId();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<String> classList = reviewTermOptionsDao.getTremClass();
        if (null != classList && !classList.isEmpty() && classList.size() > 0) {
            Map<String, Object> params;
            Long allOptionCount;
            List<String> answerList;
            for (String c : classList) {
                params = new HashMap<>();
                params.put("tremClass", c);
                //获取投票总数
                allOptionCount = reviewTermOptionsDao.getOptionsCountByClass(params);
                params.put("allOptionCount",allOptionCount);
                params.put("userId", userId);
                //获取已选的投票数据
                answerList = reviewTermOptionsDao.getAnswerOptionsByClass(params);
                if(null != answerList){
                    params.put("hasOptionCount",answerList.size());
                }
                params.remove("userId");
                resultList.add(params);
            }
        }
        return resultList;
    }

}