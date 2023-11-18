package com.example.friends.mapper.condition;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityCondition implements Serializable {

    private static final long serialVersionUID = 5290985980774731964L;

    /**
     * 关键词搜索
     */
    private String keywords;

    /**
     * 类型
     */
    private Integer type;
}
