/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.review.entity.ReviewTermUserRate;
import com.jeesite.modules.review.service.ReviewTermUserRateService;

/**
 * 用户权重Controller
 * @author liwl
 * @version 2020-09-15
 */
@Controller
@RequestMapping(value = "${adminPath}/review/reviewTermUserRate")
public class ReviewTermUserRateController extends BaseController {

	@Autowired
	private ReviewTermUserRateService reviewTermUserRateService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ReviewTermUserRate get(String id, boolean isNewRecord) {
		return reviewTermUserRateService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("review:reviewTermUserRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReviewTermUserRate reviewTermUserRate, Model model) {
		model.addAttribute("reviewTermUserRate", reviewTermUserRate);
		return "modules/review/reviewTermUserRateList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("review:reviewTermUserRate:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ReviewTermUserRate> listData(ReviewTermUserRate reviewTermUserRate, HttpServletRequest request, HttpServletResponse response) {
		Page<ReviewTermUserRate> page = reviewTermUserRateService.findPage(new Page<ReviewTermUserRate>(request, response), reviewTermUserRate); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("review:reviewTermUserRate:view")
	@RequestMapping(value = "form")
	public String form(ReviewTermUserRate reviewTermUserRate, Model model) {
		model.addAttribute("reviewTermUserRate", reviewTermUserRate);
		return "modules/review/reviewTermUserRateForm";
	}

	/**
	 * 保存用户权重
	 */
	@RequiresPermissions("review:reviewTermUserRate:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ReviewTermUserRate reviewTermUserRate) {
		reviewTermUserRateService.save(reviewTermUserRate);
		return renderResult(Global.TRUE, text("保存用户权重成功！"));
	}
	
	/**
	 * 删除用户权重
	 */
	@RequiresPermissions("review:reviewTermUserRate:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ReviewTermUserRate reviewTermUserRate) {
		reviewTermUserRateService.delete(reviewTermUserRate);
		return renderResult(Global.TRUE, text("删除用户权重成功！"));
	}
	
}