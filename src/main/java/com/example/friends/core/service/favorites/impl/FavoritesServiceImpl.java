package com.example.friends.core.service.favorites.impl;

import com.example.friends.common.Page;
import com.example.friends.common.enums.FavoritesStatus;
import com.example.friends.common.enums.FavoritesType;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.common.utils.PageUtils;
import com.example.friends.core.service.favorites.FavoritesService;
import com.example.friends.mapper.domain.Favorites;
import com.example.friends.mapper.repository.FavoritesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Slf4j
@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Resource
    private FavoritesMapper favoritesMapper;

    @Override
    public void editFavorites(FavoritesType type, String openId, String itemId, FavoritesStatus favoritesStatus) {
        var favorites = new Favorites();
        favorites.setItemId(itemId);
        favorites.setOpenId(openId);
        favorites.setType(type.getType());
        favorites.setStatus(favoritesStatus.getStatus());
        var record = getUniqueFavorites(openId, type.getType(), itemId);
        if (record == null) {
            favoritesMapper.insertSelective(favorites);
        } else {
            favorites.setId(record.getId());
            favoritesMapper.updateByPrimaryKeySelective(favorites);
        }
    }

    @Override
    public Page<Long> getFavoritesItems(String openId, FavoritesType type, Integer page, Integer pageSize) {
        var example = new Example(Favorites.class);
        example.createCriteria()
                .andEqualTo("openId", openId)
                .andEqualTo("type", type.getType())
                .andEqualTo("status", FavoritesStatus.ENABLE.getStatus());
        example.setOrderByClause("gmt_modified asc");
        return BeanCopiers.copyPage(PageUtils.page(() ->
                favoritesMapper.selectByExample(example), page, pageSize), fav -> Long.valueOf(fav.getItemId()));
    }

    @Override
    public Boolean isFavorites(FavoritesType type, String openId, String itemId) {
        var example = new Example(Favorites.class);
        example.createCriteria()
                .andEqualTo("openId", openId)
                .andEqualTo("type", type.getType())
                .andEqualTo("itemId", itemId)
                .andEqualTo("status", FavoritesStatus.ENABLE.getStatus());
        return favoritesMapper.selectOneByExample(example) != null;
    }


    /**
     * 获取收藏
     */
    private Favorites getUniqueFavorites(String openId, Integer type, String itemId) {
        var example = new Example(Favorites.class);
        example.createCriteria()
                .andEqualTo("openId", openId)
                .andEqualTo("type", type)
                .andEqualTo("itemId", itemId);
        return favoritesMapper.selectOneByExample(example);
    }
}
