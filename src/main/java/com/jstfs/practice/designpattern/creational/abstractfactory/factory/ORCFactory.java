package com.jstfs.practice.designpattern.creational.abstractfactory.factory;

import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.IFarmer;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.Poen;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.ITank;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.Tauren;

/**
 * 兽族工厂,可以生产兽族不通种类的兵种
 *
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:37:34
 */
public class ORCFactory implements IFactory {
	@Override
	public IFarmer createFarmer() {
		return new Poen();
	}

	@Override
	public ITank createTank() {
		return new Tauren();
	}
}