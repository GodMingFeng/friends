package com.example.friends.core.service.activity;

import com.example.friends.common.Page;
import com.example.friends.core.service.activity.ao.QueryActivityAO;
import com.example.friends.core.service.activity.bo.ActivityBO;

import java.util.List;

public interface ActivityService {

    /**
     * 通过id查询活动
     *
     * @param id id
     * @return 活动
     */
    ActivityBO queryActivityById(Long id);


    /**
     * 查询活动列表
     *
     * @return 活动列表
     */
    Page<ActivityBO> queryActivities(QueryActivityAO queryActivityAO);

    /**
     * 排行榜
     *
     * @return
     */
    List<ActivityBO> rankActivities();

    /**
     * 收藏的活动
     *
     * @return
     */
    Page<ActivityBO> favoritesActivities(String openId, Integer page, Integer pageSize);

    /**
     * 根据id列表查询活动列表（顺序）
     *
     * @param containsExpire 是否包含过期
     */
    List<ActivityBO> getActivitiesListByIdListOrder(List<Long> idList, Boolean containsExpire);
}
