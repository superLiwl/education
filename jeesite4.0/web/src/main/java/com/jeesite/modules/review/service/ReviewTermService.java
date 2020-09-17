/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.review.dao.ReviewTermDao;
import com.jeesite.modules.review.dao.ReviewTermOptionsDao;
import com.jeesite.modules.review.dao.ReviewTermUserRateDao;
import com.jeesite.modules.review.entity.*;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 参评管理Service
 *
 * @author liwl
 * @version 2020-09-10
 */
@Service
@Transactional(readOnly = true)
public class ReviewTermService extends CrudService<ReviewTermDao, ReviewTerm> {

    @Autowired
    private ReviewTermDao reviewTermDao;
    @Autowired
    private ReviewTermOptionsDao reviewTermOptionsDao;
    @Autowired
    private ReviewTermUserRateDao reviewTermUserRateDao;


    /**
     * 获取单条数据
     *
     * @param reviewTerm
     * @return
     */
    @Override
    public ReviewTerm get(ReviewTerm reviewTerm) {
        ReviewTerm entity = super.get(reviewTerm);
        if (entity != null) {
            ReviewTermOptions reviewTermOptions = new ReviewTermOptions(entity);
            reviewTermOptions.setStatus(ReviewTermOptions.STATUS_NORMAL);
            entity.setReviewTermOptionsList(reviewTermOptionsDao.findList(reviewTermOptions));
        }
        return entity;
    }

    /**
     * 查询分页数据
     *
     * @param page       分页对象
     * @param reviewTerm
     * @return
     */
    @Override
    public Page<ReviewTerm> findPage(Page<ReviewTerm> page, ReviewTerm reviewTerm) {
        return super.findPage(page, reviewTerm);
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param reviewTerm
     */
    @Override
    @Transactional(readOnly = false)
    public void save(ReviewTerm reviewTerm) {
        super.save(reviewTerm);
        // 保存上传图片
        FileUploadUtils.saveFileUpload(reviewTerm.getId(), "reviewTerm_image");
        // 保存 ReviewTerm子表
        for (ReviewTermOptions reviewTermOptions : reviewTerm.getReviewTermOptionsList()) {
            if (!ReviewTermOptions.STATUS_DELETE.equals(reviewTermOptions.getStatus())) {
                reviewTermOptions.setTermId(reviewTerm);
                if (!StringUtils.isEmpty(reviewTermOptions.getOptionName())) {
                    if (reviewTermOptions.getIsNewRecord()) {
                        reviewTermOptions.preInsert();
                        reviewTermOptionsDao.insert(reviewTermOptions);
                    } else {
                        reviewTermOptions.preUpdate();
                        reviewTermOptionsDao.update(reviewTermOptions);
                    }
                }
            } else {
                reviewTermOptionsDao.delete(reviewTermOptions);
            }
        }
    }

    /**
     * 更新状态
     *
     * @param reviewTerm
     */
    @Override
    @Transactional(readOnly = false)
    public void updateStatus(ReviewTerm reviewTerm) {
        super.updateStatus(reviewTerm);
    }

    /**
     * 删除数据
     *
     * @param reviewTerm
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(ReviewTerm reviewTerm) {
        super.delete(reviewTerm);
        ReviewTermOptions reviewTermOptions = new ReviewTermOptions();
        reviewTermOptions.setTermId(reviewTerm);
        reviewTermOptionsDao.delete(reviewTermOptions);
    }

    /**
     * 保存用户权重
     */
    @Transactional(readOnly = false)
    public String saveReviewTermUserRate(UserRateVo userRateVo) {
        if (null == userRateVo.getUserCodes() || userRateVo.getUserCodes().isEmpty() || userRateVo.getUserCodes().size() == 0) {
            return "未选择用户";
        }
        ReviewTermUserRate rate;
        List<ReviewTermUserRate> list = new ArrayList<>();
        User user;
        for (String userCode : userRateVo.getUserCodes()) {
            rate = new ReviewTermUserRate();
            rate.setUserId(userCode);
            //先清除数据
            reviewTermUserRateDao.deleteByEntity(rate);
            user = UserUtils.get(userCode);
            rate.setRate(userRateVo.getRate());
            rate.setUserName(user.getUserName());
            rate.setId(UUID.randomUUID().toString());
            list.add(rate);
        }
        reviewTermUserRateDao.insertBatch(list);
        return "配置成功";
    }

    /**
     * 查询投票结果数据
     */
    @Transactional(readOnly = false)
    public List<Map<String, Object>> listRankData(RankVo rankVo) {
        return reviewTermDao.listRankData(rankVo);
    }

    /**
     * 查询投票结果数据
     */
    @Transactional(readOnly = false)
    public Long listRankDataCount(RankVo rankVo) {
        return reviewTermDao.listRankDataCount(rankVo);
    }

}