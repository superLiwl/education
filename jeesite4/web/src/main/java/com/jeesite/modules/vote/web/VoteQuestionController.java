/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.ior.ObjectKey;
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
import com.jeesite.modules.vote.entity.VoteQuestion;
import com.jeesite.modules.vote.service.VoteQuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题Controller
 * @author liwl
 * @version 2020-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/voteQuestion")
public class VoteQuestionController extends BaseController {

	@Autowired
	private VoteQuestionService voteQuestionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public VoteQuestion get(String id, boolean isNewRecord) {
		return voteQuestionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("vote:voteQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(VoteQuestion voteQuestion, Model model) {
		model.addAttribute("voteQuestion", voteQuestion);
		return "modules/vote/voteQuestionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("vote:voteQuestion:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Map<String, Object>> listData(VoteQuestion voteQuestion, String ctrlPermi) {
		System.out.println(ctrlPermi);
		Map<String, Object> params = new HashMap<>();
		if(null == voteQuestion.getParentCode()){
			params.put("treeLevel","0");
		}else{
			params.put("parentId",voteQuestion.getParentCode());
		}
		List<Map<String, Object>> result =voteQuestionService.findList(params);
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		page.setList(result);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("vote:voteQuestion:view")
	@RequestMapping(value = "form")
	public String form(VoteQuestion voteQuestion, Model model) {
		model.addAttribute("voteQuestion", voteQuestion);
		return "modules/vote/voteQuestionForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("vote:voteQuestion:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated VoteQuestion voteQuestion) {
		voteQuestionService.save(voteQuestion);
		return renderResult(Global.TRUE, text("保存问题成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("vote:voteQuestion:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(VoteQuestion voteQuestion) {
		voteQuestionService.delete(voteQuestion);
		return renderResult(Global.TRUE, text("删除问题成功！"));
	}
	
}