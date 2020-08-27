/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.shiro.realm.LoginInfo;
import org.apache.shiro.SecurityUtils;
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
import com.jeesite.modules.vote.entity.VoteNaire;
import com.jeesite.modules.vote.service.VoteNaireService;

import java.util.Date;

/**
 * 问卷Controller
 *
 * @author liwl
 * @version 2020-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/voteNaire")
public class VoteNaireController extends BaseController {

    @Autowired
    private VoteNaireService voteNaireService;

    /**
     * 获取数据
     */
    @ModelAttribute
    public VoteNaire get(String id, boolean isNewRecord) {
        return voteNaireService.get(id, isNewRecord);
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("vote:voteNaire:view")
    @RequestMapping(value = {"list", ""})
    public String list(VoteNaire voteNaire, Model model) {
        model.addAttribute("voteNaire", voteNaire);
        return "modules/vote/voteNaireList";
    }
    /**
     * 查询列表
     */
    @RequiresPermissions("vote:voteNaire:view")
    @RequestMapping(value = {"listPt", ""})
    public String listPt(VoteNaire voteNaire, Model model) {
        model.addAttribute("voteNaire", voteNaire);
        return "modules/vote/voteNaireListPt";
    }

    /**
     * 查询列表数据
     */
    @RequiresPermissions("vote:voteNaire:view")
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<VoteNaire> listData(VoteNaire voteNaire, HttpServletRequest request, HttpServletResponse response) {
        voteNaire.setPage(new Page<>(request, response));
        Page<VoteNaire> page = voteNaireService.findPage(voteNaire);
        return page;
    }

    /**
     * 查看编辑表单
     */
    @RequiresPermissions("vote:voteNaire:view")
    @RequestMapping(value = "form")
    public String form(VoteNaire voteNaire, Model model) {
        model.addAttribute("voteNaire", voteNaire);
        return "modules/vote/voteNaireForm";
    }

    /**
     * 查看投票信息
     */
    @RequiresPermissions("vote:voteNaire:view")
    @RequestMapping(value = {"result", ""})
    public String result(VoteNaire voteNaire, Model model) {
        model.addAttribute("voteNaire", voteNaire);
        return "modules/vote/voteNaireResult";
    }

    /**
     * 保存数据
     */
    @RequiresPermissions("vote:voteNaire:edit")
    @PostMapping(value = "save")
    @ResponseBody
    public String save(@Validated VoteNaire voteNaire) {
        voteNaire.setCreateTime(new Date());
        voteNaireService.save(voteNaire);
        return renderResult(Global.TRUE, text("保存问卷成功！"));
    }

    /**
     * 删除数据
     */
    @RequiresPermissions("vote:voteNaire:edit")
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(VoteNaire voteNaire) {
        voteNaireService.delete(voteNaire);
        return renderResult(Global.TRUE, text("删除问卷成功！"));
    }

    /**
     * 更改问卷状态
     */
    @RequiresPermissions("vote:voteNaire:edit")
    @RequestMapping(value = "updateStatues")
    @ResponseBody
    public String updateStatues(String type, String ids) {
        String msg = "";
        if ("2".equals(type)) {
            msg = "发布成功";
        } else if ("3".equals(type)) {
            msg = "终止成功";
        }
        voteNaireService.updateStatues(type, ids);
        return renderResult(Global.TRUE, text(msg));
    }

    /**
     * 去投票
     */
    @RequiresPermissions("vote:voteNaire:edit")
    @RequestMapping(value = "toVoteByUser")
    @ResponseBody
    public String toVoteByUser(String ids, String naireId) {
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String msg = voteNaireService.toVoteByUser(naireId, login.getId(), ids);
        return renderResult(Global.TRUE, text(msg));
    }


}