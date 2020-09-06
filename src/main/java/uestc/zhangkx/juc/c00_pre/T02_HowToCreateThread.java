package uestc.zhangkx.juc.c00_pre;

/**
 * 如何创建线程
 *  - 继承Thread
 *  - 实现Runnable
 * @author zhangkx
 */
public class T02_HowToCreateThread {
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("hello! MyThread extends Thread!");
        }
    }
    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println("hello! MyRun implements Runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).run();
        new Thread(()-> System.out.println("hello! im lambda!")).start();
    }

}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThread
