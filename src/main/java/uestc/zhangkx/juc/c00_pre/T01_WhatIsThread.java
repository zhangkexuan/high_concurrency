package uestc.zhangkx.juc.c00_pre;

import java.util.concurrent.TimeUnit;

/**
 * 什么是线程————区分start和run
 * @author zhangkx
 */
public class T01_WhatIsThread {

    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);//更加安全的使用Thread.sleep
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();//这里使用start就是启动线程
//        new T1().run();//如果使用run就只是简单的调用方法
        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}