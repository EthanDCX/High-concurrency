package com.dcx.concurrency.dcxTest;

public class Main {

    public static <E> void out(E... args) {
        for (E t : args) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        ThreadUtil threadUtil = new ThreadUtil();
        out("findingsea", 123, 11.11, true,threadUtil);
    }
}