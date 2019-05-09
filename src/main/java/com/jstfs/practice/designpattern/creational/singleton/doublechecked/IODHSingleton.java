package com.jstfs.practice.designpattern.creational.singleton.doublechecked;

/**
 * IODH: Initialization On Demand Holder
 * 
 * 这个方式的代码更加优雅,其原理是:类的初始化时,JVM会自动加锁,这个锁可以让多个线程对同一个类的初始化做到同步
 * 
 * @createBy jstfs
 * @createTime 2018-10-25 上午11:00:50
 */
public class IODHSingleton {
	private IODHSingleton() {}
	
	private static class InstanceHolder {
		static final IODHSingleton instance = new IODHSingleton();
	}
	
	public static IODHSingleton getInstance() {
		return InstanceHolder.instance;
	}
}
