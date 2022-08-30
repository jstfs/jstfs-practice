package com.jstfs.practice.designpattern.creational.singleton.doublechecked;

/**
 * 这种双重检查看起来比较完美,既延迟了初始化,又避免了初始化完成之后的高并发性能降低
 * 但实际上引用类型的成员变量 instance 在初始化的过程中,由于可能会发生"内存重排序"(引用先指向申请到的内存地址,再初始化内存中的内容)
 * 也就是说先使 instance != null,然后再初始化.那么其他线程就有可能在 "first" 处不进入,而直接返回一个没有完整初始化的对象,以至于使用该不完整的对象时发生异常
 * 
 * 解决方式有两种:
 * 	1, 阻止内存重排序,见: DoubleCheckVolatileSingleton.java
 *  2, 将内存重排序带来的过程中的 "instance != null" 不要让外部线程"看到".有一个技巧就是 IODH,见: IODHSingleton.java
 *  
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:52:29 
 */
public class DoubleCheckSingleton {
	private static DoubleCheckSingleton instance;
	
	private DoubleCheckSingleton() {}
	
	public static DoubleCheckSingleton getInstance() {
		if(instance == null) {	//first
			synchronized (DoubleCheckSingleton.class) {
				if(instance == null) {	//second
					instance = new DoubleCheckSingleton();
				}
			}
		}
		
		return instance;
	}
}
