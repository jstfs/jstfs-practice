package com.jstfs.practice.designpattern.creational.abstractfactory.factory;

import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.Acolyte;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.IFarmer;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.Abomination;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.ITank;

/**
 * 不死族工厂,可以生产不死族不同种类的兵种
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:38:01
 */
public class UDFactory implements IFactory {
	@Override
	public IFarmer createFarmer() {
		return new Acolyte();
	}

	@Override
	public ITank createTank() {
		return new Abomination();
	}
}
