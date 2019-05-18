package com.jstfs.practice.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.junit.Before;
import org.junit.Test;

public class TestReentrantReadWriteLock {
	private Map<String, String> buffer = new HashMap<String, String>();
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final ReadLock r = lock.readLock();
	private final WriteLock w = lock.writeLock();
	
	@Before
	public void init() {
		buffer.put("0501", "劳动最光荣");
		buffer.put("0601", "祝儿子健康成长");
		buffer.put("0701", "敬礼!");
		buffer.put("0801", "天朝威武");
		buffer.put("1001", "祖国,生日快乐");
	}
	
	@Test
	public void testMain() {
		Thread t1 = new Thread(new MyThread("0501"));
		Thread t2 = new Thread(new MyThread("1111"));
		Thread t3 = new Thread(new MyThread("1111"));
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	class MyThread implements Runnable {
		private String input;
		MyThread(String input) {
			this.input = input;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":" + getDataWithBuffer(input));
		}
	}
	
	public String getDataWithBuffer(String input) {
		String output = null;
		r.lock();
		
		try {
			w.tryLock();
			output = buffer.get(input);
		} finally {
			r.unlock();
		}
		
		if(output == null) {
			w.lock();
			try {
				output = buffer.get(input);
				if(output == null) {
					output = "光棍还过个毛的节日";
					buffer.put(input, output);
				}
			} finally {
				w.unlock();
			}
		}
		return output;
	}
}
