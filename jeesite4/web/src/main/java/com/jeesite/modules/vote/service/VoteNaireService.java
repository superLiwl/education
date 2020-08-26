/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.entity.VoteNaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷Service
 *
 * @author liwl
 * @version 2020-08-26
 */
@Service
@Transactional(readOnly = true)
public class VoteNaireService extends CrudService<VoteNaireDao, VoteNaire> {
    @Autowired
    private VoteNaireDao voteNaireDao;

    /**
     * 获取单条数据
     *
     * @param voteNaire
     * @return
     */
    @Override
    public VoteNaire get(VoteNaire voteNaire) {
        return super.get(voteNaire);
    }

    /**
     * 查询分页数据
     *
     * @param voteNaire 查询条件
     * @return
     */
    @Override
    public Page<VoteNaire> findPage(VoteNaire voteNaire) {
        return super.findPage(voteNaire);
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param voteNaire
     */
    @Override
    @Transactional(readOnly = false)
    public void save(VoteNaire voteNaire) {
        super.save(voteNaire);
    }

    /**
     * 更新状态
     *
     * @param voteNaire
     */
    @Override
    @Transactional(readOnly = false)
    public void updateStatus(VoteNaire voteNaire) {
        super.updateStatus(voteNaire);
    }

    /**
     * 删除数据
     *
     * @param voteNaire
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(VoteNaire voteNaire) {
        super.delete(voteNaire);
    }


    /**
     * 更改问卷状态
     */
    @Transactional(readOnly = false)
    public void updateStatues(String type, String ids) {
        String idArry[] = ids.split(",");
        List<String> idList = new ArrayList<>();
        for (String id : idArry) {
            idList.add(id);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("ids", idList);
        params.put("type", type);
		voteNaireDao.updateStatues(params);
    }
}