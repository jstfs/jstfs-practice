package com.jstfs.practice.test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

import org.junit.Before;
import org.junit.Test;

public class TestCountDownLatch {
	private Map<String, String> buffer = new HashMap<String, String>();
	private final StampedLock lock = new StampedLock();
	
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
		//优先进行乐观读,乐观读是不加锁的
		long stamp = lock.tryOptimisticRead();
		output = buffer.get(input);
		if(!lock.validate(stamp)) {
			//说明乐观读之后,有其他线程进行了写操作,则升级为读锁,重新获取数据
			//有一个疑问,就是当validate验证成功之后,并且当前线程结束,也就是说还没有使用完缓存数据,这个时候缓存被重新写入了
			//这样看起来validate()的意义在哪里?我理解validate()是防止数据的不一致性,
			//比如一次要从缓存中拿很多业务数据,而调用validate()则可以保证要么拿到的都是老数据,要么就在读锁之后拿到的全部是新数据
			//可以防止乐观读之后拿的数据,由于写线程正在写入数据,从而导致拿了一部分老数据,一部分新数据
			stamp = lock.readLock();
			try {
				output = buffer.get(input);
			} finally {
				lock.unlockRead(stamp);
			}
		}
		
		if(output == null) {
			stamp = lock.writeLock();
			try {
				output = buffer.get(input);
				if(output == null) {
					output = "光棍还过个毛的节日";
					buffer.put(input, output);
				}
			} finally {
				lock.unlockWrite(stamp);
			}
		}
		return output;
	}
}
