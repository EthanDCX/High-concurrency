//package com.mmall.concurrency.dcxTest.function;
//
//public class TestHelloService {
//
//    private String username;
//
//    /**
//     * 定义构造函数
//     */
//    public TestHelloService(String username) {
//        this.name = username;
//        System.out.println(username + ":");
//    }
//    /**
//     * 定义函数接口的使用方法
//     */
//    void say(HelloService hs) {
//        hs.sayHello(this.name);
//    }
//
//    /**
//     * 静态函数
//     */
//    static void print(String username) {
//        System.out.println("Hello, " + username + "!");
//    }
//
//    void println(String username) {
//        //  这句话也会执行
//        this.name = "yiifee";
//        System.out.println("Hello, " + username + "!");
//    }
//}