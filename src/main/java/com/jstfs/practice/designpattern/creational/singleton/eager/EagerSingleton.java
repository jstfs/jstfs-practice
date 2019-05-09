package com.jstfs.practice.designpattern.creational.singleton.eager;

/**
 * 如果并没有对系统启动时的效率有要求,则应该优先选择该饿汉模式
 * 如果需要延迟初始化,并且在线程安全的前提下还不影响性能,参考: doublechecked 包
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:44:19
 */
public class EagerSingleton {
	private static EagerSingleton instance = new EagerSingleton();
	
	private EagerSingleton() {}
	
	public static EagerSingleton getInstance() {
		return instance;
	}
}
