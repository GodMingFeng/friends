package com.example.friends.mapper.repository;

import com.example.friends.mapper.domain.Message;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MessageMapper extends Mapper<Message>, MySqlMapper<Message> {
}
