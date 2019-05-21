package com.jstfs.practice.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

public class TestCyclicBarrier {
	private final CyclicBarrier lock = new CyclicBarrier(3, new CookThread());
	
	@Test
	public void testMain() {
		int count = 4;
		while(count > 0) {
			Thread t1 = new Thread(new ReadyThread(lock, "买西红柿", 2000));
			Thread t2 = new Thread(new ReadyThread(lock, "买鸡蛋", 1500));
			Thread t3 = new Thread(new ReadyThread(lock, "买食用油", 2100));
			
			t1.start();
			t2.start();
			t3.start();
			
			try {
				t1.join();
				t2.join();
				t3.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count--;
		}
	}
	
	class CookThread implements Runnable {
		@Override
		public void run() {
			System.out.println("lock.getNumberWaiting():" + lock.getNumberWaiting());
			System.out.println("lock.getParties():" + lock.getParties());
			System.out.println("lock.isBroken():" + lock.isBroken());
			System.out.println("由" + Thread.currentThread().getName() + ":开始做西红柿炒鸡蛋,3秒后出锅...");
			for(int i = 3; i > 0; i--) {
				try {
					System.out.println(i + "...");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + ":★★★西红柿炒鸡蛋出锅★★★\n\n");
		}
	}
	
	class ReadyThread implements Runnable {
		private CyclicBarrier lock;
		private long elapse;
		private String name;
		
		ReadyThread(CyclicBarrier lock, String name, long elapse) {
			this.lock = lock;
			this.name = name;
			this.elapse = elapse;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":开始 " + name + "...");
			try {
				Thread.sleep(elapse);
				System.out.println(Thread.currentThread().getName() + ":" + name + "完成");
				lock.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
