package com.jstfs.practice.designpattern.creational.factorymethod.product;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:55:46
 */
public class Strawberry implements IFruit {
	public void plant() {
		System.out.println("Strawberry has been planted.");
	}

	public void grow() {
		System.out.println("Strawberry is growing...");
	}

	public void harvest() {
		System.out.println("Strawberry has been harvested.");
	}
}
