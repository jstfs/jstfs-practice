package com.jstfs.practice.designpattern.creational.factorymethod.factory;

import com.jstfs.practice.designpattern.creational.factorymethod.product.IFruit;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:56:05
 */
public interface FruitGardener {
	public IFruit produce();
}
