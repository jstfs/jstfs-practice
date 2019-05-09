package com.jstfs.practice.designpattern.creational.factorymethod;

import com.jstfs.practice.designpattern.creational.factorymethod.factory.AppleGardener;
import com.jstfs.practice.designpattern.creational.factorymethod.factory.FruitGardener;
import com.jstfs.practice.designpattern.creational.factorymethod.factory.GrapeGardener;
import com.jstfs.practice.designpattern.creational.factorymethod.factory.StrawberryGardener;

/**
 * 工厂方法,每种工厂生产不同类的产品
 * 二维:每个工厂和自己生产的产品构成线条,然后多个这样的线条在同一个平面内相互平行
 * 
 * JDK中:
 *  	Iterable(迭代器工厂接口)		-->		Iterator(迭代器产品接口)
 *  	各种集合类(生产迭代器的具体工厂)	-->		各种集合类中的iterator()方法返回值(具体迭代器产品)	
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:56:40
 */
public class Client {
	public static void main(String[] args) {
		fruitPlant(new StrawberryGardener());
		fruitGrow(new AppleGardener());
		fruitHarvest(new GrapeGardener());
	}

	private static void fruitPlant(FruitGardener gardener) {
		gardener.produce().plant();
	}
	
	private static void fruitGrow(FruitGardener gardener) {
		gardener.produce().grow();
	}

	private static void fruitHarvest(FruitGardener gardener) {
		gardener.produce().harvest();
	}
}
