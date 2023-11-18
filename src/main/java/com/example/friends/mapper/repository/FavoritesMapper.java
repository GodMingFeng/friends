package com.example.friends.mapper.repository;

import com.example.friends.mapper.domain.Favorites;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FavoritesMapper extends Mapper<Favorites>, MySqlMapper<Favorites> {
}
