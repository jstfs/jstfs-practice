package com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer;

/**
 * 兽族农民(苦工)
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:35:18
 */
public class Poen implements IFarmer {
	@Override
	public void build() {
		System.out.println("Ready to work!");
	}
}
