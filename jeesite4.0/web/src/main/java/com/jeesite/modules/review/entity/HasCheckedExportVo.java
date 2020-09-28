/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import com.jeesite.common.utils.excel.annotation.ExcelFields;

public class HasCheckedExportVo {

    @ExcelFields({@com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评单位", attrName = "officeName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.CENTER, sort = 30),
            @com.jeesite.common.utils.excel.annotation.ExcelField(title = "参评人", attrName = "optionName", align = com.jeesite.common.utils.excel.annotation.ExcelField.Align.LEFT, sort = 50)})
    public HasCheckedExportVo getVoteInfoExportVo(HasCheckedExportVo vo) {
        return vo;
    }


    private static final long serialVersionUID = 1L;

    private String officeName;// 参评单位
    private String optionName;//参评人

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}