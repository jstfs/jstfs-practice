package com.jstfs.practice.test.concurrent;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TestCountDownLatch {
	private final CountDownLatch lock = new CountDownLatch(3);
	
	@Test
	public void testMain() {
		Thread t1 = new Thread(new MyThread(lock, "买西红柿", 1000));
		Thread t2 = new Thread(new MyThread(lock, "买鸡蛋", 2500));
		Thread t3 = new Thread(new MyThread(lock, "买食用油", 2000));
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			lock.await();
			System.out.println("材料准备完成,开始做西红柿炒蛋");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class MyThread implements Runnable {
		private CountDownLatch lock;
		private long elapse;
		private String name;
		MyThread(CountDownLatch lock, String name, long elapse) {
			this.lock = lock;
			this.name = name;
			this.elapse = elapse;
		}
		
		@Override
		public void run() {
			System.out.println("开始 " + name + "...");
			try {
				Thread.sleep(elapse);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "完成");
			lock.countDown();
		}
	}
}
