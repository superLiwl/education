/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import com.jeesite.common.utils.excel.annotation.ExcelFields;

public class VoteInfoExportVo {

    @ExcelFields({@com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评单位", attrName = "officeName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 30),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "投票人", attrName = "userName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 40),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "先进干部", attrName = "ganbu", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.LEFT, sort = 50),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "先进处长", attrName = "chuzhang", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 60),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "先进处室", attrName = "chushi", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 70)})
    public VoteInfoExportVo getVoteInfoExportVo(VoteInfoExportVo vo) {
        return vo;
    }


    private static final long serialVersionUID = 1L;

    private String officeName;// 参评单位
    private String userName;// 投票人
    private String ganbu;//先进干部
    private String chuzhang;//先进处长
    private String chushi;//先进处室

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGanbu() {
        return ganbu;
    }

    public void setGanbu(String ganbu) {
        this.ganbu = ganbu;
    }

    public String getChuzhang() {
        return chuzhang;
    }

    public void setChuzhang(String chuzhang) {
        this.chuzhang = chuzhang;
    }

    public String getChushi() {
        return chushi;
    }

    public void setChushi(String chushi) {
        this.chushi = chushi;
    }
}