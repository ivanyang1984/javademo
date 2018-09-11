/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.vphoto.demo.springboot.model.convertlab;

import lombok.Data;

/**
 * @author hongfei.yang@v.photos
 * @version creat time：2018/7/4 上午11:25
 * 根据被推广人openId 来查询对应的推广计划下对应的推广人信息
 */
@Data
public class ReferralModel {

    /**
     *  id
     */
    private String id;

    /**
     * referrer id
     */
    private String referrer;

    /**
     * referPlan
     */
    private String referPlan;

    /**
     * eventId
     */
    private String eventId;

    /**
     * eventName
     */
    private String eventName;

    /**
     * customerId
     */
    private String customerId;

    /**
     * 事件发生时间
     */
    private String date;

    /**
     * pageId
     */
    private String pageId;

    /**
     * dateCreated
     */
    private String targetId;

    /**
     * dateCreated
     */
    private String dateCreated;

    /**
     * openid
     */
    private String openId;

    /**
     * 推广人姓名
     */
    private String referrerName;

    /**
     * 推广人openid
     */
    private String referrerOpenId;

    /**
     * 推广人手机号
     */
    private String referrerMobile;

    /**
     * 被推广人名称
     */
    private String name;

    /**
     * 被推广人手机号
     */
    private String mobile;
}
