/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.question.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.question.entity.Question;
import com.jeesite.modules.question.service.QuestionService;
import com.jeesite.modules.vote.entity.VoteQuestion;
import com.jeesite.modules.vote.service.VoteQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 问题Controller
 * @author liwl
 * @version 2020-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/question/voteQuestion")
public class QuestionController extends BaseController {

	@Autowired
	private QuestionService voteQuestionService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Question get(String id, boolean isNewRecord) {
		return voteQuestionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("question:voteQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(Question voteQuestion, Model model) {
		model.addAttribute("voteQuestion", voteQuestion);
		return "modules/question/voteQuestionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("question:voteQuestion:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Question> listData(Question voteQuestion, HttpServletRequest request, HttpServletResponse response) {
		voteQuestion.setPage(new Page<>(request, response));
		voteQuestion.setParentId("0");
		Page<Question> page = voteQuestionService.findPage(voteQuestion);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("question:voteQuestion:view")
	@RequestMapping(value = "form")
	public String form(Question voteQuestion, Model model) {
		model.addAttribute("voteQuestion", voteQuestion);
		return "modules/question/voteQuestionForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("question:voteQuestion:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Question voteQuestion) {
		voteQuestionService.save(voteQuestion);
		return renderResult(Global.TRUE, text("保存问题成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("question:voteQuestion:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Question voteQuestion) {
		voteQuestionService.delete(voteQuestion);
		return renderResult(Global.TRUE, text("删除问题成功！"));
	}
	
}