package com.example.friends.api.controller;

import com.example.friends.api.convert.ActivityConverter;
import com.example.friends.api.request.FavoritesActivitiesRequest;
import com.example.friends.api.request.FavoritesPageRequest;
import com.example.friends.api.response.ActivityPageResponse;
import com.example.friends.common.Page;
import com.example.friends.common.Response;
import com.example.friends.common.enums.FavoritesStatus;
import com.example.friends.common.enums.FavoritesType;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.common.utils.UserInfoHolder;
import com.example.friends.core.service.activity.ActivityService;
import com.example.friends.core.service.favorites.FavoritesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/fake_jy")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    @Resource
    private ActivityService activityService;

    @ResponseBody
    @RequestMapping(path = "/markActivity", method = RequestMethod.POST)
    public Response<Boolean> markActivity(@RequestBody FavoritesActivitiesRequest favoritesActivitiesRequest) {
        var openId = UserInfoHolder.getOpenId();
        favoritesService.editFavorites(FavoritesType.ACTIVITIES,
                openId, String.valueOf(favoritesActivitiesRequest.getId()), FavoritesStatus.get(favoritesActivitiesRequest.getStatus()));
        return Response.<Boolean>success().result(true);
    }

    @ResponseBody
    @RequestMapping(path = "/getMarkList", method = RequestMethod.POST)
    public Response<Page<ActivityPageResponse>> getMarkList(@RequestBody FavoritesPageRequest activityPageRequest) {
        var openId = UserInfoHolder.getOpenId();
        var result = activityService.favoritesActivities(openId, activityPageRequest.getPage(), activityPageRequest.getPageSize());
        return Response.<Page<ActivityPageResponse>>success().result(BeanCopiers.copyPage(result, ActivityConverter::convertPage));
    }
}
