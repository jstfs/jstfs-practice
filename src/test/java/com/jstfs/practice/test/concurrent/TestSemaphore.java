package com.jstfs.practice.test.concurrent;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class TestSemaphore {
	static int count = 0;
	static Semaphore smp = new Semaphore(3);
	
	@Test
	public void testMain() {
		int i = 1;
		
		//提前耗尽所有许可
		smp.drainPermits();
		
		while(true) {
			Thread t = null;
			if(i == 3) {
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							addNoAcquire();
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				});
			} else {
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							add();
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				});
			}
			
			t.start();
			if(i == 5) {
				break;
			}
			i++;
		}
		try{
			Thread.sleep(100);
			System.out.println(count);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void add() throws InterruptedException {
		try {
			smp.acquire();
			count++;
		} finally {
			smp.release();
		}
	}
	
	/**
	 * 经测试:
	 * 	1, 不获取许可,也可以释放许可,并且释放的许可是可用的
	 * 	2, 即使许可被耗尽,也可以通过release()方法生成一个可用的许可,供其他线程去抢
	 * 	3, 额外释放的许可可以使许可总数高于初始化时的许可数
	 */
	public void addNoAcquire() throws InterruptedException {
		try{
			count++;
		} finally {
			smp.release();
		}
	}
}
