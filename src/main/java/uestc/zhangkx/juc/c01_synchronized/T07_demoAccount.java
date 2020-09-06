package uestc.zhangkx.juc.c01_synchronized;

import java.util.concurrent.TimeUnit;

/**
 * �����⣺ģ�������˻�
 * ��ҵ��д��������
 * ��ҵ�������������
 * �����в��У�
 * <p>
 * ���ײ���������⣨dirtyRead��
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

    public synchronized double getBalance(String name) {//�����ϵ�sync�����Ķ���
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
