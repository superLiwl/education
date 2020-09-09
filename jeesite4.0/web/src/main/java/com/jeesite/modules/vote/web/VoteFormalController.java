/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.vote.entity.VoteNaire;
import com.jeesite.modules.vote.entity.VoteUserNaireVo;
import com.jeesite.modules.vote.service.VoteFormalService;
import com.jeesite.modules.vote.service.VoteNaireService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    /**
     * 查询列表
     */
    @RequestMapping(value = {"gongshi", ""})
    public String gongshi() {
        return "modules/voteformal/gongshi";
    }

    /**
     * 查询列表数据
     */
    @RequestMapping(value = "getOfficeList")
    @ResponseBody
    public List<Map<String,Object>> getOfficeList() {
        return voteFormalService.getOfficeList();
    }

}