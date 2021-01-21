package com.rsx.juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,8,1000L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(10000));
//        executor.execute(()->{
//            System.out.println("task is running ...");
//        });
        System.out.println(Integer.toBinaryString(-1 << 29));
    }
}
