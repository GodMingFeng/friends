package com.example.friends.api.convert;

import com.alibaba.fastjson.JSON;
import com.example.friends.api.response.ActivityDetailResponse;
import com.example.friends.api.response.ActivityPageResponse;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.core.service.activity.bo.ActivityBO;

public class ActivityConverter {

    public static ActivityDetailResponse convertDetail(ActivityBO activityBO) {
        var result = BeanCopiers.copy(activityBO, ActivityDetailResponse.class);
        result.setStoreInfoObj(JSON.parseObject(activityBO.getStoreInfo()));
        return result;
    }

    public static ActivityPageResponse convertPage(ActivityBO activityBO) {
        var result = BeanCopiers.copy(activityBO, ActivityPageResponse.class);
        result.setStoreInfoObj(JSON.parseObject(activityBO.getStoreInfo()));
        return result;
    }
}
