package com.example.friends.core.service.activity;

import java.util.List;

public interface RankService {

    /**
     * 点击活动
     *
     * @param id
     * @return
     */
    Boolean activityClick(Long id);

    /**
     * 获取top活动
     *
     * @return
     */
    List<Long> getTopActivities();

    /**
     * 获取点击量
     *
     * @param id
     * @return
     */
    Integer getActivityClick(Long id);
}
