package uestc.zhangkx.juc.c01_synchronized;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 * <p>
 * 容易产生脏读问题（dirtyRead）
 */
public class T07_demoAccount {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(String name) {//方法上的sync是锁的对象
        return this.balance;
    }

    public static void main(String[] args) {
        T07_demoAccount account = new T07_demoAccount();
        new Thread(() -> account.set("zhangsan", 100.00)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));
    }
}
