package com.jstfs.practice.designpattern.creational.factorymethod.product;

/**
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:55:46
 */
public class Strawberry implements IFruit {
	@Override
	public void plant() {
		System.out.println("Strawberry has been planted.");
	}

	@Override
	public void grow() {
		System.out.println("Strawberry is growing...");
	}

	@Override
	public void harvest() {
		System.out.println("Strawberry has been harvested.");
	}
}