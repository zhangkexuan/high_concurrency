package uestc.zhangkx.juc.c01_synchronized;
/**
 * 分析一下这个程序的输出
 * @author zhangkx
 */
public class T05_exWhatIsOut implements Runnable {

	private /*volatile*/ int count = 100;
	
	@Override
	public /*synchronized*/ void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T05_exWhatIsOut t = new T05_exWhatIsOut();
		for(int i=0; i<100; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
}
