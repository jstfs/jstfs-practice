package com.jstfs.practice.designpattern.creational.factorymethod.factory;

import com.jstfs.practice.designpattern.creational.factorymethod.product.Apple;
import com.jstfs.practice.designpattern.creational.factorymethod.product.IFruit;

/**
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:56:10
 */
public class AppleGardener implements FruitGardener {
	@Override
	public IFruit produce() {
		return new Apple();
	}
}