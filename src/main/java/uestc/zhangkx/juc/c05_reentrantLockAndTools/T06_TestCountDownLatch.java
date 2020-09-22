package uestc.zhangkx.juc.c05_reentrantLockAndTools;

import java.util.concurrent.CountDownLatch;

/**
 * countdownlatch就是倒计时器
 *
 * @author zhangkx
 */
public class T06_TestCountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
                latch.countDown();
            });
        }

        /*
        这里的for，导致每一个threads也是有先后顺序的，并不是完美的并发
         */
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();// 主线程在阻塞，当计数器==0，就唤醒主线程往下执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}
