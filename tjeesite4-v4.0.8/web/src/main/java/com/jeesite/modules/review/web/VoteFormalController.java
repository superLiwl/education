/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.web;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.review.entity.*;
import com.jeesite.modules.review.service.ReviewTermAnswerService;
import com.jeesite.modules.review.service.VoteFormalService;
import com.jeesite.modules.sys.entity.DictData;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.DictUtils;
import com.jeesite.modules.sys.utils.EmpUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
//        return "modules/voteformal/gongshi01";
        return "modules/voteformal/gongshiblue";
    }

    /**
     * 投票页面--红色
     */
    @RequestMapping(value = {"toupiao", ""})
    public String toupiao() {
        return "modules/voteformal/toupiao";
    }

    /**
     * 投票页面--蓝色
     */
    @RequestMapping(value = {"toupiaoblue", ""})
    public String toupiaoblue() {
        return "modules/voteformal/toupiaoblue";
    }

    /**
     * 干部
     */
    @RequestMapping(value = {"ganbu", ""})
    public String ganbu() {
        return "modules/voteformal/xly001blue";
    }

    /**
     * 处长
     */
    @RequestMapping(value = {"chuzhang", ""})
    public String chuzhang() {
        return "modules/voteformal/xly002blue";
    }

    /**
     * 处室
     */
    @RequestMapping(value = {"chushi", ""})
    public String chushi() {
        return "modules/voteformal/xly003blue";
    }

    /**
     * 获取详情信息
     */
    @RequestMapping(value = "getDetailInfo")
    @ResponseBody
    public Map<String, Object> getDetailInfo(String id) {
        return voteFormalService.getDetailInfo(id);
    }


    /**
     * 已投票结果
     */
    @RequestMapping(value = {"toupiaocart", ""})
    public String toupiaocart() {
        return "modules/voteformal/toupiaocart";
    }

    /**
     * 已投票结果--blue
     */
    @RequestMapping(value = {"toupiaocartblue", ""})
    public String toupiaocartblue() {
        return "modules/voteformal/cxjg";
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
    public List<Map<String, Object>> getReviewTermListByOfficeCode(String officeCode, String termType) {
        return voteFormalService.getReviewTermListByOfficeCode(officeCode, termType);
    }

    /**
     * 获取投票数据
     */
    @RequestMapping(value = "getAnswerInfo")
    @ResponseBody
    public List<Map<String, Object>> getAnswerInfo(String termType) {
        return voteFormalService.getAnswerInfo(termType);
    }

    /**
     * 确定投票
     */
    @RequestMapping(value = "submitAnswer")
    @ResponseBody
    public String submitAnswer(String optionIds, String termType, String voteStatus, String sfzNo) {
        return voteFormalService.submitAnswer(optionIds, termType, voteStatus, sfzNo);
    }

    /**
     * 获取所有投票信息
     */
    @RequestMapping(value = "getAllAnswer")
    @ResponseBody
    public List<ReviewTermAnswer> getAllAnswer(String termType) {
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = login.getId();
        ReviewTermAnswer select = new ReviewTermAnswer();
        select.setUserId(userId);
        select.setReviewName(termType);
        return reviewTermAnswerService.findList(select);
    }

    /**
     * 查询我是否投过票
     */
    @RequestMapping(value = "isTouPiaoByUser")
    @ResponseBody
    public Long isTouPiaoByUser(String termType) {
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = login.getId();
        ReviewTermAnswer select = new ReviewTermAnswer();
        select.setUserId(userId);
        select.setReviewName(termType);
        select.setVoteStatus("1");//已投票状态
        return reviewTermAnswerService.findCount(select);
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        return UserUtils.getUser();
    }

    /**
     * 获取用户组织信息
     */
    @RequestMapping(value = "getOfficeInfo")
    @ResponseBody
    public Office getOfficeInfo() {
        return EmpUtils.getOffice();
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "getDicInfo")
    @ResponseBody
    public List<DictData> getDicInfo() {
        return DictUtils.getDictList("term_option");
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "searchList")
    @ResponseBody
    public List<Map<String, Object>> searchList(String officeCode, String reviewName, String optionName) {
        return voteFormalService.searchList(officeCode, reviewName, optionName);
    }

    /**
     * 获取已投票结果
     */
    @RequestMapping(value = "getHasChecked")
    @ResponseBody
    public List<Map<String, Object>> getHasChecked(String optionIds) {
        return voteFormalService.getHasChecked(optionIds);
    }

    /**
     * 导出获取已投票结果
     */
    @RequestMapping(value = "exportGetHasChecked")
    @ResponseBody
    public void exportGetHasChecked(String optionIds, String exportType, HttpServletResponse response) {
        List<Map<String, Object>> list = voteFormalService.getHasChecked(optionIds);
        ExcelExport ee;
        List elist;
        if (!StringUtils.isEmpty(exportType) && "3".equals(exportType)) {
            List<HasCheckedExportCsVo> exportList = new ArrayList<>();
            HasCheckedExportCsVo vo;
            for (Map<String, Object> m : list) {
                vo = new HasCheckedExportCsVo();
                vo.setOfficeName(String.valueOf(m.get("office_name")));
                vo.setOptionName(String.valueOf(m.get("option_name")));
                exportList.add(vo);
            }
            elist = ListUtils.newArrayList(exportList);
            ee = new ExcelExport("预选结果", HasCheckedExportCsVo.class);
        } else {
            List<HasCheckedExportVo> exportList = new ArrayList<>();
            HasCheckedExportVo vo;
            for (Map<String, Object> m : list) {
                vo = new HasCheckedExportVo();
                vo.setOfficeName(String.valueOf(m.get("office_name")));
                vo.setOptionName(String.valueOf(m.get("option_name")));
                exportList.add(vo);
            }
            elist = ListUtils.newArrayList(exportList);
            ee = new ExcelExport("预选结果", HasCheckedExportVo.class);
        }
        String fileName = "预选结果" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        Throwable localThrowable3 = null;
        try {
            ee.setDataList(elist).write(response, fileName);
        } catch (Throwable localThrowable1) {
            localThrowable3 = localThrowable1;
            throw localThrowable1;
        } finally {
            if (ee != null) if (localThrowable3 != null) try {
                ee.close();
            } catch (Throwable localThrowable2) {
                localThrowable3.addSuppressed(localThrowable2);
            }
            else ee.close();
        }
    }

    /**
     * 导出获取已投票结果
     */
    @RequestMapping(value = "exportDataByType")
    @ResponseBody
    public void exportDataByType(String exportDataType, HttpServletResponse response) {
        List<Map<String, Object>> list = voteFormalService.exportDataByType(exportDataType);
        ExcelExport ee;
        List elist;
        String title = "干部";
        if (!StringUtils.isEmpty(exportDataType) && "3".equals(exportDataType)) {
            List<TpDataExportCsVo> exportList = new ArrayList<>();
            TpDataExportCsVo vo;
            for (Map<String, Object> m : list) {
                vo = new TpDataExportCsVo();
                vo.setOfficeName(String.valueOf(m.get("office_name")));
                vo.setOptionName(String.valueOf(m.get("option_name")));
                vo.setPosition(String.valueOf(m.get("position")));
                exportList.add(vo);
            }
            elist = ListUtils.newArrayList(exportList);
            title = "处室";
            ee = new ExcelExport(title+"投票选项", TpDataExportCsVo.class);
        } else {
            List<TpDataExportVo> exportList = new ArrayList<>();
            TpDataExportVo vo;
            for (Map<String, Object> m : list) {
                vo = new TpDataExportVo();
                vo.setOfficeName(String.valueOf(m.get("office_name")));
                vo.setOptionName(String.valueOf(m.get("option_name")));
                vo.setPosition(String.valueOf(m.get("position")));
                exportList.add(vo);
            }
            elist = ListUtils.newArrayList(exportList);
            if(!StringUtils.isEmpty(exportDataType) && "2".equals(exportDataType)){
                title = "处长";
            }
            ee = new ExcelExport(title+"投票选项", TpDataExportVo.class);

        }

        String fileName = title +"投票选项" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        Throwable localThrowable3 = null;
        try {
            ee.setDataList(elist).write(response, fileName);
        } catch (Throwable localThrowable1) {
            localThrowable3 = localThrowable1;
            throw localThrowable1;
        } finally {
            if (ee != null) if (localThrowable3 != null) try {
                ee.close();
            } catch (Throwable localThrowable2) {
                localThrowable3.addSuppressed(localThrowable2);
            }
            else ee.close();
        }
    }

}