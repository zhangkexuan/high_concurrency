package uestc.zhangkx.juc.c05_reentrantLockAndTools;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 是重入的细化 分读和写
 * 锁降级：已有写锁可以重入读锁，但已有读锁再重入写锁会死锁
 * > 锁降级中的读锁获取时有必要的，这样保证了数据的线程可见性，
 * > 如当前线程释放了写锁，但是另外一个线程T马上获取了这个写锁，
 * > 并修改了数据，那么当前线程对这个数据的修改是不可见的，可能
 * > 就造成了数据的混乱不一致。但是如果先获取了读锁，再释放写锁，
 * > 那么线程T是无法获取到写锁，将被阻塞。
 *
 * @author zhangkx
 */
public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read over!");
            //模拟读取操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        lock.lock();
        try {
            Thread.sleep(1000);
            value = v;
            System.out.println("write over!");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        //Runnable readR = ()-> read(lock);
        Runnable readR = () -> read(readLock);

        //Runnable writeR = ()->write(lock, new Random().nextInt());
        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();


    }
}
