package com.jstfs.practice.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.jstfs.common.utils.MyRandomUtils;

public class TestReentrantLock {
	private final ReentrantLock lock = new ReentrantLock();
	
	private int count = 0;
	
	@Test
	public void testMain() {
		Thread t1 = new Thread(new MyThread(lock, "张三", 1000));
		Thread t2 = new Thread(new MyThread(lock, "李四", 1500));
		Thread t3 = new Thread(new MyThread(lock, "王五", 500));
		
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
		
		System.out.println("计算完成");
	}
	
	class MyThread implements Runnable {
		private ReentrantLock lock;
		private long elapse;
		private String name;
		
		MyThread(ReentrantLock lock) {
			this(lock, "");
		}
		
		MyThread(ReentrantLock lock, String name) {
			this(lock, name, 1000);
		}
		
		MyThread(ReentrantLock lock, String name, long elapse) {
			this.lock = lock;
			this.name = name;
			this.elapse = elapse;
		}
		
		@Override
		public void run() {
			lock.lock();
			MyRandomUtils.setSeed(System.currentTimeMillis());
			int cycle = MyRandomUtils.nextInteger(5);
			try {
				cycleAdd(cycle);
			} finally {
				lock.unlock();
			}
		}
		
		private void cycleAdd(int cycle) {
			lock.lock();
			try {
				for(int i = cycle; i > 0; i--) {
					Thread.sleep(elapse);
					count++;
					System.out.println("count由" + (StringUtils.isEmpty(name) ? Thread.currentThread().getName() : name) + "累加后为;" + count);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
