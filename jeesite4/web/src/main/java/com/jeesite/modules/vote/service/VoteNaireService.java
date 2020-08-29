/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.vote.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.vote.dao.VoteAnswerDao;
import com.jeesite.modules.vote.dao.VoteNaireDao;
import com.jeesite.modules.vote.dao.VoteUserNaireDao;
import com.jeesite.modules.vote.entity.VoteAnswer;
import com.jeesite.modules.vote.entity.VoteNaire;
import com.jeesite.modules.vote.entity.VoteUserNaire;
import com.jeesite.modules.vote.entity.VoteUserNaireVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    private VoteAnswerDao voteAnswerDao;
    @Autowired
    private VoteUserNaireDao voteUserNaireDao;

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

    /**
     * 更改问卷状态
     */
    @Transactional(readOnly = false)
    public String toVoteByUser(String naireId, String userId, String ids) {
        //判断当前问卷是否支持投票
        VoteNaire naire = new VoteNaire();
        naire.setId(naireId);
        naire.setDelFlag("0");
        naire =  voteNaireDao.get(naire);
        if(null == naire){
            return "已失效，暂不能投票";
        }
        if("1".equals(naire.getStatues())){
            return "未发布，暂不能投票";
        }
        if("3".equals(naire.getStatues())){
            return "已终止，暂不能投票";
        }

        String idArry[] = ids.split(",");
        List<String> noCheckIds = new ArrayList<>();
        for (String id : idArry) {
            noCheckIds.add(id);
        }
        //判断是否投过票
        VoteAnswer select = new VoteAnswer();
        select.setNaireId(naireId);
        select.setUserId(userId);
        Long count = voteAnswerDao.findCount(select);
        if(count.intValue() > 0){
            return "已经投过票了";
        }
        //校验是否有投票选项
        Map<String, Object> params = new HashMap<>();
        params.put("naireId",naireId);
        params.put("ids",noCheckIds);
        List<String> checkIds = voteNaireDao.checkQuesIds(params);
        if(null == checkIds || checkIds.isEmpty() || checkIds.size() == 0){
            return "请选择投票选项";
        }
        //保存投票信息
        List<VoteAnswer> ansList = new ArrayList<>();
        VoteAnswer answer;
        for(String id : checkIds){
            answer = new VoteAnswer();
            answer.setId(UUID.randomUUID().toString());
            answer.setUserId(userId);
            answer.setNaireId(naireId);
            answer.setQuestionId(id);
            ansList.add(answer);
        }
        voteAnswerDao.insertBatch(ansList);
        return "投票成功";
    }


    /**
     * 更改问卷状态
     */
    @Transactional(readOnly = false)
    public String saveNaireUser(VoteUserNaireVo vo) {
        if(null == vo.getNaireIds() || vo.getNaireIds().isEmpty() || vo.getNaireIds().size() == 0){
            return  "问卷id为空";
        }
        if(null == vo.getUserCodes() || vo.getUserCodes().isEmpty() || vo.getUserCodes().size() == 0){
            return  "问卷id为空";
        }
        //循环问卷
        VoteUserNaire userNaire;
        VoteUserNaire userNaireDel;
        List<VoteUserNaire> insertEntityList= new ArrayList<>();
        for (String naireId : vo.getNaireIds()){
            for (String userCode : vo.getUserCodes()){
                userNaire = new VoteUserNaire();
                userNaire.setId(UUID.randomUUID().toString());
                userNaire.setNaireId(naireId);
                userNaire.setUserCode(userCode);
                userNaire.setProportion(vo.getProportion());
                insertEntityList.add(userNaire);
            }
            //情况问卷已配置用户
            userNaireDel = new VoteUserNaire();
            userNaireDel.setNaireId(naireId);
            voteUserNaireDao.deleteByEntity(userNaireDel);
        }
        voteUserNaireDao.insertBatch(insertEntityList);
        return "配置成功";
    }
}