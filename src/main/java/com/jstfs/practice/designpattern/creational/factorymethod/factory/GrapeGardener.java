package com.jstfs.practice.designpattern.creational.factorymethod.factory;

import com.jstfs.practice.designpattern.creational.factorymethod.product.Grape;
import com.jstfs.practice.designpattern.creational.factorymethod.product.IFruit;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:56:17
 */
public class GrapeGardener implements FruitGardener {
	public IFruit produce() {
		return new Grape();
	}
}
