package com.jstfs.practice.designpattern.creational.singleton.iodh;

/**
 * IODH模式
 * 
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:45:33
 */
public class Client {
	public static void main(String[] args) {
		IODHSingleton es1 = IODHSingleton.getInstance();
		IODHSingleton es2 = IODHSingleton.getInstance();
		System.out.println(es1 == es2);
	}
}
