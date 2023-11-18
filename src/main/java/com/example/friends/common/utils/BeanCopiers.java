package com.example.friends.common.utils;

import com.example.friends.common.Page;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanCopiers {

    public static <T> T copy(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @SneakyThrows
    public static <T> T copy(Object source, Class<T> clazz) {
        return copy(source, clazz.getDeclaredConstructor().newInstance());
    }

    public static <T, V> List<T> copyList(List<V> source, Class<T> clazz) {
        return source.stream().map(e -> copy(e, clazz)).collect(Collectors.toList());
    }

    public static <T, V> List<T> copyList(List<V> source, Function<V, T> mapper) {
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> Page<T> copyPage(Page<?> source, Class<T> clazz) {
        var result = new Page<T>();
        result.setTotal(source.getTotal());
        result.setList(copyList(source.getList(), clazz));
        return result;
    }

    public static <T, V> Page<T> copyPage(Page<V> source, Function<V, T> mapper) {
        var result = new Page<T>();
        result.setTotal(source.getTotal());
        result.setList(copyList(source.getList(), mapper));
        return result;
    }
}
