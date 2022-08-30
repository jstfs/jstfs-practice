package com.jstfs.practice.designpattern.creational.singleton.doublechecked;

/**
 * 使用  volitile 关键字,可以阻止"内存重排序"以及禁用CPU缓存
 * 但想让 volitile 关键字达到阻止"内存重排序"的效果,必须使用JDK5及以上版本(1.5版本对 volitile 关键字的语义进行了增强)
 * 
 * @createBy jstfs
 * @createTime 2018-10-25 上午11:00:33
 */
public class DoubleCheckVolatileSingleton {
	private volatile static DoubleCheckVolatileSingleton instance;
	
	private DoubleCheckVolatileSingleton() {}
	
	public static DoubleCheckVolatileSingleton getInstance() {
		if(instance == null) {	//first
			synchronized (DoubleCheckVolatileSingleton.class) {
				if(instance == null) {	//second
					instance = new DoubleCheckVolatileSingleton();
				}
			}
		}
		return instance;
	}
}
