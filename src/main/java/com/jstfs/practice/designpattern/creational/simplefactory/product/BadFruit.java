package com.jstfs.practice.designpattern.creational.simplefactory.product;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:54:22
 */
public class BadFruit implements IFruit {
	public void grow() {
		System.out.println("I am a Bad Fruit...");
	}

	public void harvest() {
		System.out.println("I am a Bad Fruit...");
	}

	public void plant() {
		System.out.println("I am a Bad Fruit...");
	}
}
