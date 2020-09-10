/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.web;

import com.jeesite.common.web.BaseController;
import com.jeesite.modules.review.service.VoteFormalService;
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