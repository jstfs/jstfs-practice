package com.jstfs.practice.designpattern.creational.singleton.eager;

/**
 * 饿汉模式
 * 
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:45:33
 */
public class Client {
	public static void main(String[] args) {
		EagerSingleton es1 = EagerSingleton.getInstance();
		EagerSingleton es2 = EagerSingleton.getInstance();
		System.out.println(es1 == es2);
	}
}
