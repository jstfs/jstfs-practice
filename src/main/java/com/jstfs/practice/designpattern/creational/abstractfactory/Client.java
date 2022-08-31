package com.jstfs.practice.designpattern.creational.abstractfactory;

import com.jstfs.practice.designpattern.creational.abstractfactory.factory.IFactory;
import com.jstfs.practice.designpattern.creational.abstractfactory.factory.ORCFactory;
import com.jstfs.practice.designpattern.creational.abstractfactory.factory.UDFactory;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.IFarmer;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.ITank;

/**
 * 抽象工厂,每种工厂生产与某个种类相关的一系列(一族)产品
 * 三维:多个产品之间按某种分类方式(种族)先横向排列,然后再按某种分类方式(兵种)纵向排列,如下:
 * 					兽族工厂↓				亡灵工厂↓
 * 				----------------------------------------------------> 种族
 * 	农民工厂-->	|	兽族苦工(Peon)		亡灵侍僧(Acolyte)
 * 	肉盾工厂-->	|	兽族牛头人(Tauren)		亡灵憎恶(Abomination)
 * 				v
 * 			         兵种
 * 
 * 而工厂可以想象为垂直于纸面且平行于兵种或者种族方向的两个木板,他们负责生产同一个维度的产品
 * 此时就会有两种工厂维度的选择,如上图.从设计上来说都没有问题,但从使用上,可能不太方便,因为同一种族的士兵经常同时出现
 * 所以从种族维度切入使用兽族工厂和亡灵工厂就更合理.
 * 所以综上:
 * 		1, 抽象工厂需要所有产品满足可以从两个维度来划分种类
 * 		2, 而工厂的选择就要看实际应用场景中哪个维度的产品经常一起使用了
 * 		3, 没有被选择为工厂的另一个维度上的产品需要具有相同的接口
 * 		4, 与工厂方法的区别:就是工厂方法模式没有产品族的概念
 * 
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:43:46
 */
public class Client {
	public static void main(String[] args) {
		go(new ORCFactory());
		go(new UDFactory());
	}
	
	public static void go(IFactory factory) {
		IFarmer farmer = factory.createFarmer();
		ITank tank = factory.createTank();
		
		farmer.build();
		System.out.println(tank.getBloodVolume());
	}
}