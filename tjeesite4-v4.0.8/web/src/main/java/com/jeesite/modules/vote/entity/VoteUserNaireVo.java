/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.entity;

import java.util.List;


public class VoteUserNaireVo {

    private List<String> userCodes;        // 用户编码
    private List<String> naireIds;        // 问卷id
    private String proportion;        // 投票比例


    public List<String> getUserCodes() {
        return userCodes;
    }

    public void setUserCodes(List<String> userCodes) {
        this.userCodes = userCodes;
    }

    public List<String> getNaireIds() {
        return naireIds;
    }

    public void setNaireIds(List<String> naireIds) {
        this.naireIds = naireIds;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}