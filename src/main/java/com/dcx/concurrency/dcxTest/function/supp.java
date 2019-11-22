package com.dcx.concurrency.dcxTest.function;

import java.util.function.Supplier;

/**
 * Created by 党楚翔 on 2019/5/21.
 * https://blog.csdn.net/z834410038/article/details/77370785
**/
public class supp {

    public static void main(String[]args){
        String name = "党楚翔";
        // () -> name.length() 无参数，返回一个结果（字符串长度）
        // 所以该lambda表达式可以实现Supplier接口
        System.out.println(supplierTest(() -> name));
    }

    public static String supplierTest(Supplier<String> supplier) {
        return supplier.get();
    }
}
