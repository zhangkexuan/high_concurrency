package uestc.zhangkx.juc.c01_synchronized;

/**
 * synchronized�ؼ���
 * sync static �ȼ�����class
 * @author zhangkx
 */
public class T04_syncStatic {

	private static int count = 10;

	public synchronized static void m() { //�����ͬ��synchronized(FineCoarseLock.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void mm() {
		synchronized(T04_syncStatic.class) { //����һ������дsynchronized(this)�Ƿ����?__�����ԣ�thisֻ����not static������
			count --;
		}
	}
}
