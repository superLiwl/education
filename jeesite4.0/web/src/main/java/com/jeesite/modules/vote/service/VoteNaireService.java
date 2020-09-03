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
public class VoteNaireService extends CrudService<VoteNaireDao, VoteNaire> {
    @Autowired
    private VoteNaireDao voteNaireDao;
    @Autowired
    private VoteAnswerDao voteAnswerDao;
    @Autowired
    private VoteUserNaireDao voteUserNaireDao;
    @Autowired
    private VoteNaireQuestionDao voteNaireQuestionDao;

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
     * 查询分页数据-pt
     *
     * @param voteNaire 查询条件
     * @return
     */
    public Page<VoteNaire> findPagePt(VoteNaire voteNaire) {
        int pageNo = voteNaire.getPage().getPageNo();
        int pageSize = voteNaire.getPage().getPageSize();
        LoginInfo login = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
        String userCode = login.getId();
        Map<String, Object> params = new HashMap<>();
        params.put("userCode", userCode);
        params.put("start", (pageNo - 1) * pageSize);
        params.put("end", pageNo * pageSize);
        params.put("name", voteNaire.getName());
        Page<VoteNaire> result = new Page<VoteNaire>();
        result.setList(voteNaireDao.getListByPages(params));
        return result;
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
        naire = voteNaireDao.get(naire);
        if (null == naire) {
            return "已失效，暂不能投票";
        }
        if ("1".equals(naire.getStatues())) {
            return "未发布，暂不能投票";
        }
        if ("3".equals(naire.getStatues())) {
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
        if (count.intValue() > 0) {
            return "已经投过票了";
        }
        //校验是否有投票选项
        Map<String, Object> params = new HashMap<>();
        params.put("naireId", naireId);
        params.put("ids", noCheckIds);
        List<String> checkIds = voteNaireDao.checkQuesIds(params);
        if (null == checkIds || checkIds.isEmpty() || checkIds.size() == 0) {
            return "请选择投票选项";
        }
        //保存投票信息
        List<VoteAnswer> ansList = new ArrayList<>();
        VoteAnswer answer;
        for (String id : checkIds) {
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
        if (null == vo.getNaireIds() || vo.getNaireIds().isEmpty() || vo.getNaireIds().size() == 0) {
            return "问卷id为空";
        }
        if (null == vo.getUserCodes() || vo.getUserCodes().isEmpty() || vo.getUserCodes().size() == 0) {
            return "问卷id为空";
        }
        //循环问卷
        VoteUserNaire userNaire;
        VoteUserNaire userNaireDel;
        List<VoteUserNaire> insertEntityList = new ArrayList<>();
        for (String naireId : vo.getNaireIds()) {
            for (String userCode : vo.getUserCodes()) {
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

    /**
     * 更改问卷状态
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> seeResult(String naireId) {
        if (null == naireId || "".equals(naireId)) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("naireId", naireId);
        List<Map<String, Object>> list = voteNaireDao.seeResult(params);
        Map<String, Boolean> pMap = new HashMap<>();//用来判断是已经有父id
        Map<String, Object> pTitleMap = new HashMap<>();//用来存父id的title
        Map<String, List<Map<String, Object>>> pListMap = new HashMap<>();//父id的子集合
        //重新构造一下数据
        if (null != list && !list.isEmpty() && list.size() > 0) {
            Map<String, Object> map;
            String pId;
            List<Map<String, Object>> subList;
            for (Map<String, Object> r : list) {
                map = new HashMap<>();
                pId = String.valueOf(r.get("parent_id"));
                //构造map
                map.put("subTitle", r.get("title"));
                map.put("subCount", r.get("count"));
                map.put("pTitle", r.get("pTitle"));
                map.put("allCount", r.get("allCount"));
                map.put("rate", roundRatio(String.valueOf(r.get("count")), String.valueOf(r.get("allCount"))));
                //计算占比
                map.put("pId", pId);
                //判断是否有pid的节点-有就增加到list中，没有则新加入
                if (null != pMap.get(pId) && pMap.get(pId)) {
                    subList = pListMap.get(pId);
                    subList.add(map);
                } else {
                    pMap.put(pId, true);
                    pTitleMap.put(pId, r.get("pTitle"));
                    subList = new ArrayList<>();
                    subList.add(map);
                }
                //放入到集合中
                pListMap.put(pId, subList);
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map;
        for (String key : pMap.keySet()) {
            map = new HashMap<>();
            map.put("pid", key);
            map.put("pTitle", pTitleMap.get(key));
            map.put("subList", pListMap.get(key));
            result.add(map);
        }
        return result;
    }

    @Transactional(readOnly = false)
    public long findAllCount(String naireId) {
        VoteUserNaire v = new VoteUserNaire();
        v.setNaireId(naireId);
        return voteUserNaireDao.findCount(v);
    }

    @Transactional(readOnly = false)
    public long findHasCount(String naireId) {
        Map<String, Object> params = new HashMap<>();
        params.put("naireId", naireId);
        return voteUserNaireDao.getHasCount(params);
    }

    /**
     * 更改问卷状态
     */
    @Transactional(readOnly = false)
    public String saveQuestions(VoteUserNaireVo vo) {
        if (null == vo.getNaireIds() || vo.getNaireIds().isEmpty() || vo.getNaireIds().size() == 0) {
            return "问卷id为空";
        }
        if (null == vo.getUserCodes() || vo.getUserCodes().isEmpty() || vo.getUserCodes().size() == 0) {
            return "选项id为空";
        }
        //循环问卷
        VoteNaireQuestion voteNaireQuestion ;
        VoteNaireQuestion del ;
        List<VoteNaireQuestion> insertEntityList = new ArrayList<>();
        for (String naireId : vo.getNaireIds()) {
            del = new VoteNaireQuestion();
            del.setNaireId(naireId);
            voteNaireQuestionDao.deleteByEntity(del);
            for (String userCode : vo.getUserCodes()) {
                voteNaireQuestion = new VoteNaireQuestion();
                voteNaireQuestion.setNaireId(naireId);
                voteNaireQuestion.setQuestionId(userCode);
                insertEntityList.add(voteNaireQuestion);
            }
        }
        voteNaireQuestionDao.insertBatch(insertEntityList);
        return "配置成功";
    }


    /**
     * 计算百分比，精确到小数点两位
     */
    public static double roundRatio(String v1, String v2) {
        if ("0".equals(v2)) {
            return 0.0;
        }
        double result = div(v1, v2, 4);
        BigDecimal b1 = new BigDecimal(Double.toString(result));
        return b1.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("没有指精确位数");
        }
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

}