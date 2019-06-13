package com.jstfs.practice.test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

import com.jstfs.common.utils.MyRandomUtils;

public class TestThreadPoolExecutor {
	private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
	private static ThreadFactory factory = new MyThreadFactory(); 
	private static RejectedExecutionHandler rej = new CallerRunsPolicy();
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, queue, factory, rej);
	
	public static void main(String[] args) {
		TestThreadPoolExecutor tpe = new TestThreadPoolExecutor();
		Thread.currentThread().setName("Thread-Main");
		for(int i = 1; i <= 150; i++) {
			String taskName = (i >= 100 ? "Task-" : i >= 10 ? "Task-0" : "Task-00") + i;
			Runnable r = tpe.new Task(taskName);
			try {
				executor.execute(r);
			} catch(Throwable e) {
				System.out.println(e);
			}
		}
		long waitStart = System.currentTimeMillis();
		while(true) {
			long waitNow = System.currentTimeMillis();
			long diff = waitNow - waitStart;
			if(diff > 10000) {
				break;
			} else {
				try {
					Thread.sleep(1000);
					System.out.println("============================executor.getPoolSize():" + executor.getPoolSize());
					System.out.println("============================executor.getMaximumPoolSize():" + executor.getMaximumPoolSize());
					System.out.println("============================executor.getActiveCount():" + executor.getActiveCount());
					System.out.println("============================executor.getLargestPoolSize():" + executor.getLargestPoolSize());
					System.out.println("============================executor.getCompletedTaskCount():" + executor.getCompletedTaskCount());
					System.out.println("============================executor.getTaskCount():" + executor.getTaskCount());
					System.out.println("============================main diff:" + diff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    class Task implements Runnable {
    	String taskName;
    	
    	public String getTaskName() {
			return taskName;
		}
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
		public Task(String taskName) {
    		this.taskName = taskName;
    	}
    	@Override
    	public void run() {
    		String name = Thread.currentThread().getName();
    		Integer sleepTime = MyRandomUtils.nextInteger(5);
    		try {
				Thread.sleep(sleepTime.longValue() * 100);
				System.out.println(name + "\tRun " + getTaskName() + "\tend(" + (sleepTime * 100) + ")");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    static class MyThreadFactory implements ThreadFactory {
    	Integer i = 1;
    	String name = "Thread";

		@Override
		public Thread newThread(Runnable r) {
			String tName;
			if(i.toString().length() == 2) {
				tName = name + "-" + i++;
			} else {
				tName = name + "-0" + i++;
			}
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.setName(tName);
			return t;
		}
    }
}
