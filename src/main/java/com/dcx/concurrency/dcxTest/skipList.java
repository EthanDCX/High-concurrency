package com.dcx.concurrency.dcxTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 党楚翔 on 2019/9/26.
 */
public class skipList {
    //按每3个一组分割
    private static final Integer MAX_NUMBER = 2;

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int limit = countStep(list.size());
        //方法一：使用流遍历操作
        List<List<Integer>> mglist = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(list.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
        });

//        System.out.println(mglist);


        List<Map<String,Object>> mapList = new ArrayList<>();
        mapList.add((Map<String, Object>) new HashMap<>().put("1", 1));
        mapList.add((Map<String, Object>) new HashMap<>().put("2", 2));
        mapList.add((Map<String, Object>) new HashMap<>().put("3", 3));
        //方法二：获取分割后的集合
        getSpiltList(mapList, limit);
    }

    private static void getSpiltList(List<Map<String,Object
                >> list, int limit) {
        List<List<Map<String,Object>>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(splitList);
    }
}