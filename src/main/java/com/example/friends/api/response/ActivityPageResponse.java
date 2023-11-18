package com.example.friends.api.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动详情
 */
@Data
public class ActivityPageResponse implements Serializable {

    private static final long serialVersionUID = 6303174134936748337L;

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动图片
     */
    private String images;

    /**
     * 营业时间
     */
    private String openHours;

    /**
     * 活动说明
     */
    private String comments;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 省
     */
    private String province;

    /**
     * 省id
     */
    private Integer provinceId;

    /**
     * 市
     */
    private String city;

    /**
     * 市id
     */
    private Integer cityId;

    /**
     * 区
     */
    private String district;

    /**
     * 区id
     */
    private Integer districtId;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 申请id
     */
    private Long applyId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 商户名称
     */
    private String storeName;

    /**
     * 商户信息
     */
    private JSONObject storeInfoObj;
}
