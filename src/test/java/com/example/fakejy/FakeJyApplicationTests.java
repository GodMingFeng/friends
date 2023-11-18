package com.example.friends;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.friends.common.enums.ActivityType;
import com.example.friends.common.enums.FavoritesType;
import com.example.friends.core.service.activity.RankService;
import com.example.friends.core.service.favorites.FavoritesService;
import com.example.friends.mapper.condition.ActivityCondition;
import com.example.friends.mapper.repository.ActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = friendsApplication.class)
public class friendsApplicationTests {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RankService rankService;

    @Resource
    private FavoritesService favoritesService;

    @Test
    public void contextLoads() {
        var condition = new ActivityCondition();
        condition.setKeywords("圣诞季");
        condition.setType(ActivityType.NEWS.getType());
        var a = activityMapper.selectByCondition(condition);
        System.out.println(JSONObject.toJSONString(a));
    }

    @Test
    public void getRank() {
        rankService.activityClick(1L);
        var result = rankService.getTopActivities();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testFavorite() {
        var result = favoritesService.getFavoritesItems("1", FavoritesType.ACTIVITIES, 1, 10);
        System.out.println(JSONObject.toJSONString(result));
    }
}
