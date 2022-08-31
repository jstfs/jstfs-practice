package com.jstfs.practice.designpattern.creational.simplefactory.product;

/**
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:54:22
 */
public class BadFruit implements IFruit {
	@Override
	public void grow() {
		System.out.println("I am a Bad Fruit...");
	}

	@Override
	public void harvest() {
		System.out.println("I am a Bad Fruit...");
	}
	
	@Override
	public void plant() {
		System.out.println("I am a Bad Fruit...");
	}
}