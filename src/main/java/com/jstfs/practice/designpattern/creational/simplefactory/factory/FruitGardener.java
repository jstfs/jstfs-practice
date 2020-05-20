package com.jstfs.practice.designpattern.creational.simplefactory.factory;

import com.jstfs.practice.designpattern.creational.simplefactory.product.Apple;
import com.jstfs.practice.designpattern.creational.simplefactory.product.BadFruit;
import com.jstfs.practice.designpattern.creational.simplefactory.product.Grape;
import com.jstfs.practice.designpattern.creational.simplefactory.product.IFruit;
import com.jstfs.practice.designpattern.creational.simplefactory.product.Strawberry;

/**
 * 园丁(工厂类):可以根据客户需求来生产所有水果
 *
 * @createBy jstfs
 * @createTime 2018-10-24 上午10:53:22
 */
public class FruitGardener {
	public static IFruit produce(String fruitName) {
		if (fruitName.equalsIgnoreCase("apple")) {
			return new Apple();
		} else if (fruitName.equalsIgnoreCase("strawberry")) {
			return new Strawberry();
		} else if (fruitName.equalsIgnoreCase("grape")) {
			return new Grape();
		} else {
			return new BadFruit();
		}
	}
}