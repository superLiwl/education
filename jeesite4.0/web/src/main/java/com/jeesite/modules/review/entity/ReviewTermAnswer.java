/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.review.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import org.hibernate.validator.constraints.Length;

/**
 * 投票结果Entity
 *
 * @author liwl
 * @version 2020-09-11
 */
@Table(name = "review_term_answer", alias = "a", columns = {
        @Column(name = "id", attrName = "id", label = "主键", isPK = true),
        @Column(name = "user_id", attrName = "userId", label = "用户id"),
        @Column(name = "option_id", attrName = "optionId", label = "投票答案"),
        @Column(name = "review_name", attrName = "reviewName", label = "投票项类型"),
        @Column(name = "vote_status", attrName = "voteStatus", label = "投票状态(0-草稿，1-已投票)"),
}, orderBy = "a.id DESC"
)
public class ReviewTermAnswer extends DataEntity<ReviewTermAnswer> {

    private static final long serialVersionUID = 1L;
    private String userId;        // 用户id
    private String optionId;        // 投票答案
    private String reviewName;        // 投票项类型
    private String voteStatus;        // 投票状态(0-草稿，1-已投票)

    public ReviewTermAnswer() {
        this(null);
    }

    public ReviewTermAnswer(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "用户id长度不能超过 64 个字符")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Length(min = 0, max = 64, message = "投票项长度不能超过 64 个字符")
    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    @Length(min = 0, max = 100, message = "投票项类型长度不能超过 100 个字符")
    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    @Length(min = 0, max = 2, message = "投票状态长度不能超过 2 个字符")
    public String getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(String voteStatus) {
        this.voteStatus = voteStatus;
    }
}