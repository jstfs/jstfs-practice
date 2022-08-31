package com.jstfs.practice.designpattern.creational.singleton.lazy;

/**
 * 由于懒汉模式对于获取单例实例的方法做了同步,所以当高并发的时候,性能有影响
 * 而同步只是为了阻止初始化动作不能被多个线程同时执行,一旦初始化完成之后,同步功能就会完全没必要
 * 如何解决这个问题,人们想到了一个看似优美的 "Double Checked" 方式,见 doublechecked 包
 * 
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:45:45
 */
public class LazySingleton {
	private static LazySingleton instance;
	
	private LazySingleton() {}
	
	synchronized public static LazySingleton getInstance() {
		if(instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
