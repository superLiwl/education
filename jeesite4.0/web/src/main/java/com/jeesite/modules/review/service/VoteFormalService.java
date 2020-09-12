/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.modules.review.dao.ReviewTermAnswerDao;
import com.jeesite.modules.review.dao.ReviewTermOptionsDao;
import com.jeesite.modules.review.entity.ReviewTermAnswer;
import com.jeesite.modules.sys.utils.DictUtils;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.entity.VoteNaire;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private ReviewTermAnswerDao reviewTermAnswerDao;

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
                m.put("review_name",DictUtils.getDictLabel("term_option",String.valueOf(m.get("review_name")),""));
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
                params.put("allOptionCount", allOptionCount);
                params.put("userId", userId);
                //获取已选的投票数据
                answerList = reviewTermOptionsDao.getAnswerOptionsByClass(params);
                if (null != answerList) {
                    params.put("hasOptionCount", answerList.size());
                }
                params.remove("userId");
                params.put("tremClass",DictUtils.getDictLabel("term_option",c,""));
                resultList.add(params);
            }
        }
        return resultList;
    }

    /**
     * 确定投票
     */
    @Transactional(readOnly = false)
    public String submitAnswer(String optionIds) {
        if (StringUtils.isEmpty(optionIds)) {
            return "投票失败，未选择参评人";
        }
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = login.getId();
        String arry[] = optionIds.split(",");
        List<ReviewTermAnswer> list = new ArrayList<>();
        ReviewTermAnswer answer;
        for (String s : arry) {
            if (!StringUtils.isEmpty(s)) {
                answer = new ReviewTermAnswer();
                answer.setOptionId(s);
                answer.setUserId(userId);
                answer.setId(UUID.randomUUID().toString());
                list.add(answer);
            }
        }
        if (null == list || list.isEmpty() || list.size() == 0) {
            return "投票失败，未选择参评人";
        }
        //先执行删除
        ReviewTermAnswer del = new ReviewTermAnswer();
        del.setUserId(userId);
        reviewTermAnswerDao.deleteByEntity(del);
        //在执行插入
        reviewTermAnswerDao.insertBatch(list);
        return "投票成功";
    }

}