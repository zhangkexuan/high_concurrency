package uestc.zhangkx.juc.c01_synchronized;

/**
 * synchronized关键字
 * sync static 等价于锁class
 * @author zhangkx
 */
public class T04_syncStatic {

	private static int count = 10;

	public synchronized static void m() { //这里等同于synchronized(FineCoarseLock.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void mm() {
		synchronized(T04_syncStatic.class) { //考虑一下这里写synchronized(this)是否可以?__不可以，this只能是not static才能用
			count --;
		}
	}
}
