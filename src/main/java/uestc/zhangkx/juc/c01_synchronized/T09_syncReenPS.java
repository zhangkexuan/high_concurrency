package uestc.zhangkx.juc.c01_synchronized;

import java.util.concurrent.TimeUnit;

/**
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö������.
 * Ҳ����˵synchronized��õ����ǿ������
 * �����Ǽ̳����п��ܷ��������Σ�������ø����ͬ������
 * @author zhangkx
 */
class parent {
	synchronized void m() {
		System.out.println("parent m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("parent m end");
	}
	
	public static void main(String[] args) {
		new child().m();
	}
	
}

class child extends parent {
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}
