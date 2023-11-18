package com.example.friends.common.utils;

import com.example.friends.common.Page;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PageUtils {

    /**
     * 分页
     *
     * @param select
     * @param page
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> Page<T> page(Supplier<List<T>> select, Integer page, Integer pageSize) {
        var result = new Page<T>();
        var pageInfo = PageHelper.startPage(page, pageSize).<T>doSelectPage(select::get);
        result.setTotal(pageInfo.getTotal());
        result.setList(new ArrayList<>(pageInfo));
        return result;
    }
}
