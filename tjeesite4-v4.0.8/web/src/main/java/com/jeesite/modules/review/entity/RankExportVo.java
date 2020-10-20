/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import com.jeesite.common.utils.excel.annotation.ExcelFields;

public class RankExportVo {

    @ExcelFields({@com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评单位", attrName = "officeName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 30),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评项", attrName = "reviewName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 40),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评人", attrName = "optionName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.LEFT, sort = 50),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "厅局票数", attrName = "tjCount", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 60),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "县市票数", attrName = "xsCount", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 70),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "总票数", attrName = "answerCount", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 80)})
    public RankExportVo getVoteInfoExportVo(RankExportVo vo) {
        return vo;
    }


    private static final long serialVersionUID = 1L;

    private String officeName;// 参评单位
    private String reviewName;// 参评项
    private String optionName;//参评人
    private String tjCount;//厅局票数
    private String xsCount;//县市票数
    private String answerCount;//总票数

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getTjCount() {
        return tjCount;
    }

    public void setTjCount(String tjCount) {
        this.tjCount = tjCount;
    }

    public String getXsCount() {
        return xsCount;
    }

    public void setXsCount(String xsCount) {
        this.xsCount = xsCount;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }
}