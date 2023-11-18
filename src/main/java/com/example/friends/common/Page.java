package com.example.friends.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 2448790488559779158L;

    /**
     * 总数
     */
    private Long total;

    /**
     * 列表
     */
    private List<T> list;


}
