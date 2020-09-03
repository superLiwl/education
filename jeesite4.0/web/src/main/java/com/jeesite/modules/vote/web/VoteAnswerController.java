/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.web;

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
import com.jeesite.modules.vote.entity.VoteAnswer;
import com.jeesite.modules.vote.service.VoteAnswerService;

/**
 * 投票结果Controller
 * @author liwl
 * @version 2020-08-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/voteAnswer")
public class VoteAnswerController extends BaseController {

	@Autowired
	private VoteAnswerService voteAnswerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public VoteAnswer get(String id, boolean isNewRecord) {
		return voteAnswerService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("vote:voteAnswer:view")
	@RequestMapping(value = {"list", ""})
	public String list(VoteAnswer voteAnswer, Model model) {
		model.addAttribute("voteAnswer", voteAnswer);
		return "modules/vote/voteAnswerList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("vote:voteAnswer:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<VoteAnswer> listData(VoteAnswer voteAnswer, HttpServletRequest request, HttpServletResponse response) {
		voteAnswer.setPage(new Page<>(request, response));
		Page<VoteAnswer> page = voteAnswerService.findPage(voteAnswer);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("vote:voteAnswer:view")
	@RequestMapping(value = "form")
	public String form(VoteAnswer voteAnswer, Model model) {
		model.addAttribute("voteAnswer", voteAnswer);
		return "modules/vote/voteAnswerForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("vote:voteAnswer:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated VoteAnswer voteAnswer) {
		voteAnswerService.save(voteAnswer);
		return renderResult(Global.TRUE, text("保存投票结果成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("vote:voteAnswer:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(VoteAnswer voteAnswer) {
		voteAnswerService.delete(voteAnswer);
		return renderResult(Global.TRUE, text("删除投票结果成功！"));
	}
	
}