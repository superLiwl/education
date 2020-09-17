/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.review.entity.RankVo;
import com.jeesite.modules.review.entity.UserRateVo;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.utils.EmpUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.review.entity.ReviewTerm;
import com.jeesite.modules.review.service.ReviewTermService;

import java.util.Date;
import java.util.Map;

/**
 * 参评管理Controller
 *
 * @author liwl
 * @version 2020-09-10
 */
@Controller
@RequestMapping(value = "${adminPath}/review/reviewTerm")
public class ReviewTermController extends BaseController {

    @Autowired
    private ReviewTermService reviewTermService;

    /**
     * 获取数据
     */
    @ModelAttribute
    public ReviewTerm get(String id, boolean isNewRecord) {
        return reviewTermService.get(id, isNewRecord);
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("review:reviewTerm:view")
    @RequestMapping(value = {"list", ""})
    public String list(ReviewTerm reviewTerm, Model model) {
        model.addAttribute("reviewTerm", reviewTerm);
        return "modules/review/reviewTermList";
    }

    /**
     * 查询列表数据
     */
    @RequiresPermissions("review:reviewTerm:view")
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<ReviewTerm> listData(ReviewTerm reviewTerm, HttpServletRequest request, HttpServletResponse response) {
        Page<ReviewTerm> page = reviewTermService.findPage(new Page<ReviewTerm>(request, response), reviewTerm);
        return page;
    }



    /**
     * 投票结果
     */
    @RequiresPermissions("review:reviewTerm:view")
    @RequestMapping(value = {"listRank", ""})
    public String listRank(RankVo rankVo, Model model) {
        model.addAttribute("rankVo", rankVo);
        return "modules/review/reviewTermRank";
    }

    /**
     * 查询投票结果数据
     */
    @RequiresPermissions("review:reviewTerm:view")
    @RequestMapping(value = "listRankData")
    @ResponseBody
    public Page<Map<String,Object>> listRankData(RankVo rankVo, HttpServletRequest request, HttpServletResponse response) {
        Page<Map<String,Object>> page = new Page<Map<String,Object>>(request, response);
        rankVo.setPageNo(page.getPageNo());
        rankVo.setPageSize(page.getPageSize());
        page.setList(reviewTermService.listRankData(rankVo));
        page.setCount(reviewTermService.listRankDataCount(rankVo));
        return page;
    }

    /**
     * 查看编辑表单
     */
    @RequiresPermissions("review:reviewTerm:view")
    @RequestMapping(value = "form")
    public String form(ReviewTerm reviewTerm, Model model) {
        model.addAttribute("reviewTerm", reviewTerm);
        return "modules/review/reviewTermForm";
    }

    /**
     * 保存参评管理
     */
    @RequiresPermissions("review:reviewTerm:edit")
    @PostMapping(value = "save")
    @ResponseBody
    public String save(@Validated ReviewTerm reviewTerm) {
        if (StringUtils.isEmpty(reviewTerm.getId())) {
            reviewTerm.setCreateTime(new Date());
        }
        Office office = EmpUtils.getOffice(reviewTerm.getOfficeCode());
        reviewTerm.setOfficeName(office.getOfficeName());
        reviewTermService.save(reviewTerm);
        return renderResult(Global.TRUE, text("保存参评管理成功！"));
    }

    /**
     * 删除参评管理
     */
    @RequiresPermissions("review:reviewTerm:edit")
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(ReviewTerm reviewTerm) {
        reviewTermService.delete(reviewTerm);
        return renderResult(Global.TRUE, text("删除参评管理成功！"));
    }

    /**
     * 查询列表
     */
    @RequestMapping(value = {"gongshi", ""})
    public String gongshi() {
        return "modules/voteformal/gongshi";
    }


    /**
     * 保存参评管理
     */
    @RequiresPermissions("review:reviewTerm:edit")
    @PostMapping(value = "saveReviewTermUserRate")
    @ResponseBody
    public String saveReviewTermUserRate(UserRateVo userVateVo) {
        String msg = reviewTermService.saveReviewTermUserRate(userVateVo);
        return renderResult(Global.TRUE, text(msg));
    }

}