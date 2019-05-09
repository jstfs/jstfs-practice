package com.jstfs.practice.designpattern.creational.singleton.lazy;

/**
 * 高并发的情况,同步方法会影响性能
 */
public class Client {
	public static void main(String[] args) {
		LazySingleton ls1 = LazySingleton.getInstance();
		LazySingleton ls2 = LazySingleton.getInstance();
		System.out.println(ls1 == ls2);
	}
}
