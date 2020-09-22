package uestc.zhangkx.juc.c05_reentrantLockAndTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏：barrier.await将线程阻塞，CyclicBarrier(int parties, Runnable barrierAction)，一旦达到parties后就执行barrierAction
 *
 * @author zhangkx
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });*/

        for(int i=0; i<100; i++) {

                new Thread(()->{
                    try {
                        barrier.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            
        }
    }
}
