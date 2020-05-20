package com.jstfs.practice.designpattern.creational.simplefactory;

import com.jstfs.practice.designpattern.creational.simplefactory.factory.FruitGardener;
import com.jstfs.practice.designpattern.creational.simplefactory.product.IFruit;

/**
 * 简单工厂,所有产品都由一个工厂生产
 * 一维:工厂和产品两个点构成了一条直线
 * 
 * JDK中:
 * 		DateFormat
 * 		NumberFormat
 * 
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:55:00
 */
public class Client {
	public static void main(String[] args) {
		IFruit apple = FruitGardener.produce("apple");
		apple.grow();
		IFruit strawBerry = FruitGardener.produce("strawberry");
		strawBerry.plant();
		IFruit grape = FruitGardener.produce("grape");
		grape.harvest();
		
		IFruit badFruit = FruitGardener.produce("orange");
		badFruit.grow();
		badFruit.plant();
		badFruit.harvest();
	}
}