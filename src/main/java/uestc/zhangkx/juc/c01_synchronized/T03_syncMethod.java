package uestc.zhangkx.juc.c01_synchronized;
/**
 * synchronized关键字
 * 1.sync在修饰方法等价于lockThis
 * 2.sync方法和其他方法是同时发生的
 * 3.sync可以访问其他非sync的方法
 * @author zhangkx
 */
public class T03_syncMethod{

	private int count = 10;

	public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public void n() { //访问这个方法的时候不需要上锁
		count++;
	}
}
