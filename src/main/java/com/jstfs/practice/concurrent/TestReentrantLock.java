package com.jstfs.practice.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

	final Lock lock = new ReentrantLock();
	
	public static void main(String[] args) throws Exception {
		final TestReentrantLock test = new TestReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					test.get();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				test.set();
			}
		});
		
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		
		while(true) {
			Thread.sleep(1000);
			System.out.println("t1:" + t1.getState());
			System.out.println("t2:" + t2.getState());
		}
	}
	
	private void get() {
		System.out.println("get in");
		lock.lock();
		System.out.println("get lock");
		try {
			Thread.sleep(30000);
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
			System.out.println("get out");
		}
	}
	
	private void set() {
		System.out.println("set in");
		lock.lock();
		System.out.println("set lock");
		try {
			Thread.sleep(10);
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
			System.out.println("set out");
		}
	}
}
