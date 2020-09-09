/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.modules.vote.dao.VoteAnswerDao;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.dao.VoteNaireQuestionDao;
import com.jeesite.modules.vote.dao.VoteUserNaireDao;
import com.jeesite.modules.vote.entity.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 问卷Service
 *
 * @author liwl
 * @version 2020-08-26
 */
@Service
@Transactional(readOnly = true)
public class VoteFormalService extends CrudService<VoteNaireDao, VoteNaire> {
    @Autowired
    private VoteNaireDao voteNaireDao;
    /**
     * 查询组织机构
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> getOfficeList() {
        return voteNaireDao.getOfficeList();
    }

}