package com.jstfs.practice.designpattern.creational.singleton.doublechecked;

/**
 * Double Checked 方式
 */
public class Client {
	public static void main(String[] args) {
		DoubleCheckVolatileSingleton ls1 = DoubleCheckVolatileSingleton.getInstance();
		DoubleCheckVolatileSingleton ls2 = DoubleCheckVolatileSingleton.getInstance();
		System.out.println(ls1 == ls2);
	}
}
