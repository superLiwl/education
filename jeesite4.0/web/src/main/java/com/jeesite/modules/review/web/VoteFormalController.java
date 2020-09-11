/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.web;

import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.review.entity.ReviewTermAnswer;
import com.jeesite.modules.review.service.ReviewTermAnswerService;
import com.jeesite.modules.review.service.VoteFormalService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 问卷Controller
 *
 * @author liwl
 * @version 2020-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/formal")
public class VoteFormalController extends BaseController {
    @Autowired
    private VoteFormalService voteFormalService;
    @Autowired
    private ReviewTermAnswerService reviewTermAnswerService;

    /**
     * 公示
     */
    @RequestMapping(value = {"gongshi", ""})
    public String gongshi() {
        return "modules/voteformal/gongshi";
    }

    /**
     * 投票页面
     */
    @RequestMapping(value = {"toupiao", ""})
    public String toupiao() {
        return "modules/voteformal/toupiao";
    }

    /**
     * 查询组织机构
     */
    @RequestMapping(value = "getReviewTermList")
    @ResponseBody
    public List<Map<String, Object>> getReviewTermList() {
        return voteFormalService.getReviewTermList();
    }

    /**
     * 根据机构查询下级数据
     */
    @RequestMapping(value = "getReviewTermListByOfficeCode")
    @ResponseBody
    public List<Map<String, Object>> getReviewTermListByOfficeCode(String officeCode) {
        return voteFormalService.getReviewTermListByOfficeCode(officeCode);
    }

    /**
     * 获取投票数据
     */
    @RequestMapping(value = "getAnswerInfo")
    @ResponseBody
    public List<Map<String, Object>> getAnswerInfo() {
        return voteFormalService.getAnswerInfo();
    }

    /**
     * 确定投票
     */
    @RequestMapping(value = "submitAnswer")
    @ResponseBody
    public String submitAnswer(String optionIds) {
        return voteFormalService.submitAnswer(optionIds);
    }

    /**
     * 获取所有投票信息
     */
    @RequestMapping(value = "getAllAnswer")
    @ResponseBody
    public List<ReviewTermAnswer> getAllAnswer() {
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = login.getId();
        ReviewTermAnswer select = new ReviewTermAnswer();
        select.setUserId(userId);
        return reviewTermAnswerService.findList(select);
    }

}