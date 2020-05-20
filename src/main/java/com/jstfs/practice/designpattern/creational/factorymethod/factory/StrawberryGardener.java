package com.jstfs.practice.designpattern.creational.factorymethod.factory;

import com.jstfs.practice.designpattern.creational.factorymethod.product.IFruit;
import com.jstfs.practice.designpattern.creational.factorymethod.product.Strawberry;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:56:25
 */
public class StrawberryGardener implements FruitGardener {
	@Override
	public IFruit produce() {
		return new Strawberry();
	}
}