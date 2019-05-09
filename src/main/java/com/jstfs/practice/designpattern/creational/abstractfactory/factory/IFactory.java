package com.jstfs.practice.designpattern.creational.abstractfactory.factory;

import com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer.IFarmer;
import com.jstfs.practice.designpattern.creational.abstractfactory.product.tank.ITank;

/**
 * 工厂接口
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:36:57
 */
public interface IFactory {
	IFarmer createFarmer();
	ITank createTank();
}
